// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#include "fe_divpowm1.h"

void fe_divpowm1(fe r, const fe u, const fe v)
{
    fe v3, uv7, t0, t1, t2;
    int i;

    fe_sq(v3, v);
    fe_mul(v3, v3, v); /* v3 = v^3 */
    fe_sq(uv7, v3);
    fe_mul(uv7, uv7, v);
    fe_mul(uv7, uv7, u); /* uv7 = uv^7 */

    /*fe_pow22523(uv7, uv7);*/

    /* From fe_pow22523.c */

    fe_sq(t0, uv7);
    fe_sq(t1, t0);
    fe_sq(t1, t1);
    fe_mul(t1, uv7, t1);
    fe_mul(t0, t0, t1);
    fe_sq(t0, t0);
    fe_mul(t0, t1, t0);
    fe_sq(t1, t0);
    for (i = 0; i < 4; ++i)
    {
        fe_sq(t1, t1);
    }
    fe_mul(t0, t1, t0);
    fe_sq(t1, t0);
    for (i = 0; i < 9; ++i)
    {
        fe_sq(t1, t1);
    }
    fe_mul(t1, t1, t0);
    fe_sq(t2, t1);
    for (i = 0; i < 19; ++i)
    {
        fe_sq(t2, t2);
    }
    fe_mul(t1, t2, t1);
    for (i = 0; i < 10; ++i)
    {
        fe_sq(t1, t1);
    }
    fe_mul(t0, t1, t0);
    fe_sq(t1, t0);
    for (i = 0; i < 49; ++i)
    {
        fe_sq(t1, t1);
    }
    fe_mul(t1, t1, t0);
    fe_sq(t2, t1);
    for (i = 0; i < 99; ++i)
    {
        fe_sq(t2, t2);
    }
    fe_mul(t1, t2, t1);
    for (i = 0; i < 50; ++i)
    {
        fe_sq(t1, t1);
    }
    fe_mul(t0, t1, t0);
    fe_sq(t0, t0);
    fe_sq(t0, t0);
    fe_mul(t0, t0, uv7);

    /* End fe_pow22523.c */
    /* t0 = (uv^7)^((q-5)/8) */
    fe_mul(t0, t0, v3);
    fe_mul(r, t0, u); /* u^(m+1)v^(-(m+1)) */
}