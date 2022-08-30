// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#ifndef ED25519_GE_SCALARMULT_H
#define ED25519_GE_SCALARMULT_H

#include "equal.h"
#include "fe_copy.h"
#include "fe_neg.h"
#include "ge.h"
#include "ge_add.h"
#include "ge_cached_0.h"
#include "ge_cached_cmov.h"
#include "ge_p1p1_to_p2.h"
#include "ge_p1p1_to_p3.h"
#include "ge_p2_0.h"
#include "ge_p2_dbl.h"
#include "ge_p3_to_cached.h"
#include "negative.h"

void ge_scalarmult(ge_p2 *r, const unsigned char *a, const ge_p3 *A);

#endif // ED25519_GE_SCALARMULT_H
