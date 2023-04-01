
#include <stdint.h>
#include <stdlib.h>
#include <memory.h>
#include "crypto-ops.h"
#include "crypto.h"
#include "random.h"
#include "hash-ops.h"
#include "types.h"

typedef struct
{
  hash_t h;
  elliptic_curve_point_t key;
  elliptic_curve_point_t comm;
} s_comm;

typedef struct
{
  hash_t h;
  struct
  {
    elliptic_curve_point_t a, b;
  } ab[];
} rs_comm;

int check_scalar(uint8_t *scalar)
{
  return sc_check(scalar) == 0;
}

int check_public_key(const uint8_t *public_key)
{
  ge_p3 point;
  return ge_frombytes_vartime(&point, public_key) == 0;
}

void random_scalar(uint8_t *res)
{
  unsigned char tmp[64];
  generate_random_bytes_not_thread_safe(64, tmp);
  sc_reduce(tmp);
  memcpy(res, tmp, 32);
}

void hash_to_scalar(const uint8_t *scalar, size_t length, uint8_t *hash)
{
  cn_fast_hash(scalar, length, (char *)hash);
  sc_reduce32(hash);
}

void generate_keys(uint8_t *public_key, uint8_t *secret_key)
{
  ge_p3 point;
  random_scalar(secret_key);
  ge_scalarmult_base(&point, secret_key);
  ge_p3_tobytes(public_key, &point);
}

int check_key(const uint8_t *public_key)
{
  ge_p3 point;
  return ge_frombytes_vartime(&point, public_key) == 0;
}

int secret_key_to_public_key(const uint8_t *secret_key, uint8_t *public_key)
{
  ge_p3 point;
  if (sc_check(secret_key) != 0)
  {
    return 0;
  }
  ge_scalarmult_base(&point, secret_key);
  ge_p3_tobytes(public_key, &point);
  return 1;
}

int generate_key_derivation(const uint8_t *public_key, const uint8_t *secret_key, uint8_t *key_derivation)
{
  ge_p3 point;
  ge_p2 point2;
  ge_p1p1 point3;
  assert(sc_check(secret_key) == 0);
  if (ge_frombytes_vartime(&point, public_key) != 0)
  {
    return 0;
  }
  ge_scalarmult(&point2, secret_key, &point);
  ge_mul8(&point3, &point2);
  ge_p1p1_to_p2(&point2, &point3);
  ge_tobytes(key_derivation, &point2);
  return 1;
}

static void derivation_to_scalar(const uint8_t *derivation, size_t output_index, uint8_t *res)
{
  struct
  {
    struct
    {
      char data[32];
    } derivation;
    char output_index[(sizeof(size_t) * 8 + 6) / 7];
  } buf;
  char *end = buf.output_index;
  memcpy(&buf.derivation, derivation, 32);
  VARINT_WRITE(end, output_index);
  assert(end <= buf.output_index + sizeof buf.output_index);
  hash_to_scalar((const uint8_t *)&buf, end - (char *)(&buf), res);
}

static void derivation_to_scalar_suffix(const uint8_t *derivation, size_t output_index, const uint8_t *suffix, size_t suffixLength, uint8_t *res)
{
  assert(suffixLength <= 32);
  struct
  {
    struct
    {
      char data[32];
    } derivation;
    char output_index[(sizeof(size_t) * 8 + 6) / 7 + 32];
  } buf;
  char *end = buf.output_index;
  memcpy(&buf.derivation, derivation, 32);
  VARINT_WRITE(end, output_index);

  assert(end <= buf.output_index + sizeof buf.output_index);
  size_t bufSize = end - (char *)(&buf);
  memcpy(end, suffix, suffixLength);
  hash_to_scalar((const uint8_t *)&buf, bufSize + suffixLength, res);
}

