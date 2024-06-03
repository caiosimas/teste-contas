package teste.totvs.contas.core.auth;

import teste.totvs.contas.core.auth.adapter.in.api.dto.RegistrarDTO;

public interface RegistrarUseCase {

    void handle(RegistrarCommand registrarCommand);

    record RegistrarCommand(String login, String senha) {
        public static RegistrarCommand toCommand(RegistrarDTO registrarDTO) {
            return new RegistrarCommand(registrarDTO.login(), registrarDTO.senha());
        }
    }
}
