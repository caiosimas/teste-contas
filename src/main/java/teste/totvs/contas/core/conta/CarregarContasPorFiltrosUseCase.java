package teste.totvs.contas.core.conta;

import org.springframework.data.domain.Pageable;
import teste.totvs.contas.core.conta.domain.enums.SituacaoContaEnum;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface CarregarContasPorFiltrosUseCase {

    List<ContaProjection> handle(CarregarContasPorFiltroCommand carregarContasPorFiltroCommand);

    record CarregarContasPorFiltroCommand(
            LocalDateTime dataVencimentoInicial,
            LocalDateTime dataVencimentoFinal,
            String descricao,
            Pageable pageable
    ) {
    }

    interface ContaProjection {
        UUID getId();
        LocalDateTime getDataVencimento();
        LocalDateTime getDataPagamento();
        String getDescricao();
        SituacaoContaEnum getSituacaoConta();
        Double getValor();
    }
}
