package teste.totvs.contas.core.conta;

import teste.totvs.contas.core.conta.adapter.in.api.dto.AlterarSituacaoContaDTO;
import teste.totvs.contas.core.conta.domain.enums.SituacaoContaEnum;

import java.util.UUID;

public interface AlterarSituacaoContaUseCase {

    void handle(AlterarSituacaoContaCommand alterarSituacaoContaCommand);

    record AlterarSituacaoContaCommand(
            UUID id,
            SituacaoContaEnum situacaoConta
    ) {
        public static AlterarSituacaoContaCommand toCommand(AlterarSituacaoContaDTO alterarSituacaoContaDTO) {
            return new AlterarSituacaoContaCommand(alterarSituacaoContaDTO.id(), alterarSituacaoContaDTO.situacaoContaEnum());
        }
    }
}
