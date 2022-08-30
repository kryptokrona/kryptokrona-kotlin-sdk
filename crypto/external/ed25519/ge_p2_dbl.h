// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#ifndef ED25519_GE_P2_DBL_H
#define ED25519_GE_P2_DBL_H

#include "fe_add.h"
#include "fe_sq.h"
#include "fe_sq2.h"
#include "fe_sub.h"
#include "ge.h"

void ge_p2_dbl(ge_p1p1 *r, const ge_p2 *p);

#endif // ED25519_GE_P2_DBL_H
