// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#include "load_4.h"

uint64_t load_4(const unsigned char *in)
{
    uint64_t result;
    result = (uint64_t)in[0];
    result |= ((uint64_t)in[1]) << 8;
    result |= ((uint64_t)in[2]) << 16;
    result |= ((uint64_t)in[3]) << 24;
    return result;
}