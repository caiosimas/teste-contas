package teste.totvs.contas.core.conta.adapter.in.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import teste.totvs.contas.core.conta.adapter.in.api.dto.AtualizarContaDTO;
import teste.totvs.contas.core.conta.adapter.in.api.dto.CadastrarContaDTO;
import teste.totvs.contas.core.conta.domain.enums.SituacaoContaEnum;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ContaControllerTest {

    public static final LocalDateTime DATA_VENCIMENTO = LocalDateTime.now();
    public static final LocalDateTime DATA_PAGAMENTO = LocalDateTime.now();
    public static final String DESCRICAO = "Conta de luz";
    public static final SituacaoContaEnum SITUACAO_CONTA = SituacaoContaEnum.ABERTA;
    public static final Double VALOR = 137.80;

    @Autowired
    private MockMvc mock;

    @Autowired
    private ObjectMapper mapper;


    @Test
    void cadastrarConta() throws Exception {
        CadastrarContaDTO cadastrarContaDTO = new CadastrarContaDTO(
            DATA_VENCIMENTO, DATA_PAGAMENTO, DESCRICAO, SITUACAO_CONTA, VALOR
        );

        String dto = this.mapper.writeValueAsString(cadastrarContaDTO);

        mock.perform(post("/ws-api/conta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dto))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void atualizarConta() throws Exception {
        AtualizarContaDTO atualizarContaDTO = new AtualizarContaDTO(
                UUID.randomUUID(), DATA_VENCIMENTO, LocalDateTime.now(), DESCRICAO, SituacaoContaEnum.PAGA, VALOR
        );

        String dto = this.mapper.writeValueAsString(atualizarContaDTO);

        mock.perform(put("/ws-api/conta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dto))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void atualizarSituacaoConta() throws Exception {
        AtualizarContaDTO atualizarContaDTO = new AtualizarContaDTO(
                UUID.randomUUID(), DATA_VENCIMENTO, DATA_PAGAMENTO, DESCRICAO, SituacaoContaEnum.PAGA, VALOR
        );

        String dto = this.mapper.writeValueAsString(atualizarContaDTO);

        mock.perform(patch("/ws-api/conta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dto))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void carregarContaPorId() throws Exception {
        mock.perform(get("/ws-api/conta/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void carregarContas() throws Exception {
        mock.perform(get("/ws-api/conta/carregar-contas?descricao=TESTEA&data-vencimento-final=2024-06-05T12:30:45&data-vencimento-inicial=2024-06-01T12:30:45")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void carregarValorPagoPorPeriodo() throws Exception {
        mock.perform(get("/ws-api/conta/carregar-valor-pago?data-inicial=2024-06-03T12:30:45&data-final=2024-06-06T12:30:45")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}
