// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#include "ge_scalarmult.h"

/* Assumes that a[31] <= 127 */
void ge_scalarmult(ge_p2 *r, const unsigned char *a, const ge_p3 *A)
{
    signed char e[64];
    int carry, carry2, i;
    ge_cached Ai[8]; /* 1 * A, 2 * A, ..., 8 * A */
    ge_p1p1 t;
    ge_p3 u;

    carry = 0; /* 0..1 */
    for (i = 0; i < 31; i++)
    {
        carry += a[i]; /* 0..256 */
        carry2 = (carry + 8) >> 4; /* 0..16 */
        e[2 * i] = carry - (carry2 << 4); /* -8..7 */
        carry = (carry2 + 8) >> 4; /* 0..1 */
        e[2 * i + 1] = carry2 - (carry << 4); /* -8..7 */
    }
    carry += a[31]; /* 0..128 */
    carry2 = (carry + 8) >> 4; /* 0..8 */
    e[62] = carry - (carry2 << 4); /* -8..7 */
    e[63] = carry2; /* 0..8 */

    ge_p3_to_cached(&Ai[0], A);
    for (i = 0; i < 7; i++)
    {
        ge_add(&t, A, &Ai[i]);
        ge_p1p1_to_p3(&u, &t);
        ge_p3_to_cached(&Ai[i + 1], &u);
    }

    ge_p2_0(r);
    for (i = 63; i >= 0; i--)
    {
        signed char b = e[i];
        unsigned char bnegative = negative(b);
        unsigned char babs = b - (((-bnegative) & b) << 1);
        ge_cached cur, minuscur;
        ge_p2_dbl(&t, r);
        ge_p1p1_to_p2(r, &t);
        ge_p2_dbl(&t, r);
        ge_p1p1_to_p2(r, &t);
        ge_p2_dbl(&t, r);
        ge_p1p1_to_p2(r, &t);
        ge_p2_dbl(&t, r);
        ge_p1p1_to_p3(&u, &t);
        ge_cached_0(&cur);
        ge_cached_cmov(&cur, &Ai[0], equal(babs, 1));
        ge_cached_cmov(&cur, &Ai[1], equal(babs, 2));
        ge_cached_cmov(&cur, &Ai[2], equal(babs, 3));
        ge_cached_cmov(&cur, &Ai[3], equal(babs, 4));
        ge_cached_cmov(&cur, &Ai[4], equal(babs, 5));
        ge_cached_cmov(&cur, &Ai[5], equal(babs, 6));
        ge_cached_cmov(&cur, &Ai[6], equal(babs, 7));
        ge_cached_cmov(&cur, &Ai[7], equal(babs, 8));
        fe_copy(minuscur.YplusX, cur.YminusX);
        fe_copy(minuscur.YminusX, cur.YplusX);
        fe_copy(minuscur.Z, cur.Z);
        fe_neg(minuscur.T2d, cur.T2d);
        ge_cached_cmov(&cur, &minuscur, bnegative);
        ge_add(&t, &u, &cur);
        ge_p1p1_to_p2(r, &t);
    }
}