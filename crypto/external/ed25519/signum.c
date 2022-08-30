// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#include "signum.h"

int64_t signum(int64_t a)
{
    return (a >> 63) - ((-a) >> 63);
}