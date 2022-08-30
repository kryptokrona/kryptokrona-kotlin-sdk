// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#include "ge_mul8.h"

void ge_mul8(ge_p1p1 *r, const ge_p2 *t)
{
    ge_p2 u;
    ge_p2_dbl(r, t);
    ge_p1p1_to_p2(&u, r);
    ge_p2_dbl(r, &u);
    ge_p1p1_to_p2(&u, r);
    ge_p2_dbl(r, &u);
}
