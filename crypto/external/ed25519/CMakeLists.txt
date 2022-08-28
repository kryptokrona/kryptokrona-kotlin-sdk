cmake_minimum_required(VERSION 2.6)

find_program(CCACHE_PROGRAM ccache)
if(CCACHE_PROGRAM)
    message(STATUS "-- ED25519: Found ccache package... Activating...")
    set_property(GLOBAL PROPERTY RULE_LAUNCH_COMPILE "${CCACHE_PROGRAM}")
endif()

project(ed25519 C)

enable_language(C)

## This section describes our general CMake setup options
set_property(GLOBAL PROPERTY USE_FOLDERS ON)
set(CMAKE_SKIP_INSTALL_RULES OFF FORCE)
set(CMAKE_SKIP_PACKAGE_ALL_DEPENDENCY ON FORCE)
set(CMAKE_SUPPRESS_REGENERATION ON)
set(CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} ${CMAKE_CURRENT_SOURCE_DIR}/cmake)

# Enable c++11
set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

# We need to set the label and import it into CMake if it exists
set(LABEL "")
if(DEFINED ENV{LABEL})
  set(LABEL $ENV{LABEL})
  message(STATUS "Found LABEL: ${LABEL}")
endif()

## We only build static binaries -- this is left here for our dependencies
set(STATIC ON CACHE BOOL FORCE "Link libraries statically? Forced to ON")
add_definitions(-DSTATICLIB)

if(NOT MSVC)
  set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -std=c11 -Wno-c99-compat")
endif()

set(ED25119_SRC
  equal.c
  fe_0.c
  fe_1.c
  fe_add.c
  fe_cmov.c
  fe_copy.c
  fe_divpowm1.c
  fe_frombytes.c
  fe_invert.c
  fe_isnegative.c
  fe_isnonzero.c
  fe_mul.c
  fe_neg.c
  fe_sq.c
  fe_sq2.c
  fe_sub.c
  fe_tobytes.c
  ge_add.c
  ge_cached_0.c
  ge_cached_cmov.c
  ge_check_subgroup_precomp_vartime.c
  ge_double_scalarmult_base_vartime.c
  ge_double_scalarmult_precomp_vartime.c
  ge_dsm_precomp.c
  ge_frombytes_vartime.c
  ge_fromfe_frombytes_vartime.c
  ge_madd.c
  ge_msub.c
  ge_mul8.c
  ge_p1p1_to_p2.c
  ge_p1p1_to_p3.c
  ge_p2_0.c
  ge_p2_dbl.c
  ge_p3_0.c
  ge_p3_dbl.c
  ge_p3_to_cached.c
  ge_p3_to_p2.c
  ge_p3_tobytes.c
  ge_precomp_0.c
  ge_precomp_cmov.c
  ge_scalarmult.c
  ge_scalarmult_base.c
  ge_sub.c
  ge_tobytes.c
  load_3.c
  load_4.c
  negative.c
  sc_0.c
  sc_add.c
  sc_check.c
  sc_isnonzero.c
  sc_mul.c
  sc_muladd.c
  sc_mulsub.c
  sc_reduce.c
  sc_reduce32.c
  sc_sub.c
  signum.c
  slide.c
)

add_library(ed25519 STATIC ${ED25119_SRC})

set_property(TARGET ed25519 PROPERTY C_STANDARD 90)
set_property(TARGET ed25519 PROPERTY SOVERSION 1)