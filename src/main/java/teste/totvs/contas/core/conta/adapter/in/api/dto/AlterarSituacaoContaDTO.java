package teste.totvs.contas.core.conta.adapter.in.api.dto;

import teste.totvs.contas.core.conta.domain.enums.SituacaoContaEnum;

import java.util.UUID;

public record AlterarSituacaoContaDTO(
        UUID id,
        SituacaoContaEnum situacaoContaEnum
) {
}
