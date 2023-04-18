CC := gcc
CFLAGS := -Wall -fPIC -Wextra -pedantic -std=gnu11 -O2 -march=native
LDFLAGS :=
SRCS := $(wildcard *.c)
OBJS := $(SRCS:.c=.o)
TARGET := build/libcrypto.so

ifeq ($(OS),Windows_NT)
    CFLAGS += -I"$(JAVA_HOME)\include" -I"$(JAVA_HOME)\include\win32" -I/mingw64/include
    LDFLAGS += -shared
    TARGET := build/crypto.dll
else
    UNAME_S := $(shell uname -s)
    ifeq ($(UNAME_S),Linux)
        CFLAGS += -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux
        LDFLAGS += -shared
        TARGET := build/libcrypto.so
    endif
    ifeq ($(UNAME_S),Darwin)
        CFLAGS += -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/darwin
        LDFLAGS += -dynamiclib
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
