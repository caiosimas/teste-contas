package teste.totvs.contas.core.auth.app;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import teste.totvs.contas.core.auth.AutenticarUseCase;
import teste.totvs.contas.core.auth.domain.AuthDomainRepository;
import teste.totvs.contas.core.infra.JwtAppService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AutenticarAppServiceTest {

    public static final String LOGIN = "caio";
    public static final String PASSWORD = "123";

    @InjectMocks
    private AutenticarAppService autenticarAppService;

    @Mock
    private AuthDomainRepository authDomainRepository;

    @Mock
    private JwtAppService jwtAppService;

    @Test
    void handle() {
        AutenticarUseCase.AutenticarCommand autenticarCommand = new AutenticarUseCase.AutenticarCommand(
                LOGIN, PASSWORD
        );

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        Mockito.when(this.authDomainRepository.findByLoginOrThrowNotFound(LOGIN)).thenReturn(new AutenticarUseCase.AuthProjection() {
            @Override
            public UUID getId() {
                return UUID.randomUUID();
            }

            @Override
            public String getUsername() {
                return LOGIN;
            }

            @Override
            public String getPassword() {
                return encoder.encode(PASSWORD);
            }
        });

        assertDoesNotThrow(() -> this.autenticarAppService.handle(autenticarCommand));
    }

    @Test
    void loadUserByUsername() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        Mockito.when(this.authDomainRepository.findByLoginOrThrowNotFound(LOGIN)).thenReturn(new AutenticarUseCase.AuthProjection() {
            @Override
            public UUID getId() {
                return UUID.randomUUID();
            }

            @Override
            public String getUsername() {
                return LOGIN;
            }

            @Override
            public String getPassword() {
                return encoder.encode(PASSWORD);
            }
        });

        assertDoesNotThrow(() -> this.autenticarAppService.loadUserByUsername(LOGIN));
    }
}