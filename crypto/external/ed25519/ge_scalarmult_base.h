// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#ifndef ED25519_GE_SCALARMULT_BASE_H
#define ED25519_GE_SCALARMULT_BASE_H

#include "equal.h"
#include "fe_copy.h"
#include "fe_neg.h"
#include "ge.h"
#include "ge_madd.h"
#include "ge_p1p1_to_p2.h"
#include "ge_p1p1_to_p3.h"
#include "ge_p2_dbl.h"
#include "ge_p3_0.h"
#include "ge_p3_dbl.h"
#include "ge_precomp_0.h"
#include "ge_precomp_cmov.h"
#include "negative.h"

void ge_scalarmult_base(ge_p3 *h, const unsigned char *a);

#endif // ED25519_GE_SCALARMULT_BASE_H
