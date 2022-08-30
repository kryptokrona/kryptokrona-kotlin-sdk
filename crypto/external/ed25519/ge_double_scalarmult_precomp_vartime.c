// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#include "ge_double_scalarmult_precomp_vartime.h"

void ge_double_scalarmult_precomp_vartime(
    ge_p2 *r,
    const unsigned char *a,
    const ge_p3 *A,
    const unsigned char *b,
    const ge_dsmp Bi)
{
    signed char aslide[256];
    signed char bslide[256];
    ge_dsmp Ai; /* A, 3A, 5A, 7A, 9A, 11A, 13A, 15A */
    ge_p1p1 t;
    ge_p3 u;
    int i;

    slide(aslide, a);
    slide(bslide, b);
    ge_dsm_precomp(Ai, A);

    ge_p2_0(r);

    for (i = 255; i >= 0; --i)
    {
        if (aslide[i] || bslide[i])
            break;
    }

    for (; i >= 0; --i)
    {
        ge_p2_dbl(&t, r);

        if (aslide[i] > 0)
        {
            ge_p1p1_to_p3(&u, &t);
            ge_add(&t, &u, &Ai[aslide[i] / 2]);
        }
        else if (aslide[i] < 0)
        {
            ge_p1p1_to_p3(&u, &t);
            ge_sub(&t, &u, &Ai[(-aslide[i]) / 2]);
        }

        if (bslide[i] > 0)
        {
            ge_p1p1_to_p3(&u, &t);
            ge_add(&t, &u, &Bi[bslide[i] / 2]);
        }
        else if (bslide[i] < 0)
        {
            ge_p1p1_to_p3(&u, &t);
            ge_sub(&t, &u, &Bi[(-bslide[i]) / 2]);
        }

        ge_p1p1_to_p2(r, &t);
    }
}