#!/bin/bash

# Set up emscripten

if [[ -z "${EMSDK}" ]]; then
  echo "Installing emscripten..."
  echo ""
  if [[ ! -e ./emsdk/emsdk ]]; then
    git submodule init
    git submodule update
  fi
  cd emsdk && git pull
  ./emsdk install latest && ./emsdk activate latest
  source ./emsdk_env.sh
  cd ..
fi

# This applies a patch to fastcomp to make sure that the
# environment is set correctly for react environments
patch -N --verbose emsdk/fastcomp/emscripten/src/shell.js scripts/emscripten.patch

mkdir -p jsbuild && cd jsbuild && rm -rf *
emconfigure cmake .. -DNO_AES=1 -DARCH=default -DBUILD_WASM=1 -DBUILD_JS=0
make && cp turtlecoin-crypto-wasm.js ../dist
emconfigure cmake .. -DNO_AES=1 -DARCH=default -DBUILD_WASM=0 -DBUILD_JS=1
make && cp turtlecoin-crypto.js ../dist
