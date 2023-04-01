
#pragma once

#include <stdint.h>

typedef struct
{
  uint8_t data[32];
} hash_t;

typedef struct
{
  uint8_t data[32];
} public_key_t;

typedef struct
{
  uint8_t data[32];
} secret_key_t;

typedef struct
{
  uint8_t data[32];
} key_derivation_t;

typedef struct
{
  uint8_t data[32];
} key_image_t;

typedef struct
{
  uint8_t data[64];
} signature_t;

// typedef struct
// {
//   uint8_t data[32];
// } elliptic_curve_point_t;

// typedef struct
// {
//   uint8_t data[32];
// } elliptic_curve_scalar_t;

// typedef struct
// {
//   hash_t h;
//   struct
//   {
//     elliptic_curve_point_t a, b;
//   } ab[];
// } rs_comm_t;
