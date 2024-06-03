package teste.totvs.contas.core.auth.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import teste.totvs.contas.core.auth.RegistrarUseCase;

import java.util.UUID;

@Entity
@Table(name = "AUTH")
public class Auth {

    @Id
    private UUID id;

    private String username;
    private String password;

    public static Auth registrarAuth(RegistrarUseCase.RegistrarCommand registrarCommand) {
        Auth auth = new Auth();
        auth.id = UUID.randomUUID();
        auth.username = registrarCommand.login();

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.password = encoder.encode(registrarCommand.senha());

        return auth;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
