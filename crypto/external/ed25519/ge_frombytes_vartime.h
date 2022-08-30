// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#ifndef ED25519_GE_FROMBYTES_VARTIME_H
#define ED25519_GE_FROMBYTES_VARTIME_H

#include "fe_1.h"
#include "fe_add.h"
#include "fe_divpowm1.h"
#include "fe_frombytes.h"
#include "fe_isnegative.h"
#include "fe_isnonzero.h"
#include "fe_mul.h"
#include "fe_neg.h"
#include "fe_sq.h"
#include "fe_sub.h"
#include "ge.h"
#include "sc.h"

int ge_frombytes_vartime(ge_p3 *h, const unsigned char *s);

#endif // ED25519_GE_FROMBYTES_VARTIME_H
