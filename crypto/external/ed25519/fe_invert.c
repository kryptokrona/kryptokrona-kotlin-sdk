// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#include "fe_invert.h"

void fe_invert(fe out, const fe z)
{
    fe t0;
    fe t1;
    fe t2;
    fe t3;
    int i;

    fe_sq(t0, z);
    fe_sq(t1, t0);
    fe_sq(t1, t1);
    fe_mul(t1, z, t1);
    fe_mul(t0, t0, t1);
    fe_sq(t2, t0);
    fe_mul(t1, t1, t2);
    fe_sq(t2, t1);
    for (i = 0; i < 4; ++i)
    {
        fe_sq(t2, t2);
    }
    fe_mul(t1, t2, t1);
    fe_sq(t2, t1);
    for (i = 0; i < 9; ++i)
    {
        fe_sq(t2, t2);
    }
    fe_mul(t2, t2, t1);
    fe_sq(t3, t2);
    for (i = 0; i < 19; ++i)
    {
        fe_sq(t3, t3);
    }
    fe_mul(t2, t3, t2);
    fe_sq(t2, t2);
    for (i = 0; i < 9; ++i)
    {
        fe_sq(t2, t2);
    }
    fe_mul(t1, t2, t1);
    fe_sq(t2, t1);
    for (i = 0; i < 49; ++i)
    {
        fe_sq(t2, t2);
    }
    fe_mul(t2, t2, t1);
    fe_sq(t3, t2);
    for (i = 0; i < 99; ++i)
    {
        fe_sq(t3, t3);
    }
    fe_mul(t2, t3, t2);
    fe_sq(t2, t2);
    for (i = 0; i < 49; ++i)
    {
        fe_sq(t2, t2);
    }
    fe_mul(t1, t2, t1);
    fe_sq(t1, t1);
    for (i = 0; i < 4; ++i)
    {
        fe_sq(t1, t1);
    }
    fe_mul(out, t1, t0);

    return;
}