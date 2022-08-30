{
  "variables": {
    "v8_enable_pointer_compression": "false",
    "v8_enable_31bit_smis_on_64bit_arch": "false"
  },
  "targets": [
    {
      "target_name": "kryptokrona-crypto",
      "defines": [
        "NDEBUG",
        "NO_CRYPTO_EXPORTS",
        "FORCE_USE_HEAP",
        "NO_AES"
      ],
      "include_dirs": [
        "crypto/include",
        "<!(node -e \"require('nan')\")",
        "crypto/external/argon2/include",
        "crypto/external/argon2/lib",
        "crypto/external/ed25519"
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
        "crypto/external/argon2/lib/argon2.c",
        "crypto/external/argon2/arch/generic/lib/argon2-arch.c",
        "crypto/external/argon2/lib/core.c",
        "crypto/external/argon2/lib/encoding.c",
        "crypto/external/argon2/lib/genkat.c",
        "crypto/external/argon2/lib/impl-select.c",
        "crypto/external/argon2/lib/thread.c",
        "crypto/external/argon2/lib/blake2/blake2.c",
        "crypto/external/ed25519/equal.c",
        "crypto/external/ed25519/fe_0.c",
        "crypto/external/ed25519/fe_1.c",
        "crypto/external/ed25519/fe_add.c",
        "crypto/external/ed25519/fe_cmov.c",
        "crypto/external/ed25519/fe_copy.c",
        "crypto/external/ed25519/fe_divpowm1.c",
        "crypto/external/ed25519/fe_frombytes.c",
        "crypto/external/ed25519/fe_invert.c",
        "crypto/external/ed25519/fe_isnegative.c",
        "crypto/external/ed25519/fe_isnonzero.c",
        "crypto/external/ed25519/fe_mul.c",
        "crypto/external/ed25519/fe_neg.c",
        "crypto/external/ed25519/fe_sq.c",
        "crypto/external/ed25519/fe_sq2.c",
        "crypto/external/ed25519/fe_sub.c",
        "crypto/external/ed25519/fe_tobytes.c",
        "crypto/external/ed25519/ge_add.c",
        "crypto/external/ed25519/ge_cached_0.c",
        "crypto/external/ed25519/ge_cached_cmov.c",
        "crypto/external/ed25519/ge_check_subgroup_precomp_vartime.c",
        "crypto/external/ed25519/ge_double_scalarmult_base_vartime.c",
        "crypto/external/ed25519/ge_double_scalarmult_precomp_vartime.c",
        "crypto/external/ed25519/ge_dsm_precomp.c",
        "crypto/external/ed25519/ge_frombytes_vartime.c",
        "crypto/external/ed25519/ge_fromfe_frombytes_vartime.c",
        "crypto/external/ed25519/ge_madd.c",
        "crypto/external/ed25519/ge_msub.c",
        "crypto/external/ed25519/ge_mul8.c",
        "crypto/external/ed25519/ge_p1p1_to_p2.c",
        "crypto/external/ed25519/ge_p1p1_to_p3.c",
        "crypto/external/ed25519/ge_p2_0.c",
        "crypto/external/ed25519/ge_p2_dbl.c",
        "crypto/external/ed25519/ge_p3_0.c",
        "crypto/external/ed25519/ge_p3_dbl.c",
        "crypto/external/ed25519/ge_p3_to_cached.c",
        "crypto/external/ed25519/ge_p3_to_p2.c",
        "crypto/external/ed25519/ge_p3_tobytes.c",
        "crypto/external/ed25519/ge_precomp_0.c",
        "crypto/external/ed25519/ge_precomp_cmov.c",
        "crypto/external/ed25519/ge_scalarmult.c",
        "crypto/external/ed25519/ge_scalarmult_base.c",
        "crypto/external/ed25519/ge_sub.c",
        "crypto/external/ed25519/ge_tobytes.c",
        "crypto/external/ed25519/load_3.c",
        "crypto/external/ed25519/load_4.c",
        "crypto/external/ed25519/negative.c",
        "crypto/external/ed25519/sc_0.c",
        "crypto/external/ed25519/sc_add.c",
        "crypto/external/ed25519/sc_check.c",
        "crypto/external/ed25519/sc_isnonzero.c",
        "crypto/external/ed25519/sc_mul.c",
        "crypto/external/ed25519/sc_muladd.c",
        "crypto/external/ed25519/sc_mulsub.c",
        "crypto/external/ed25519/sc_reduce.c",
        "crypto/external/ed25519/sc_reduce32.c",
        "crypto/external/ed25519/sc_sub.c",
        "crypto/external/ed25519/signum.c",
        "crypto/external/ed25519/slide.c",
        "crypto/src/kryptokrona-crypto.cpp",
        "crypto/src/kryptokrona-crypto-node.cpp"
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
              "crypto/src/platform/msc"
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
