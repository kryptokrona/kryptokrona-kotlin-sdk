// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#include "sc_check.h"

int sc_check(const unsigned char *s)
{
    int64_t s0 = load_4(s);
    int64_t s1 = load_4(s + 4);
    int64_t s2 = load_4(s + 8);
    int64_t s3 = load_4(s + 12);
    int64_t s4 = load_4(s + 16);
    int64_t s5 = load_4(s + 20);
    int64_t s6 = load_4(s + 24);
    int64_t s7 = load_4(s + 28);

    return (
        int)((signum(1559614444 - s0) + (signum(1477600026 - s1) << 1) + (signum(2734136534 - s2) << 2) + (signum(350157278 - s3) << 3) + (signum(-s4) << 4) + (signum(-s5) << 5) + (signum(-s6) << 6) + (signum(268435456 - s7) << 7)) >> 8);
}