CC := gcc
CFLAGS := -Wall -fPIC
LDFLAGS := -shared
SRCS := $(wildcard *.c)
OBJS := $(SRCS:.c=.o)
TARGET := build/libcrypto.so

ifeq ($(OS),Windows_NT)
    TARGET := build/crypto.dll
else
    UNAME_S := $(shell uname -s)
    ifeq ($(UNAME_S),Darwin)
        TARGET := build/libcrypto.dylib
    endif
endif

all: $(TARGET)

$(TARGET): $(OBJS)
	@mkdir -p build
	$(CC) $(LDFLAGS) -o $@ $^

%.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

.PHONY: clean
clean:
	rm -rf build
	rm -f $(OBJS)