int derive_public_key(const uint8_t *derivation, size_t output_index,
                      const uint8_t *base, uint8_t *derived_key)
{
  elliptic_curve_scalar_t scalar;
  ge_p3 point1;
  ge_p3 point2;
  ge_cached point3;
  ge_p1p1 point4;
  ge_p2 point5;
  if (ge_frombytes_vartime(&point1, base) != 0)
  {
    return 0;
  }
  derivation_to_scalar(derivation, output_index, (uint8_t *)&scalar);
  ge_scalarmult_base(&point2, (uint8_t *)&scalar);
  ge_p3_to_cached(&point3, &point2);
  ge_add(&point4, &point1, &point3);
  ge_p1p1_to_p2(&point5, &point4);
  ge_tobytes(derived_key, &point5);
  return 1;
}

int derive_public_key_suffix(const uint8_t *derivation, size_t output_index,
                             const uint8_t *base, const uint8_t *suffix, size_t suffixLength, uint8_t *derived_key)
{
  elliptic_curve_scalar_t scalar;
  ge_p3 point1;
  ge_p3 point2;
  ge_cached point3;
  ge_p1p1 point4;
  ge_p2 point5;
  if (ge_frombytes_vartime(&point1, base) != 0)
  {
    return 0;
  }
  derivation_to_scalar_suffix((const uint8_t *)&derivation, output_index, suffix, suffixLength, (uint8_t *)&scalar);
  ge_scalarmult_base(&point2, (uint8_t *)&scalar);
  ge_p3_to_cached(&point3, &point2);
  ge_add(&point4, &point1, &point3);
  ge_p1p1_to_p2(&point5, &point4);
  ge_tobytes(derived_key, &point5);
  return 1;
}

int underive_public_key_and_get_scalar(const uint8_t *derivation, size_t output_index,
                                       const uint8_t *derived_key, uint8_t *base, uint8_t *hashed_derivation)
{
  ge_p3 point1;
  ge_p3 point2;
  ge_cached point3;
  ge_p1p1 point4;
  ge_p2 point5;
  if (ge_frombytes_vartime(&point1, derived_key) != 0)
  {
    return 0;
  }
  derivation_to_scalar(derivation, output_index, hashed_derivation);
  ge_scalarmult_base(&point2, hashed_derivation);
  ge_p3_to_cached(&point3, &point2);
  ge_sub(&point4, &point1, &point3);
  ge_p1p1_to_p2(&point5, &point4);
  ge_tobytes(base, &point5);
  return 1;
}

void derive_secret_key(const uint8_t *derivation, size_t output_index, const uint8_t *base, uint8_t *derived_key)
{
  elliptic_curve_scalar_t scalar;
  assert(sc_check(base) == 0);
  derivation_to_scalar(derivation, output_index, (uint8_t *)&scalar);
  sc_add(derived_key, base, (uint8_t *)&scalar);
}

void derive_secret_key_suffix(const uint8_t *derivation, size_t output_index, const uint8_t *base, const uint8_t *suffix, size_t suffixLength, uint8_t *derived_key)
{
  elliptic_curve_scalar_t scalar;
  assert(sc_check(base) == 0);
  derivation_to_scalar_suffix(derivation, output_index, suffix, suffixLength, (uint8_t *)&scalar);
  sc_add(derived_key, base, (uint8_t *)&scalar);
}

int underive_public_key(const uint8_t *derivation, size_t output_index,
                        const uint8_t *derived_key, uint8_t *base)
{
  elliptic_curve_scalar_t scalar;
  ge_p3 point1;
  ge_p3 point2;
  ge_cached point3;
  ge_p1p1 point4;
  ge_p2 point5;
  if (ge_frombytes_vartime(&point1, derived_key) != 0)
  {
    return 0;
  }
  derivation_to_scalar(derivation, output_index, (uint8_t *)&scalar);
  ge_scalarmult_base(&point2, (uint8_t *)&scalar);
  ge_p3_to_cached(&point3, &point2);
  ge_sub(&point4, &point1, &point3);
  ge_p1p1_to_p2(&point5, &point4);
  ge_tobytes(base, &point5);
  return 1;
}

