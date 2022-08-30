// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#ifndef ED25519_GE_P3_TO_P2_H
#define ED25519_GE_P3_TO_P2_H

#include "fe_copy.h"
#include "ge.h"

void ge_p3_to_p2(ge_p2 *r, const ge_p3 *p);

#endif // ED25519_GE_P3_TO_P2_H
