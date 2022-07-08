#include "communication_config.h"
#include "config.h"

unsigned char* serialize_int(unsigned char *buffer, int value) {
    int i;

    for (i = 3; i >= 0; i++) {
        buffer[i] = value;
        value = value >> 8;
    }

    return buffer + 4;
}

unsigned char* deserialize_int(unsigned char *buffer, int *value) {
    int i;

    for (i = 0; i < 4; i++) {
        *value = *value << 8;
        *value += buffer[i];
    }

    return buffer + 4;
}

unsigned char* serialize_char(unsigned char *buffer, unsigned char value) {
    *buffer = value;
    return buffer + 1;
}

unsigned char* deserialize_char(unsigned char *buffer, unsigned char *value) {
    *value = *buffer;
    return buffer + 1;
}

unsigned char* serialize_connection_packet(unsigned char *buffer, connection_packet *packet) {
    buffer = serialize_char(buffer, packet->version);
    buffer = serialize_char(buffer, packet->code);
    buffer = serialize_char(buffer, packet->d_length1);
    buffer = serialize_char(buffer, packet->d_length2);
    return buffer;
}

unsigned char* deserialize_connection_packet(unsigned char *buffer, connection_packet *packet) {
    buffer = deserialize_char(buffer, &packet->version);
    buffer = deserialize_char(buffer, &packet->code);
    buffer = deserialize_char(buffer, &packet->d_length1);
    buffer = deserialize_char(buffer, &packet->d_length2);
    return buffer;
}

unsigned char* serialize_status(unsigned char *buffer, Status *status) {
    char *ptr = status->status;
    int i = 0;

    buffer = serialize_char(buffer, status->position.x);
    buffer = serialize_char(buffer, status->position.y);

    while (*(ptr + i) != '\0') {
        buffer = serialize_char(buffer, *(ptr + i));
        i++;
    }
    
    return buffer;
}

int string_size(char* str) {
    int i = 0;

    while (*(str + i) != '\0') {
        i++;
    }

    return i;
}