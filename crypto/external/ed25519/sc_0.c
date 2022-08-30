// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#include "sc_0.h"

void sc_0(unsigned char *s)
{
    int i;
    for (i = 0; i < 32; i++)
    {
        s[i] = 0;
    }
}