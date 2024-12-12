package suny.keycloak.requiredaction;

import com.google.auto.service.AutoService;
import org.keycloak.Config;
import org.keycloak.authentication.RequiredActionFactory;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

/**
 */
@AutoService(RequiredActionFactory.class)
public class RingBellRequiredActionFactory implements RequiredActionFactory {

	@Override
	public RequiredActionProvider create(KeycloakSession keycloakSession) {
		return new RingBellRequiredAction();
	}

	@Override
	public String getDisplayText() {
		return "Ring the bell at the door";
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
		return RingBellRequiredAction.PROVIDER_ID;
	}

}
