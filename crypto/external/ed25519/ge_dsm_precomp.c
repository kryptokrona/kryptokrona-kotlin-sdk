// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#include "ge_dsm_precomp.h"

void ge_dsm_precomp(ge_dsmp r, const ge_p3 *s)
{
    ge_p1p1 t;
    ge_p3 s2, u;
    ge_p3_to_cached(&r[0], s);
    ge_p3_dbl(&t, s);
    ge_p1p1_to_p3(&s2, &t);
    ge_add(&t, &s2, &r[0]);
    ge_p1p1_to_p3(&u, &t);
    ge_p3_to_cached(&r[1], &u);
    ge_add(&t, &s2, &r[1]);
    ge_p1p1_to_p3(&u, &t);
    ge_p3_to_cached(&r[2], &u);
    ge_add(&t, &s2, &r[2]);
    ge_p1p1_to_p3(&u, &t);
    ge_p3_to_cached(&r[3], &u);
    ge_add(&t, &s2, &r[3]);
    ge_p1p1_to_p3(&u, &t);
    ge_p3_to_cached(&r[4], &u);
    ge_add(&t, &s2, &r[4]);
    ge_p1p1_to_p3(&u, &t);
    ge_p3_to_cached(&r[5], &u);
    ge_add(&t, &s2, &r[5]);
    ge_p1p1_to_p3(&u, &t);
    ge_p3_to_cached(&r[6], &u);
    ge_add(&t, &s2, &r[6]);
    ge_p1p1_to_p3(&u, &t);
    ge_p3_to_cached(&r[7], &u);
}