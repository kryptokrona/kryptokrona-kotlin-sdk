// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#include "ge_fromfe_frombytes_vartime.h"

/* sqrt(x) is such an integer y that 0 <= y <= p - 1, y % 2 = 0, and y^2 = x (mod p). */
/* d = -121665 / 121666 */
static const fe fe_d =
    {-10913610, 13857413, -15372611, 6949391, 114729, -8787816, -6275908, -3247719, -18696448, -12055116}; /* d */

static const fe fe_sqrtm1 =
    {-32595792, -7943725, 9377950, 3500415, 12389472, -272473, -25146209, -2005654, 326686, 11406482}; /* sqrt(-1) */

static const fe fe_ma = {-486662, 0, 0, 0, 0, 0, 0, 0, 0, 0}; /* -A */

static const fe fe_ma2 = {-12721188, -3529, 0, 0, 0, 0, 0, 0, 0, 0}; /* -A^2 */

static const fe fe_fffb1 = {-31702527,
                            -2466483,
                            -26106795,
                            -12203692,
                            -12169197,
                            -321052,
                            14850977,
                            -10296299,
                            -16929438,
                            -407568}; /* sqrt(-2 * A * (A + 2)) */

static const fe fe_fffb2 = {8166131,
                            -6741800,
                            -17040804,
                            3154616,
                            21461005,
                            1466302,
                            -30876704,
                            -6368709,
                            10503587,
                            -13363080}; /* sqrt(2 * A * (A + 2)) */

static const fe fe_fffb3 = {-13620103,
                            14639558,
                            4532995,
                            7679154,
                            16815101,
                            -15883539,
                            -22863840,
                            -14813421,
                            13716513,
                            -6477756}; /* sqrt(-sqrt(-1) * A * (A + 2)) */

static const fe fe_fffb4 = {-21786234,
                            -12173074,
                            21573800,
                            4524538,
                            -4645904,
                            16204591,
                            8012863,
                            -8444712,
                            3212926,
                            6885324}; /* sqrt(sqrt(-1) * A * (A + 2)) */

void ge_fromfe_frombytes_vartime(ge_p2 *r, const unsigned char *s)
{
    fe u, v, w, x, y, z;
    unsigned char sign;

    /* From fe_frombytes.c */

    int64_t h0 = load_4(s);
    int64_t h1 = load_3(s + 4) << 6;
    int64_t h2 = load_3(s + 7) << 5;
    int64_t h3 = load_3(s + 10) << 3;
    int64_t h4 = load_3(s + 13) << 2;
    int64_t h5 = load_4(s + 16);
    int64_t h6 = load_3(s + 20) << 7;
    int64_t h7 = load_3(s + 23) << 5;
    int64_t h8 = load_3(s + 26) << 4;
    int64_t h9 = load_3(s + 29) << 2;
    int64_t carry0;
    int64_t carry1;
    int64_t carry2;
    int64_t carry3;
    int64_t carry4;
    int64_t carry5;
    int64_t carry6;
    int64_t carry7;
    int64_t carry8;
    int64_t carry9;

    carry9 = (h9 + (int64_t)(1 << 24)) >> 25;
    h0 += carry9 * 19;
    h9 -= carry9 << 25;
    carry1 = (h1 + (int64_t)(1 << 24)) >> 25;
    h2 += carry1;
    h1 -= carry1 << 25;
    carry3 = (h3 + (int64_t)(1 << 24)) >> 25;
    h4 += carry3;
    h3 -= carry3 << 25;
    carry5 = (h5 + (int64_t)(1 << 24)) >> 25;
    h6 += carry5;
    h5 -= carry5 << 25;
    carry7 = (h7 + (int64_t)(1 << 24)) >> 25;
    h8 += carry7;
    h7 -= carry7 << 25;

    carry0 = (h0 + (int64_t)(1 << 25)) >> 26;
    h1 += carry0;
    h0 -= carry0 << 26;
    carry2 = (h2 + (int64_t)(1 << 25)) >> 26;
    h3 += carry2;
    h2 -= carry2 << 26;
    carry4 = (h4 + (int64_t)(1 << 25)) >> 26;
    h5 += carry4;
    h4 -= carry4 << 26;
    carry6 = (h6 + (int64_t)(1 << 25)) >> 26;
    h7 += carry6;
    h6 -= carry6 << 26;
    carry8 = (h8 + (int64_t)(1 << 25)) >> 26;
    h9 += carry8;
    h8 -= carry8 << 26;

    u[0] = (int32_t)h0;
    u[1] = (int32_t)h1;
    u[2] = (int32_t)h2;
    u[3] = (int32_t)h3;
    u[4] = (int32_t)h4;
    u[5] = (int32_t)h5;
    u[6] = (int32_t)h6;
    u[7] = (int32_t)h7;
    u[8] = (int32_t)h8;
    u[9] = (int32_t)h9;

    /* End fe_frombytes.c */

    fe_sq2(v, u); /* 2 * u^2 */
    fe_1(w);
    fe_add(w, v, w); /* w = 2 * u^2 + 1 */
    fe_sq(x, w); /* w^2 */
    fe_mul(y, fe_ma2, v); /* -2 * A^2 * u^2 */
    fe_add(x, x, y); /* x = w^2 - 2 * A^2 * u^2 */
    fe_divpowm1(r->X, w, x); /* (w / x)^(m + 1) */
    fe_sq(y, r->X);
    fe_mul(x, y, x);
    fe_sub(y, w, x);
    fe_copy(z, fe_ma);
    if (fe_isnonzero(y))
    {
        fe_add(y, w, x);
        if (fe_isnonzero(y))
        {
            goto negative;
        }
        else
        {
            fe_mul(r->X, r->X, fe_fffb1);
        }
    }
    else
    {
        fe_mul(r->X, r->X, fe_fffb2);
    }
    fe_mul(r->X, r->X, u); /* u * sqrt(2 * A * (A + 2) * w / x) */
    fe_mul(z, z, v); /* -2 * A * u^2 */
    sign = 0;
    goto setsign;
negative:
    fe_mul(x, x, fe_sqrtm1);
    fe_sub(y, w, x);
    if (fe_isnonzero(y))
    {
        assert((fe_add(y, w, x), !fe_isnonzero(y)));
        fe_mul(r->X, r->X, fe_fffb3);
    }
    else
    {
        fe_mul(r->X, r->X, fe_fffb4);
    }
    /* r->X = sqrt(A * (A + 2) * w / x) */
    /* z = -A */
    sign = 1;
setsign:
    if (fe_isnegative(r->X) != sign)
    {
        assert(fe_isnonzero(r->X));
        fe_neg(r->X, r->X);
    }
    fe_add(r->Z, z, w);
    fe_sub(r->Y, z, w);
    fe_mul(r->X, r->X, r->Z);
#if !defined(NDEBUG)
    {
        fe check_x, check_y, check_iz, check_v;
        fe_invert(check_iz, r->Z);
        fe_mul(check_x, r->X, check_iz);
        fe_mul(check_y, r->Y, check_iz);
        fe_sq(check_x, check_x);
        fe_sq(check_y, check_y);
        fe_mul(check_v, check_x, check_y);
        fe_mul(check_v, fe_d, check_v);
        fe_add(check_v, check_v, check_x);
        fe_sub(check_v, check_v, check_y);
        fe_1(check_x);
        fe_add(check_v, check_v, check_x);
        assert(!fe_isnonzero(check_v));
    }
#endif
}