int underive_public_key_suffix(const uint8_t *derivation, size_t output_index,
                               const uint8_t *derived_key, const uint8_t *suffix, size_t suffixLength, uint8_t *base)
{
  elliptic_curve_scalar_t scalar;
  ge_p3 point1;
  ge_p3 point2;
  ge_cached point3;
  ge_p1p1 point4;
  ge_p2 point5;
  if (ge_frombytes_vartime(&point1, derived_key) != 0)
  {
    return 0;
  }

  derivation_to_scalar_suffix(derivation, output_index, suffix, suffixLength, (uint8_t *)&scalar);
  ge_scalarmult_base(&point2, (uint8_t *)&scalar);
  ge_p3_to_cached(&point3, &point2);
  ge_sub(&point4, &point1, &point3);
  ge_p1p1_to_p2(&point5, &point4);
  ge_tobytes(base, &point5);
  return 1;
}

void hash_data_to_ec(const uint8_t *data, size_t len, uint8_t *key)
{
  hash_t h;
  ge_p2 point;
  ge_p1p1 point2;
  cn_fast_hash(data, len, (char *)&h);
  ge_fromfe_frombytes_vartime(&point, (const uint8_t *)(&h));
  ge_mul8(&point2, &point);
  ge_p1p1_to_p2(&point, &point2);
  ge_tobytes(key, &point);
}

void generate_signature(const uint8_t *prefix_hash, const uint8_t *pub, const uint8_t *sec, uint8_t *sig)
{
  // lock_guard<mutex> lock(random_lock);
  ge_p3 tmp3;
  elliptic_curve_scalar_t k;
  s_comm buf;
#if !defined(NDEBUG)
  {
    ge_p3 t;
    public_key_t t2;
    assert(sc_check(sec) == 0);
    ge_scalarmult_base(&t, sec);
    ge_p3_tobytes((uint8_t *)&t2, &t);
    // assert(*(public_key_t *)pub == t2);
    assert(memcmp(pub, &t2, sizeof(public_key_t)) == 0);
  }
#endif
  buf.h = *(const hash_t *)prefix_hash;
  buf.key = *(const elliptic_curve_point_t *)pub;
  random_scalar((uint8_t *)&k);
  ge_scalarmult_base(&tmp3, (uint8_t *)&k);
  ge_p3_tobytes((uint8_t *)(&buf.comm), &tmp3);
  hash_to_scalar((uint8_t *)&buf, sizeof(s_comm), sig);
  sc_mulsub(sig + 32, sig, sec, (uint8_t *)&k);
}

int check_signature(const uint8_t *prefix_hash, const uint8_t *pub, const uint8_t *sig)
{
  ge_p2 tmp2;
  ge_p3 tmp3;
  elliptic_curve_scalar_t c;
  s_comm buf;
  assert(check_key((uint8_t *)pub));
  buf.h = *(const hash_t *)prefix_hash;
  buf.key = *(const elliptic_curve_point_t *)pub;
  if (ge_frombytes_vartime(&tmp3, pub) != 0)
  {
    abort();
  }
  if (sc_check(sig) != 0 || sc_check(sig + 32) != 0)
  {
    return 0;
  }
  ge_double_scalarmult_base_vartime(&tmp2, sig, &tmp3, sig + 32);
  ge_tobytes((uint8_t *)(&buf.comm), &tmp2);
  hash_to_scalar((const uint8_t *)&buf, sizeof(s_comm), (uint8_t *)&c);
  sc_sub((uint8_t *)&c, (const uint8_t *)&c, sig);
  return sc_isnonzero((const uint8_t *)&c) == 0;
}

void hash_to_ec(const uint8_t *key, uint8_t *res)
{
  hash_t h;
  ge_p2 point;
  ge_p1p1 point2;
  cn_fast_hash(key, 32, (char *)&h);
  ge_fromfe_frombytes_vartime(&point, (const uint8_t *)(&h));
  ge_mul8(&point2, &point);
  ge_p1p1_to_p3((ge_p3 *)res, &point2);
}

