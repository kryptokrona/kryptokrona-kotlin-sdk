// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#ifndef ED25519_GE_P3_DBL_H
#define ED25519_GE_P3_DBL_H

#include "ge.h"
#include "ge_p2_dbl.h"
#include "ge_p3_to_p2.h"

void ge_p3_dbl(ge_p1p1 *r, const ge_p3 *p);

#endif // ED25519_GE_P3_DBL_H
