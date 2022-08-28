// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#ifndef ED25519_GE_PRECOMP_CMOV_H
#define ED25519_GE_PRECOMP_CMOV_H

#include "fe_cmov.h"
#include "ge.h"

void ge_precomp_cmov(ge_precomp *t, const ge_precomp *u, unsigned char b);

#endif // ED25519_GE_PRECOMP_CMOV_H
