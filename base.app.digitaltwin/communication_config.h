 #ifndef CONFIG_COM
 #define CONFIG_COM
    #include <sys/socket.h>
    #include <netinet/in.h>
    #include <arpa/inet.h>
    #include <netdb.h>

    #include <openssl/crypto.h>
    #include <openssl/ssl.h>
    #include <openssl/err.h>
    #include <openssl/conf.h>
    #include <openssl/x509.h>
    #include <openssl/bio.h>

    #include "cJson/cJSON.h"

    #define BUF_SIZE 300

    #define MANAGER_PORT "9304"
    #define MANAGER_SSL_CERT_FILE "../sslkeys/agv_manager_J.pem"
    #define MANAGER_IP "172.25.144.1"

    #define DIGITAL_PORT "9203"
    #define DIGITAL_CLIENTS_SSL_CERTS_FILE "../sslkeys/agv_manager_J.pem"
    #define DIGITAL_SSL_CERT_FILE "../sslkeys/digital_twin_J.pem"
    #define DIGITAL_SSL_KEY_FILE "../sslkeys/digital_twin_J.key"

    #define COMMTEST 0
    #define DISCONN 1
    #define ACK 2
    #define TASK_FINISH 3
    #define TASK_ASSIGN 5
    #define DIGITAL_TWIN_REQUEST 7
    #define DIGITAL_TWIN_ASSIGN 8
    #define STATUS 9

    typedef struct {
        unsigned char version;
        unsigned char code;
        unsigned char d_length1;
        unsigned char d_length2;
    } connection_packet;

#endif





