package teste.totvs.contas.core.infra;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class JwtAppServiceTest {

    public static final String LOGIN = "caio";
    public static final String PASSWORD = "123";

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @InjectMocks
    private JwtAppService jwtAppService;

    @Test
    void gerarToken() {
        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public String getPassword() {
                return encoder.encode(PASSWORD);
            }

            @Override
            public String getUsername() {
                return UUID.randomUUID().toString();
            }
        };

        String token = this.jwtAppService.gerarToken(userDetails);

        assertNotNull(token);
    }

    @Test
    void tokenValido() {
        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public String getPassword() {
                return encoder.encode(PASSWORD);
            }

            @Override
            public String getUsername() {
                return UUID.randomUUID().toString();
            }
        };

        boolean tokenValido = this.jwtAppService.tokenValido(this.jwtAppService.gerarToken(userDetails));

        assertTrue(tokenValido);
    }

    @Test
    void obterLoginUsuario() {
        String username = UUID.randomUUID().toString();
        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public String getPassword() {
                return encoder.encode(PASSWORD);
            }

            @Override
            public String getUsername() {
                return username;
            }
        };

        String token = this.jwtAppService.gerarToken(userDetails);
        String nomeUsuario = this.jwtAppService.obterLoginUsuario(token);

        assertEquals(username, nomeUsuario);
    }

    @Test
    void obterClaims() {
        String username = UUID.randomUUID().toString();
        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public String getPassword() {
                return encoder.encode(PASSWORD);
            }

            @Override
            public String getUsername() {
                return username;
            }
        };

        String token = this.jwtAppService.gerarToken(userDetails);

        assertDoesNotThrow(() -> JwtAppService.obterClaims(token));
    }
}