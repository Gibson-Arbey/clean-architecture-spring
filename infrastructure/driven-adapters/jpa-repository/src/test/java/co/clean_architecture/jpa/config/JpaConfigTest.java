package co.clean_architecture.jpa.config;

import org.springframework.core.env.Environment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class JpaConfigTest {


    @Mock
    DataSource dataSource;

    private DBSecret dbSecretUnderTest;
    private JpaConfig jpaConfigUnderTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        jpaConfigUnderTest = new JpaConfig();

        dbSecretUnderTest = DBSecret.builder()
                .password("sa")
                .username("sa")
                .url("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")
                .build();

    }

}
