package teste.totvs.contas.core.conta.app;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import teste.totvs.contas.core.conta.AtualizarContaUseCase;
import teste.totvs.contas.core.conta.domain.ContaDomainRepository;
import teste.totvs.contas.core.conta.domain.enums.SituacaoContaEnum;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AtualizarContaAppServiceTest {

    public static final LocalDateTime DATA_VENCIMENTO = LocalDateTime.now();
    public static final LocalDateTime DATA_PAGAMENTO = LocalDateTime.now();
    public static final String DESCRICAO = "Conta de luz";
    public static final SituacaoContaEnum SITUACAO_CONTA = SituacaoContaEnum.ABERTA;
    public static final Double VALOR = 137.80;

    @InjectMocks
    private AtualizarContaAppService atualizarContaAppService;

    @Mock
    private ContaDomainRepository contaDomainRepository;

    @Test
    void handle() {
        AtualizarContaUseCase.AtualizarContaCommand atualizarContaCommand = new AtualizarContaUseCase.AtualizarContaCommand(
                UUID.randomUUID(), DATA_VENCIMENTO, DATA_PAGAMENTO, DESCRICAO, SITUACAO_CONTA, VALOR
        );

        assertDoesNotThrow(() -> this.atualizarContaAppService.handle(atualizarContaCommand));
    }

}