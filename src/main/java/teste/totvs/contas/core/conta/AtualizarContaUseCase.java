package teste.totvs.contas.core.conta;

import org.springframework.stereotype.Component;
import teste.totvs.contas.core.conta.adapter.in.api.dto.AtualizarContaDTO;
import teste.totvs.contas.core.conta.adapter.in.api.dto.CadastrarContaDTO;
import teste.totvs.contas.core.conta.domain.enums.SituacaoContaEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Component
public interface AtualizarContaUseCase {

    void handle(AtualizarContaCommand atualizarContaCommand);

    record AtualizarContaCommand(
            UUID id,
            LocalDateTime dataVencimento,
            LocalDateTime dataPagamento,
            String descricao,
            SituacaoContaEnum situacaoContaEnum,
            Double valor
    ) {
        public static AtualizarContaCommand toCommand(AtualizarContaDTO atualizarContaDTO) {
            return new AtualizarContaCommand(
                    atualizarContaDTO.id(),
                    atualizarContaDTO.dataVencimento(),
                    atualizarContaDTO.dataPagamento(),
                    atualizarContaDTO.descricao(),
                    atualizarContaDTO.situacaoContaEnum(),
                    atualizarContaDTO.valor());
        }
    }
}
