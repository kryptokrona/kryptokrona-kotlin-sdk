// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#ifndef ED25519_GE_DOUBLE_SCALARMULT_BASE_VARTIME_H
#define ED25519_GE_DOUBLE_SCALARMULT_BASE_VARTIME_H

#include "ge.h"
#include "ge_add.h"
#include "ge_dsm_precomp.h"
#include "ge_madd.h"
#include "ge_msub.h"
#include "ge_p1p1_to_p2.h"
#include "ge_p1p1_to_p3.h"
#include "ge_p2_0.h"
#include "ge_p2_dbl.h"
#include "ge_sub.h"
#include "slide.h"

void ge_double_scalarmult_base_vartime(ge_p2 *r, const unsigned char *a, const ge_p3 *A, const unsigned char *b);

#endif // ED25519_GE_DOUBLE_SCALARMULT_BASE_VARTIME_H
