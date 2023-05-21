#!/bin/bash
# This script is used to generate the list of function signatures included in the libraries

echo "Generating list of function signatures for libcrypto.dylib:"
echo
(cd ../crypto/build && nm -gU libcrypto.dylib)
echo

echo "Generating list of function signatures for libed25519.dylib:"
echo
(cd ../crypto/build && nm -gU libed25519.dylib)
echo