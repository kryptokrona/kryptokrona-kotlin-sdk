# Kryptokrona Java SDK Scripts
Useful scripts for the Kryptokrona Java SDK. 

## Setup Testnet

If you don't want to use a remote node in order to test the implementation you could setup your own testnet. To setup
run the command:

- `./start-testnet.sh`

This will fetch the Docker Image from Docker Hub and orchestrate up a testnet.

If you want to stop the containers:

- `./teardown-testnet.sh`

If you want to remove everything locally with Docker (use with caution):

- `./remove-testnet.sh`

## Contribute

If you come up with some idea of how to improve these scripts please feel free to send a PR by first forking this repo.