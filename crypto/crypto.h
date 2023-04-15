
#pragma once

#include <stdint.h>

typedef struct
{
  uint8_t data[32];
} elliptic_curve_point_t;

typedef struct
{
  uint8_t data[32];
} elliptic_curve_scalar_t;

#define VARINT_WRITE(DEST, INT)            \
  while ((INT) >= 0x80)                      \
  {                                        \
    *(DEST)++ = ((char)(INT)&0x7f) | 0x80; \
    (INT) >>= 7;                           \
  }                                        \
  *DEST++ = (char)(INT);

void random_scalar(uint8_t *res);
void hash_to_scalar(const uint8_t *scalar, size_t length, uint8_t *hash);
void generate_keys(uint8_t *public_key, uint8_t *secret_key);

int check_key(const uint8_t *public_key);
int secret_key_to_public_key(const uint8_t *secret_key, uint8_t *public_key);
int generate_key_derivation(const uint8_t *public_key, const uint8_t *secret_key, uint8_t *key_derivation);

int derive_public_key(const uint8_t *derivation, size_t output_index,
                       const uint8_t *base, uint8_t *derived_key);
int derive_public_key_suffix(const uint8_t *derivation, size_t output_index,
                              const uint8_t *base, const uint8_t *suffix, size_t suffixLength, uint8_t *derived_key);

void derive_secret_key(const uint8_t *derivation, size_t output_index, const uint8_t *base, uint8_t *derived_key);
void derive_secret_key_suffix(const uint8_t *derivation, size_t output_index, const uint8_t *base, const uint8_t *suffix, size_t suffixLength, uint8_t *derived_key);

int underive_public_key(const uint8_t *derivation, size_t output_index,
                         const uint8_t *derived_key, uint8_t *base);
int underive_public_key_suffix(const uint8_t *derivation, size_t output_index,
                         const uint8_t *derived_key, const uint8_t *suffix, size_t suffixLength, uint8_t *base);

int underive_public_key_and_get_scalar(const uint8_t *derivation, size_t output_index,
                                        const uint8_t *derived_key, uint8_t *base, uint8_t *hashed_derivation);
void hash_data_to_ec(const uint8_t *data, size_t len, uint8_t *key);

void generate_signature(const uint8_t *prefix_hash, const uint8_t *pub, const uint8_t *sec, uint8_t *sig);
int check_signature(const uint8_t *prefix_hash, const uint8_t *pub, const uint8_t *sig);

void hash_to_ec(const uint8_t *key, uint8_t *res);
void generate_key_image(const uint8_t *pub, const uint8_t *sec, uint8_t *image);

void generate_ring_signature(const uint8_t *prefix_hash, const uint8_t *image,
                             const uint8_t *const *pubs, size_t pubs_count,
                             const uint8_t *sec, size_t sec_index,
                             uint8_t *sig);
int check_ring_signature(const uint8_t *prefix_hash, const uint8_t *image,
                         const uint8_t *const *pubs, size_t pubs_count,
                         const uint8_t *sig);
                         
void hash_to_point(const uint8_t *hash, uint8_t *point);
void hash_to_ec_ex(const uint8_t *hash, uint8_t *ec);

