// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#ifndef ED25519_FE_FROMBYTES_H
#define ED25519_FE_FROMBYTES_H

#include "fe.h"
#include "load_3.h"
#include "load_4.h"

void fe_frombytes(fe h, const unsigned char *s);

#endif // ED25519_FE_FROMBYTES_H
