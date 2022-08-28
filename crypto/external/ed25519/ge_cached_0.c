// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#include "ge_cached_0.h"

void ge_cached_0(ge_cached *r)
{
    fe_1(r->YplusX);
    fe_1(r->YminusX);
    fe_1(r->Z);
    fe_0(r->T2d);
}