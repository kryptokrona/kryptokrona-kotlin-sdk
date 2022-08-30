// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#ifndef ED25519_GE_DSM_PRECOMP_H
#define ED25519_GE_DSM_PRECOMP_H

#include "ge.h"
#include "ge_add.h"
#include "ge_p1p1_to_p3.h"
#include "ge_p3_dbl.h"
#include "ge_p3_to_cached.h"

void ge_dsm_precomp(ge_dsmp r, const ge_p3 *s);

#endif // ED25519_GE_DSM_PRECOMP_H
