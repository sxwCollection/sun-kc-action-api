package suny.keycloak.requiredaction;

import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.keycloak.authentication.InitiatedActionSupport;
import org.keycloak.authentication.RequiredActionContext;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.forms.login.LoginFormsProvider;
import org.keycloak.models.UserModel;
import org.keycloak.models.utils.FormMessage;
import org.keycloak.services.validation.Validation;

import java.util.function.Consumer;

import static org.keycloak.common.util.CollectionUtil.isEmpty;

/**
 */
public class RingBellRequiredAction implements RequiredActionProvider {

	public static final String PROVIDER_ID = "RING_BELL";

	private static final String ATTRIBUTE_NAME = "echo";
	private static final String BE_POLITE = "on";
	protected static final Logger logger = Logger.getLogger(RingBellRequiredAction.class);
	@Override
	public InitiatedActionSupport initiatedActionSupport() {
		return InitiatedActionSupport.SUPPORTED;
	}

	@Override
	public void evaluateTriggers(RequiredActionContext context) {
		logger.debug("in requriedAction: DoorBell");
		MultivaluedMap<String, String> formData = context.getHttpRequest().getDecodedFormParameters();
		String on = formData.getFirst("actrole");
		 if (BE_POLITE.equalsIgnoreCase(on)) {
			 logger.debug("actRole: " + on);
		 	context.getUser().addRequiredAction(PROVIDER_ID);
		 }
	}

	@Override
	public void requiredActionChallenge(RequiredActionContext context) {
		// show initial form
		context.challenge(createForm(context, null));
	}

	@Override
	public void processAction(RequiredActionContext context) {

		UserModel user = context.getUser();
		MultivaluedMap<String, String> formData = context.getHttpRequest().getDecodedFormParameters();
		String inputVal = formData.getFirst(ATTRIBUTE_NAME);

		if (Validation.isBlank(inputVal) || !inputVal.equals("ding")) {
			context.challenge(createForm(context, form -> form.addError(new FormMessage(ATTRIBUTE_NAME, "go away!"))));
			return;
		}
		if (isEmpty(user.getAttributes().get(ATTRIBUTE_NAME))) {
			user.setSingleAttribute(ATTRIBUTE_NAME, "dong");
		}
		user.removeRequiredAction(PROVIDER_ID);

		context.success();
	}

	@Override
	public void close() {
	}

	private Response createForm(RequiredActionContext context, Consumer<LoginFormsProvider> formConsumer) {
		LoginFormsProvider form = context.form();
		form.setAttribute("username", context.getUser().getUsername());

		String attriVal = context.getUser().getFirstAttribute(ATTRIBUTE_NAME);
		form.setAttribute(ATTRIBUTE_NAME, attriVal == null ? "" : attriVal);

		if (formConsumer != null) {
			formConsumer.accept(form);
		}

		return form.createForm("ring-bell-action.ftl");
	}

}
