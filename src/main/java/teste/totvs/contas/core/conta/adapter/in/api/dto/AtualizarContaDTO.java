package teste.totvs.contas.core.conta.adapter.in.api.dto;

import teste.totvs.contas.core.conta.domain.enums.SituacaoContaEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public record AtualizarContaDTO(
        UUID id,
        LocalDateTime dataVencimento,
        LocalDateTime dataPagamento,
        String descricao,
        SituacaoContaEnum situacaoContaEnum,
        Double valor
) {
}
