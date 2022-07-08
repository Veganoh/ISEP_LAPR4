#include "config.h"

void write_byte(int sock, unsigned char byte) {
    write(sock, &byte, 1);
}

void write_int(int sock, int num) {
    unsigned int n=num;
    unsigned char bt;
    
    for(int i=0;i<4;i++) {
        bt=n%256; write(sock,&bt,1); n=n/256; }
}

int read_int(int sock) {
    unsigned char bt;
    unsigned int num,f;
    
    num=0;f=1;
    for(int i=0;i<4;i++) {
        read(sock,&bt,1); num=num+bt*f; f=256*f;
        }
    return num;
}

void write_str(int sock, int size, char* str) {
    for(int i=0;i<size;i++){
        write(sock, &(str[i]), 1);
    }
}

void read_str(int sock, int size, char* str) {
    char byte;

	for(int i=0;i<size;i++) {
		read(sock, &byte, 1);
		str[i] = byte;
	}
}

char read_byte(int sock) {
    char byte;
    read(sock, &byte, 1);
    return byte;
}