package teste.totvs.contas.core.conta;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface CarregarValorPagoPorPeriodoUseCase {

    Double handle(CarregarValorPorPeriodoCommand carregarValorPorPeriodoCommand);

    record CarregarValorPorPeriodoCommand(
            LocalDateTime dataInicial,
            LocalDateTime dataFinal
    ) {
    }


}
