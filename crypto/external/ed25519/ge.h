// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#ifndef ED25519_GE_H
#define ED25519_GE_H

#include "fe.h"

typedef struct
{
    fe X;
    fe Y;
    fe Z;
} ge_p2;

typedef struct
{
    fe X;
    fe Y;
    fe Z;
    fe T;
} ge_p3;

typedef struct
{
    fe X;
    fe Y;
    fe Z;
    fe T;
} ge_p1p1;

typedef struct
{
    fe yplusx;
    fe yminusx;
    fe xy2d;
} ge_precomp;

typedef struct
{
    fe YplusX;
    fe YminusX;
    fe Z;
    fe T2d;
} ge_cached;

typedef ge_cached ge_dsmp[8];

#endif // ED25519_GE_H
