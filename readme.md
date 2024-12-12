# what
2 parts are in this demo
1. how to customize a required action in Keycloak.
2. how to extend Keycloak API

# custome required action
what is a required action?  
as a Keycloak admin you can require a user to do some actions in the log in flow or in the registration flow,  
for instance: ask a user to update password etc..  
how to customize a required action?  
1. implement the interface RequiredActionProvider and the interface RequiredActionFactory  
2. create a template like the ring-bell-action.ftl, it is a apache freeMarker template
3. create the java SPI configuration file under resources/META-INF or use google autoService

# extend Keycloak API
see example in UserResourceProvider.java  

# how to deploy custom extensions
1. mvn clean package
2. download keycloak zip file, https://www.keycloak.org/downloads, and unzip it
3. copy the package under target/xxxYourCustomExtension.jar to yourKeycloakHome/providers
4. run keycloakHome/bin/kc.sh/bat build
5. run kc.bat start-dev --http-port 8088 --proxy edge --hostname-strict=false --spi-theme-static-max-age=-1 --spi-theme-cache-themes=false --spi-theme-cache-templates=false --log="console,file"
6. http://localhost:8088, initial username: admin, password: admin
7. set the custom required action both "Enabled" and "Set as default action" on, in the keycloak admin console/Authentication/Required actions.  
![](/img.png)
