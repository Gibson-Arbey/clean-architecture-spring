package co.clean_architecture.model.user.gateways;

public interface AuthenticationGateway {

    void authenticate(String username, String password);

}
