// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#ifndef ED25519_GE_P3_TO_CACHED_H
#define ED25519_GE_P3_TO_CACHED_H

#include "fe_add.h"
#include "fe_copy.h"
#include "fe_mul.h"
#include "fe_sub.h"
#include "ge.h"

void ge_p3_to_cached(ge_cached *r, const ge_p3 *p);

#endif // ED25519_GE_P3_TO_CACHED_H
