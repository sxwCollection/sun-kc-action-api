package suny.keycloak.token.resource.provider;

import com.google.auto.service.AutoService;
import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.services.resource.RealmResourceProvider;
import org.keycloak.services.resource.RealmResourceProviderFactory;

@AutoService(RealmResourceProviderFactory.class)
public class UserResourceProviderFactory implements RealmResourceProviderFactory {
    @Override
    public RealmResourceProvider create(KeycloakSession keycloakSession) {
        return new UserResourceProvider(keycloakSession);
    }

    @Override
    public void init(Config.Scope scope) {

    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return UserResourceProvider.ID;
    }
}
