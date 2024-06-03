package teste.totvs.contas.core.conta.adapter.in.api.dto;

import teste.totvs.contas.core.conta.domain.enums.SituacaoContaEnum;

import java.time.LocalDateTime;

public record CadastrarContaDTO(

        LocalDateTime dataVencimento,
        LocalDateTime dataPagamento,
        String descricao,
        SituacaoContaEnum situacaoContaEnum,
        Double valor
) {
}
