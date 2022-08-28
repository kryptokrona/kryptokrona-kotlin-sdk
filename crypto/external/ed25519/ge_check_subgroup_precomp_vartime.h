// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#ifndef ED25519_GE_CHECK_SUBGROUP_PRECOMP_VARTIME_H
#define ED25519_GE_CHECK_SUBGROUP_PRECOMP_VARTIME_H

#include "fe_isnonzero.h"
#include "fe_sub.h"
#include "ge.h"
#include "ge_add.h"
#include "ge_p1p1_to_p2.h"
#include "ge_p1p1_to_p3.h"
#include "ge_p2_dbl.h"
#include "ge_p3_0.h"
#include "ge_sub.h"

int ge_check_subgroup_precomp_vartime(const ge_dsmp p);

#endif // ED25519_GE_CHECK_SUBGROUP_PRECOMP_VARTIME_H
