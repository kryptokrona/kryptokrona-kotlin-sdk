// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#include "ge_p2_0.h"

void ge_p2_0(ge_p2 *h)
{
    fe_0(h->X);
    fe_1(h->Y);
    fe_1(h->Z);
}