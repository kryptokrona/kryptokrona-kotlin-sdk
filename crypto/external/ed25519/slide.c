// Copyright (c) 2012-2017, The CryptoNote Developers, The Bytecoin Developers
// Copyright (c) 2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#include "slide.h"

void slide(signed char *r, const unsigned char *a)
{
    int i;
    int b;
    int k;

    for (i = 0; i < 256; ++i)
    {
        r[i] = 1 & (a[i >> 3] >> (i & 7));
    }

    for (i = 0; i < 256; ++i)
    {
        if (r[i])
        {
            for (b = 1; b <= 6 && i + b < 256; ++b)
            {
                if (r[i + b])
                {
                    if (r[i] + (r[i + b] << b) <= 15)
                    {
                        r[i] += r[i + b] << b;
                        r[i + b] = 0;
                    }
                    else if (r[i] - (r[i + b] << b) >= -15)
                    {
                        r[i] -= r[i + b] << b;
                        for (k = i + b; k < 256; ++k)
                        {
                            if (!r[k])
                            {
                                r[k] = 1;
                                break;
                            }
                            r[k] = 0;
                        }
                    }
                    else
                        break;
                }
            }
        }
    }
}