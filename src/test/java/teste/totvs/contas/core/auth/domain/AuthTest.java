package teste.totvs.contas.core.auth.domain;

import org.junit.jupiter.api.Test;
import teste.totvs.contas.core.auth.RegistrarUseCase;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthTest {

    public static final String LOGIN = "caio";
    public static final String PASSWORD = "123";

    @Test
    void registrarAuth() {
        RegistrarUseCase.RegistrarCommand registrarCommand = new RegistrarUseCase.RegistrarCommand(LOGIN, PASSWORD);
        Auth auth = Auth.registrarAuth(registrarCommand);

        assertAll("Verifica se todos os atributos foram relacionados com sucesso",
                () -> assertEquals(LOGIN, auth.getUsername()));
    }

}