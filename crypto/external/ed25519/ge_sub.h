// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#ifndef ED25519_GE_SUB_H
#define ED25519_GE_SUB_H

#include "fe_add.h"
#include "fe_mul.h"
#include "fe_sub.h"
#include "ge.h"

void ge_sub(ge_p1p1 *r, const ge_p3 *p, const ge_cached *q);

#endif // ED25519_GE_SUB_H
