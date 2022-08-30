// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#include "ge_cached_cmov.h"

void ge_cached_cmov(ge_cached *t, const ge_cached *u, unsigned char b)
{
    fe_cmov(t->YplusX, u->YplusX, b);
    fe_cmov(t->YminusX, u->YminusX, b);
    fe_cmov(t->Z, u->Z, b);
    fe_cmov(t->T2d, u->T2d, b);
}