// Copyright (c) 2019, The TurtleCoin Developers

#include "random.h"

#include <random>

/* Used to obtain a random seed */
static std::random_device random_device;

/* Generator, seeded with the random device */
static std::mt19937 gen(random_device());

/* The distribution to get numbers for - in this case, uint8_t */
static std::uniform_int_distribution<int> distribution {0, std::numeric_limits<uint8_t>::max()};

void generate_random_bytes(size_t n, uint8_t *result)
{
    for (size_t i = 0; i < n; i++)
    {
        result[i] = distribution(gen);
    }
}
