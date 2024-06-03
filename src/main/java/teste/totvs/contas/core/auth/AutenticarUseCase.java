package teste.totvs.contas.core.auth;

import org.springframework.security.core.userdetails.UserDetailsService;
import teste.totvs.contas.core.auth.adapter.in.api.dto.AutenticarDTO;

import java.util.UUID;

public interface AutenticarUseCase extends UserDetailsService {

    String handle(AutenticarCommand autenticarCommand);

    record AutenticarCommand(String login, String senha) {
        public static AutenticarCommand toCommand(AutenticarDTO autenticarDTO) {
            return new AutenticarCommand(autenticarDTO.login(), autenticarDTO.senha());
        }
    }

    interface AuthProjection {
        UUID getId();
        String getUsername();
        String getPassword();
    }
}
