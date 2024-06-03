package teste.totvs.contas.core.conta.app;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import teste.totvs.contas.core.conta.CarregarContaPorIdUseCase;
import teste.totvs.contas.core.conta.domain.ContaDomainRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CarregarContaPorIdAppServiceTest {

    @InjectMocks
    private CarregarContaPorIdAppService carregarContaPorIdAppService;

    @Mock
    private ContaDomainRepository contaDomainRepository;

    @Test
    void handle() {
        CarregarContaPorIdUseCase.CarregarContaPorIdCommand carregarContaPorIdCommand = new CarregarContaPorIdUseCase.CarregarContaPorIdCommand(
                UUID.randomUUID()
        );

        assertDoesNotThrow(() -> this.carregarContaPorIdAppService.handle(carregarContaPorIdCommand));
    }
}