void generate_key_image(const uint8_t *pub, const uint8_t *sec, uint8_t *image)
{
  ge_p3 point;
  ge_p2 point2;
  assert(sc_check(sec) == 0);
  hash_to_ec(pub, (uint8_t *)&point);
  ge_scalarmult(&point2, sec, &point);
  ge_tobytes(image, &point2);
}

void generate_incomplete_key_image(const uint8_t *pub, uint8_t *incomplete_key_image)
{
  ge_p3 point;
  hash_to_ec(pub, (uint8_t *)&point);
  ge_p3_tobytes(incomplete_key_image, &point);
}

static inline size_t rs_comm_size(size_t pubs_count)
{
  rs_comm rs;
  return sizeof(rs_comm) + pubs_count * sizeof(rs.ab[0]);
}

void generate_ring_signature_ex(const uint8_t *prefix_hash, const uint8_t *image,
                                const public_key_t *const *pubs, size_t pubs_count,
                                const uint8_t *sec, size_t sec_index,
                                uint8_t *sig)
{
  size_t i;
  ge_p3 image_unp;
  ge_dsmp image_pre;
  elliptic_curve_scalar_t sum, k, h;
  rs_comm *const buf = (rs_comm *)(alloca(rs_comm_size(pubs_count)));
  assert(sec_index < pubs_count);
#if !defined(NDEBUG)
  {
    ge_p3 t;
    public_key_t t2;
    key_image_t t3;
    assert(sc_check(sec) == 0);
    ge_scalarmult_base(&t, sec);
    ge_p3_tobytes((uint8_t *)(&t2), &t);
    assert(memcmp(pubs[sec_index], &t2, sizeof(public_key_t)) == 0);
    generate_key_image((const uint8_t *)&(*pubs[sec_index]), sec, (uint8_t *)&t3);
    assert(memcmp(image, &t3, sizeof(key_image_t)) == 0);

    for (i = 0; i < pubs_count; i++)
    {
      assert(check_key((uint8_t *)&(*pubs[i])));
    }
  }
#endif
  if (ge_frombytes_vartime(&image_unp, image) != 0)
  {
    abort();
  }
  ge_dsm_precomp(image_pre, &image_unp);
  sc_0((uint8_t *)(&sum));
  buf->h = *(const hash_t *)prefix_hash;

  for (i = 0; i < pubs_count; i++)
  {
    ge_p2 tmp2;
    ge_p3 tmp3;
    if (i == sec_index)
    {
      random_scalar((uint8_t *)&k);
      ge_scalarmult_base(&tmp3, (uint8_t *)(&k));
      ge_p3_tobytes((uint8_t *)(&buf->ab[i].a), &tmp3);
      hash_to_ec((const uint8_t *)pubs[i], (uint8_t *)&tmp3);
      ge_scalarmult(&tmp2, (uint8_t *)(&k), &tmp3);
      ge_tobytes((uint8_t *)(&buf->ab[i].b), &tmp2);
    }
    else
    {
      random_scalar((uint8_t *)&(sig[i * 64]));
      random_scalar((uint8_t *)((uint8_t *)(&sig[i * 64]) + 32));
      if (ge_frombytes_vartime(&tmp3, (const uint8_t *)(&*pubs[i])) != 0)
      {
        abort();
      }
      ge_double_scalarmult_base_vartime(&tmp2, (uint8_t *)(&sig[i * 64]), &tmp3, (uint8_t *)(&sig[i * 64]) + 32);
      ge_tobytes((uint8_t *)(&buf->ab[i].a), &tmp2);
      hash_to_ec((const uint8_t *)pubs[i], (uint8_t *)&tmp3);
      ge_double_scalarmult_precomp_vartime(&tmp2, (uint8_t *)(&sig[i * 64]) + 32, &tmp3, (uint8_t *)(&sig[i * 64]), image_pre);
      ge_tobytes((uint8_t *)(&buf->ab[i].b), &tmp2);
      sc_add((uint8_t *)(&sum), (uint8_t *)(&sum), (uint8_t *)(&sig[i * 64]));
    }
  }
  hash_to_scalar((uint8_t *)buf, rs_comm_size(pubs_count), (uint8_t *)&h);
  sc_sub((uint8_t *)(&sig[sec_index * 64]), (uint8_t *)(&h), (uint8_t *)(&sum));
  sc_mulsub((uint8_t *)(&sig[sec_index * 64]) + 32, (uint8_t *)(&sig[sec_index * 64]), sec, (uint8_t *)(&k));
}

