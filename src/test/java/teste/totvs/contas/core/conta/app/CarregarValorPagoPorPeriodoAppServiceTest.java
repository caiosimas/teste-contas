package teste.totvs.contas.core.conta.app;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import teste.totvs.contas.core.conta.CarregarValorPagoPorPeriodoUseCase;
import teste.totvs.contas.core.conta.domain.ContaDomainRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CarregarValorPagoPorPeriodoAppServiceTest {

    @InjectMocks
    private CarregarValorPagoPorPeriodoAppService carregarValorPagoPorPeriodoAppService;

    @Mock
    private ContaDomainRepository contaDomainRepository;

    @Test
    void handle() {
        CarregarValorPagoPorPeriodoUseCase.CarregarValorPorPeriodoCommand carregarValorPorPeriodoCommand = new CarregarValorPagoPorPeriodoUseCase.CarregarValorPorPeriodoCommand(
                LocalDateTime.now(), LocalDateTime.now()
        );

        assertDoesNotThrow(() -> this.carregarValorPagoPorPeriodoAppService.handle(carregarValorPorPeriodoCommand));
    }

}