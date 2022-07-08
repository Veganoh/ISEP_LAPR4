#ifndef SOCKET_UTILS
#define SOCKET_UTILS
    void write_byte(int sock, unsigned char byte);
    
    void write_int(int sock, int num);

    int read_int(int sock);

    void write_str(int sock, int size, char* str);

    void read_str(int sock, int size, char* str);

    char read_byte(int sock);
#endif