int check_ring_signature_ex(const hash_t *prefix_hash, const key_image_t *image,
                            const public_key_t *const *pubs, size_t pubs_count,
                            const signature_t *sig)
{
  size_t i;
  ge_p3 image_unp;
  ge_dsmp image_pre;
  elliptic_curve_scalar_t sum, h;
  rs_comm *const buf = (rs_comm *)(alloca(rs_comm_size(pubs_count)));
#if !defined(NDEBUG)
  for (i = 0; i < pubs_count; i++)
  {
    assert(check_key((const uint8_t *)pubs[i]));
  }
#endif
  if (ge_frombytes_vartime(&image_unp, (const unsigned char *)image) != 0)
  {
    return 0;
  }
  ge_dsm_precomp(image_pre, &image_unp);
  sc_0((unsigned char *)(&sum));
  buf->h = *prefix_hash;
  for (i = 0; i < pubs_count; i++)
  {
    ge_p2 tmp2;
    ge_p3 tmp3;
    if (sc_check((const unsigned char *)(&sig[i])) != 0 || sc_check((const unsigned char *)(&sig[i]) + 32) != 0)
    {
      return 0;
    }
    if (ge_frombytes_vartime(&tmp3, (const unsigned char *)(&*pubs[i])) != 0)
    {
      abort();
    }
    ge_double_scalarmult_base_vartime(&tmp2, (const unsigned char *)(&sig[i]), &tmp3, (const unsigned char *)(&sig[i]) + 32);
    ge_tobytes((unsigned char *)(&buf->ab[i].a), &tmp2);
    hash_to_ec((const uint8_t *)pubs[i], (uint8_t *)&tmp3);
    ge_double_scalarmult_precomp_vartime(&tmp2, (const unsigned char *)(&sig[i]) + 32, &tmp3, (const unsigned char *)(&sig[i]), image_pre);
    ge_tobytes((unsigned char *)(&buf->ab[i].b), &tmp2);
    sc_add((unsigned char *)(&sum), (unsigned char *)(&sum), (const unsigned char *)(&sig[i]));
  }
  hash_to_scalar((const uint8_t *)buf, rs_comm_size(pubs_count), (uint8_t *)&h);
  sc_sub((unsigned char *)(&h), (unsigned char *)(&h), (unsigned char *)(&sum));
  return sc_isnonzero((unsigned char *)(&h)) == 0;
}

void generate_ring_signature(const uint8_t *prefix_hash, const uint8_t *image,
                             const uint8_t *const *pubs, size_t pubs_count,
                             const uint8_t *sec, size_t sec_index,
                             uint8_t *sig)
{
  generate_ring_signature_ex(prefix_hash,
                             image,
                             (const public_key_t *const *)pubs, pubs_count, sec, sec_index, sig);
}

int check_ring_signature(const uint8_t *prefix_hash, const uint8_t *image,
                         const uint8_t *const *pubs, size_t pubs_count,
                         const uint8_t *sig)
{
  return check_ring_signature_ex(
      (const hash_t *)prefix_hash,
      (const key_image_t *)image, (const public_key_t *const *)pubs,
      pubs_count, (const signature_t *)sig);
}

void hash_to_point(const uint8_t *h, uint8_t *res)
{
  ge_p2 point;
  ge_fromfe_frombytes_vartime(&point, h);
  ge_tobytes(res, &point);
}

void hash_to_ec_ex(const uint8_t *hash, uint8_t *res)
{
  ge_p3 tmp;
  hash_to_ec(hash, (uint8_t *)&tmp);
  ge_p3_tobytes(res, &tmp);
}