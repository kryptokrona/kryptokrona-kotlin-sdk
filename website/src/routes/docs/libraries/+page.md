---
title: Libraries
---

Our libraries that are being built in this SDK is for various purposes. Here we are going to get into what they do and what you can expect from each. We are trying to make these libraries as loosly coupled as possible so one can choose to use one, two or multiple ones and is not required to include all into the project.

## kryptokrona-crypto

This is our cryptographic library that are used in most other libraries with exception of kryptokrona-util. It contains external functions to our C library for doing things like generating key derivation, key images etc.

## kryptokrona-node

This is the library to use if you want to communicate to a Kryptokrona node and send/recieve data.

## kryptokrona-service

This is the library to use if you want to communicate to a Kryptokrona Service. Today this is mostly used for mining pools.

## kryptokrona-util

This library contains models or other object files that are shared across many libraries that does not have any special functionality. Currently, mostly data classes that can store data.

## kryptokrona-wallet

This is the library for creating a wallet as well as the usage around it.

## kryptokrona-walletapi

This is the library to use if you want to communicate to a Kryptokrona Wallet API, if so the user whishes to do so.

## playground

This is not a published library, this is only for demonstrating the usage of the SDK. As the name suggest, just play around with it and see what it possible!
