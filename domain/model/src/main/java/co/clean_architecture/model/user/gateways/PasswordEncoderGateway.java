package co.clean_architecture.model.user.gateways;

public interface PasswordEncoderGateway {

    String encode(String rawPassword);

    boolean matches(String rawPassword, String encodedPassword);
}
