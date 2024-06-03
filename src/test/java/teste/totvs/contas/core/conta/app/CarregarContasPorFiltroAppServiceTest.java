package teste.totvs.contas.core.conta.app;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import teste.totvs.contas.core.conta.CarregarContasPorFiltrosUseCase;
import teste.totvs.contas.core.conta.domain.ContaDomainRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CarregarContasPorFiltroAppServiceTest {

    public static final String DESCRICAO = "Conta de luz";

    @InjectMocks
    private CarregarContasPorFiltroAppService carregarContasPorFiltroAppService;

    @Mock
    private ContaDomainRepository contaDomainRepository;

    @Test
    void handle() {
        CarregarContasPorFiltrosUseCase.CarregarContasPorFiltroCommand carregarContasPorFiltroCommand = new CarregarContasPorFiltrosUseCase.CarregarContasPorFiltroCommand(
                LocalDateTime.now(), LocalDateTime.now(), DESCRICAO, PageRequest.of(0, 10)
        );

        assertDoesNotThrow(() -> this.carregarContasPorFiltroAppService.handle(carregarContasPorFiltroCommand));
    }


}