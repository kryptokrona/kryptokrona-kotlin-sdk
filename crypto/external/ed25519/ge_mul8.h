// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#ifndef ED25519_GE_MUL8_H
#define ED25519_GE_MUL8_H

#include "ge.h"
#include "ge_p1p1_to_p2.h"
#include "ge_p2_dbl.h"

void ge_mul8(ge_p1p1 *r, const ge_p2 *t);

#endif // ED25519_GE_MUL8_H
