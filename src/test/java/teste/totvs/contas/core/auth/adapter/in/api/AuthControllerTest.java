package teste.totvs.contas.core.auth.adapter.in.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import teste.totvs.contas.core.auth.AutenticarUseCase;
import teste.totvs.contas.core.auth.adapter.in.api.dto.RegistrarDTO;
import teste.totvs.contas.core.auth.domain.AuthDomainRepository;
import teste.totvs.contas.core.conta.adapter.in.api.dto.CadastrarContaDTO;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    public static final String LOGIN = "caio";
    public static final String PASSWORD = "123";

    @Mock
    private AuthDomainRepository repository;

    @Mock
    private AutenticarUseCase autenticarUseCase;

    @Autowired
    private MockMvc mock;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void cadastrar() throws Exception {
        RegistrarDTO registrarDTO = new RegistrarDTO(
                UUID.randomUUID().toString(), PASSWORD
        );

        String dto = mapper.writeValueAsString(registrarDTO);

        mock.perform(post("/auth/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dto))
                .andExpect(status().isOk())
                .andReturn();
    }
}