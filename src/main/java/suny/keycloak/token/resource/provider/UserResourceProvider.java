package suny.keycloak.token.resource.provider;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.UserModel;
import org.keycloak.services.managers.AppAuthManager;
import org.keycloak.services.managers.AuthenticationManager;
import org.keycloak.services.resource.RealmResourceProvider;

import java.util.Map;

public class UserResourceProvider implements RealmResourceProvider {
    static final String ID = "user-api";
    private KeycloakSession session;
    protected static final Logger logger = Logger.getLogger(UserResourceProvider.class);

    public UserResourceProvider(KeycloakSession session) {
        this.session = session;
    }

    @Override
    public Object getResource() {
        return this;
    }

    @Override
    public void close() {

    }

    @GET
    @Path("hello/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response helloAnonymous(@PathParam("user") String user) {
        final String hi = "Hi " + user;
        logger.info(hi);
        return Response.ok(hi).build();
    }

    @GET
    @Path("create/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(@PathParam("user") String user) {

        checkAuth();
        UserModel userModel = session.users().addUser(session.getContext().getRealm(), user);
//        userModel.setAttribute("test", List.of("testVal1"));
//        userModel.addRequiredAction("UPDATE_PASSWORD");
        userModel.setEnabled(true);
        return Response.ok(Map.of(user, "has been created")).build();
    }

    private AuthenticationManager.AuthResult checkAuth() {
        AuthenticationManager.AuthResult auth = new AppAuthManager.BearerTokenAuthenticator(session).authenticate();
        if (auth == null) {
            throw new NotAuthorizedException("Bearer");
        }
        return auth;
    }
}
