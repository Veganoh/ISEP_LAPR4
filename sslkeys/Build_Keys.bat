SET PASS=spomsp

FOR %%x IN (agv_manager_J digital_twin_J orders_server_J requests_J orders_J) DO (
    del %%x.pem
    del %%x.jks
    keytool -genkey  -v -alias %%x -dname "CN=G3, OU=DEI, O=ISEP, L=Porto, S=Porto, C=PT" -keyalg RSA -keysize 2048 -validity 365 -keystore %%x.jks -storepass %PASS%
    keytool -exportcert -alias %%x -keystore %%x.jks -storepass %PASS% -rfc -file %%x.pem
    )

del http_server_J.jks
keytool -genkey -v -alias http_server_J -dname "CN=G3, OU=DEI, O=ISEP, L=Porto, S=Porto, C=PT" -keyalg RSA -keysize 2048 -validity 365 -keystore http_server_J.jks -storepass %PASS%

FOR %%x IN (requests_J digital_twin_J) DO (
    keytool -import -noprompt -alias %%x -keystore agv_manager_J.jks -file %%x.pem -storepass %PASS%
    keytool -import -noprompt -alias agv_manager_J -keystore %%x.jks -file agv_manager_J.pem -storepass %PASS%
    )

keytool -import -noprompt -alias orders_J -keystore orders_server_J.jks -file orders_J.pem -storepass %PASS%
keytool -import -noprompt -alias orders_server_J -keystore orders_J.jks -file orders_server_J.pem -storepass %PASS%

FOR %%x IN (agv_manager_J digital_twin_J orders_server_J http_server_J) DO (
    keytool -list -keystore %%x.jks -storepass %PASS%
    )
