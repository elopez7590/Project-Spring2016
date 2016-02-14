#/bin/bash

rm -f *.o *.so
gcc -Wall -fPIC -c -I /opt/ibm/java-x86_64-80/include/ -o jnidemo.o jnidemo.c
gcc -shared -Wl,-soname,libjnidemo.so.1 -o libjnidemo.so jnidemo.o
