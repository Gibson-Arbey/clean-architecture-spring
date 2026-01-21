package co.clean_architecture.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "security")
public class SecurityConstants {

    private String jwtKeyPrivate;
    private String jwtUserGenerator;
}
