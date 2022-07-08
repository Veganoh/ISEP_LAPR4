#ifndef CONFIG
#define CONFIG
    #include "config.h"
#endif

#ifndef CONFIG_COM
#define CONFIG_COM
    #include "communication_config.h"
#endif

#ifndef PACKET_UTILS
#define PACKET_UTILS
    unsigned char* serialize_int(unsigned char *buffer, int value);

    unsigned char* deserialize_int(unsigned char *buffer, int *value);

    unsigned char* serialize_char(unsigned char *buffer, unsigned char value);

    unsigned char* deserialize_char(unsigned char *buffer, unsigned char *value);

    unsigned char* serialize_connection_packet(unsigned char *buffer, connection_packet *packet);

    unsigned char* deserialize_connection_packet(unsigned char *buffer, connection_packet *packet);

    unsigned char* serialize_status(unsigned char *buffer, Status *status);

    int string_size(char* str);
#endif