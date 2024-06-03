package teste.totvs.contas.core.conta;

import teste.totvs.contas.core.conta.domain.enums.SituacaoContaEnum;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public interface CarregarContaPorIdUseCase {

    ContaProjection handle(CarregarContaPorIdCommand carregarContaPorIdCommand);

    record CarregarContaPorIdCommand(
            UUID id
    ) {
        public static CarregarContaPorIdCommand toCommand(UUID id) {
            return new CarregarContaPorIdCommand(id);
        }
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
