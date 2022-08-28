{
  "variables": {
    "v8_enable_pointer_compression": "false",
    "v8_enable_31bit_smis_on_64bit_arch": "false"
  },
  "targets": [
    {
      "target_name": "turtlecoin-crypto",
      "defines": [
        "NDEBUG",
        "NO_CRYPTO_EXPORTS",
        "FORCE_USE_HEAP",
        "NO_AES"
      ],
      "include_dirs": [
        "include",
        "<!(node -e \"require('nan')\")",
        "external/argon2/include",
        "external/argon2/lib",
        "external/ed25519"
      ],
      "sources": [
        "src/aesb.c",
        "src/blake256.c",
        "src/chacha8.cpp",
        "src/crypto.cpp",
        "src/groestl.c",
        "src/hash.c",
        "src/hash-extra-blake.c",
        "src/hash-extra-groestl.c",
        "src/hash-extra-jh.c",
        "src/hash-extra-skein.c",
        "src/jh.c",
        "src/keccak.c",
        "src/multisig.cpp",
        "src/oaes_lib.c",
        "src/random.cpp",
        "src/skein.c",
        "src/slow-hash-arm.c",
        "src/slow-hash-x86.c",
        "src/slow-hash-portable.c",
        "src/StringTools.cpp",
        "src/tree-hash.c",
        "external/argon2/lib/argon2.c",
        "external/argon2/arch/generic/lib/argon2-arch.c",
        "external/argon2/lib/core.c",
        "external/argon2/lib/encoding.c",
        "external/argon2/lib/genkat.c",
        "external/argon2/lib/impl-select.c",
        "external/argon2/lib/thread.c",
        "external/argon2/lib/blake2/blake2.c",
        "external/ed25519/equal.c",
        "external/ed25519/fe_0.c",
        "external/ed25519/fe_1.c",
        "external/ed25519/fe_add.c",
        "external/ed25519/fe_cmov.c",
        "external/ed25519/fe_copy.c",
        "external/ed25519/fe_divpowm1.c",
        "external/ed25519/fe_frombytes.c",
        "external/ed25519/fe_invert.c",
        "external/ed25519/fe_isnegative.c",
        "external/ed25519/fe_isnonzero.c",
        "external/ed25519/fe_mul.c",
        "external/ed25519/fe_neg.c",
        "external/ed25519/fe_sq.c",
        "external/ed25519/fe_sq2.c",
        "external/ed25519/fe_sub.c",
        "external/ed25519/fe_tobytes.c",
        "external/ed25519/ge_add.c",
        "external/ed25519/ge_cached_0.c",
        "external/ed25519/ge_cached_cmov.c",
        "external/ed25519/ge_check_subgroup_precomp_vartime.c",
        "external/ed25519/ge_double_scalarmult_base_vartime.c",
        "external/ed25519/ge_double_scalarmult_precomp_vartime.c",
        "external/ed25519/ge_dsm_precomp.c",
        "external/ed25519/ge_frombytes_vartime.c",
        "external/ed25519/ge_fromfe_frombytes_vartime.c",
        "external/ed25519/ge_madd.c",
        "external/ed25519/ge_msub.c",
        "external/ed25519/ge_mul8.c",
        "external/ed25519/ge_p1p1_to_p2.c",
        "external/ed25519/ge_p1p1_to_p3.c",
        "external/ed25519/ge_p2_0.c",
        "external/ed25519/ge_p2_dbl.c",
        "external/ed25519/ge_p3_0.c",
        "external/ed25519/ge_p3_dbl.c",
        "external/ed25519/ge_p3_to_cached.c",
        "external/ed25519/ge_p3_to_p2.c",
        "external/ed25519/ge_p3_tobytes.c",
        "external/ed25519/ge_precomp_0.c",
        "external/ed25519/ge_precomp_cmov.c",
        "external/ed25519/ge_scalarmult.c",
        "external/ed25519/ge_scalarmult_base.c",
        "external/ed25519/ge_sub.c",
        "external/ed25519/ge_tobytes.c",
        "external/ed25519/load_3.c",
        "external/ed25519/load_4.c",
        "external/ed25519/negative.c",
        "external/ed25519/sc_0.c",
        "external/ed25519/sc_add.c",
        "external/ed25519/sc_check.c",
        "external/ed25519/sc_isnonzero.c",
        "external/ed25519/sc_mul.c",
        "external/ed25519/sc_muladd.c",
        "external/ed25519/sc_mulsub.c",
        "external/ed25519/sc_reduce.c",
        "external/ed25519/sc_reduce32.c",
        "external/ed25519/sc_sub.c",
        "external/ed25519/signum.c",
        "external/ed25519/slide.c",
        "src/turtlecoin-crypto.cpp",
        "src/turtlecoin-crypto-node.cpp"
      ],
      "cflags!": [
        "-std=c11",
        "-Wall",
        "-Wextra",
        "-Wpointer-arith",
        "-Wvla",
        "-Wwrite-strings",
        "-Wno-error=extra",
        "-Wno-error=unused-function",
        "-Wno-error=sign-compare",
        "-Wno-error=strict-aliasing",
        "-Wno-error=type-limits",
        "-Wno-error=unused-parameter",
        "-Wno-error=unused-variable",
        "-Wno-error=undef",
        "-Wno-error=uninitialized",
        "-Wno-error=unused-result",
        "-Wlogical-op",
        "-Wno-error=maybe-uninitialized",
        "-Wno-error=clobbered",
        "-Wno-error=unused-but-set-variable",
        "-Waggregate-return",
        "-Wnested-externs",
        "-Wold-style-definition",
        "-Wstrict-prototypes",
        "-fno-exceptions"
      ],
      "cflags_cc!": [
        "-fno-exceptions"
      ],
      "cflags_cc": [
        "-Wall",
        "-Wextra",
        "-Wpointer-arith",
        "-Wvla",
        "-Wwrite-strings",
        "-Wno-error=extra",
        "-Wno-error=unused-function",
        "-Wno-error=sign-compare",
        "-Wno-error=strict-aliasing",
        "-Wno-error=type-limits",
        "-Wno-unused-parameter",
        "-Wno-error=unused-variable",
        "-Wno-error=undef",
        "-Wno-error=uninitialized",
        "-Wno-error=unused-result",
        "-Wlogical-op",
        "-Wno-error=maybe-uninitialized",
        "-Wno-error=clobbered",
        "-Wno-error=unused-but-set-variable",
        "-Wno-reorder",
        "-Wno-missing-field-initializers",
        "-fexceptions"
      ],
      "conditions": [
        [
          'OS=="mac"',
          {
            'xcode_settings': {
              'OTHER_CPLUSPLUSFLAGS': [
                '-stdlib=libc++',
                '-fexceptions',
              ],
              'CLANG_CXX_LIBRARY': 'libc++',
              'CLANG_CXX_LANGUAGE_STANDARD':'c++17',
              'MACOSX_DEPLOYMENT_TARGET': '10.7'
            }
          }
        ],
        [
          "OS=='win'",
          {
            "include_dirs": [
              "src/platform/msc"
            ],
            "configurations": {
              "Release": {
                "msvs_settings": {
                  "VCCLCompilerTool": {
                    "RuntimeLibrary": 0,
                    "Optimization": 3,
                    "FavorSizeOrSpeed": 1,
                    "InlineFunctionExpansion": 2,
                    "WholeProgramOptimization": "true",
                    "OmitFramePointers": "true",
                    "EnableFunctionLevelLinking": "true",
                    "EnableIntrinsicFunctions": "true",
                    "RuntimeTypeInfo": "false",
                    "ExceptionHandling": "0",
                    "AdditionalOptions": [
                      "/EHsc -D_WIN32_WINNT=0x0501 /bigobj /MP /W3 /D_CRT_SECURE_NO_WARNINGS /wd4996 /wd4345 /D_WIN32_WINNT=0x0600 /DWIN32_LEAN_AND_MEAN /DGTEST_HAS_TR1_TUPLE=0 /D_VARIADIC_MAX=8 /D__SSE4_1__"
                    ]
                  },
                  "VCLibrarianTool": {
                    "AdditionalOptions": [
                      "/LTCG"
                    ]
                  },
                  "VCLinkerTool": {
                    "LinkTimeCodeGeneration": 1,
                    "OptimizeReferences": 2,
                    "EnableCOMDATFolding": 2,
                    "LinkIncremental": 1,
                    "AdditionalLibraryDirectories": []
                  }
                }
              }
            }
          }
        ]
      ]
    }
  ]
}
