package teste.totvs.contas.core.conta.app;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import teste.totvs.contas.core.conta.AlterarSituacaoContaUseCase;
import teste.totvs.contas.core.conta.domain.ContaDomainRepository;
import teste.totvs.contas.core.conta.domain.enums.SituacaoContaEnum;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AlterarSituacaoContaAppServiceTest {

    @InjectMocks
    private AlterarSituacaoContaAppService alterarSituacaoContaAppService;

    @Mock
    private ContaDomainRepository contaDomainRepository;

    @Test
    void handle() {
        AlterarSituacaoContaUseCase.AlterarSituacaoContaCommand alterarSituacaoContaCommand = new AlterarSituacaoContaUseCase.AlterarSituacaoContaCommand(
                UUID.randomUUID(), SituacaoContaEnum.CANCELADA
        );

        assertDoesNotThrow(() -> this.alterarSituacaoContaAppService.handle(alterarSituacaoContaCommand));
    }
}