// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#ifndef ED25519_FE_INVERT_H
#define ED25519_FE_INVERT_H

#include "fe.h"
#include "fe_mul.h"
#include "fe_sq.h"

void fe_invert(fe out, const fe z);

#endif // ED25519_FE_INVERT_H
