// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#ifndef ED25519_GE_TOBYTES_H
#define ED25519_GE_TOBYTES_H

#include "fe_invert.h"
#include "fe_isnegative.h"
#include "fe_mul.h"
#include "fe_tobytes.h"
#include "ge.h"

void ge_tobytes(unsigned char *s, const ge_p2 *h);

#endif // ED25519_GE_TOBYTES_H
