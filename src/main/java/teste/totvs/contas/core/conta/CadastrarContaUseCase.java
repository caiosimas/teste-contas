package teste.totvs.contas.core.conta;

import org.springframework.stereotype.Component;
import teste.totvs.contas.core.conta.adapter.in.api.dto.CadastrarContaDTO;
import teste.totvs.contas.core.conta.domain.enums.SituacaoContaEnum;

import java.time.LocalDateTime;

@Component
public interface CadastrarContaUseCase {

    void handle(CadastrarContaCommand cadastrarContaCommand);

    record CadastrarContaCommand(
            LocalDateTime dataVencimento,
            LocalDateTime dataPagamento,
            String descricao,
            SituacaoContaEnum situacaoContaEnum,
            Double valor
    ) {
        public static CadastrarContaCommand toCommand(CadastrarContaDTO cadastrarContaDTO) {
            return new CadastrarContaCommand(
                    cadastrarContaDTO.dataVencimento(),
                    cadastrarContaDTO.dataPagamento(),
                    cadastrarContaDTO.descricao(),
                    cadastrarContaDTO.situacaoContaEnum(),
                    cadastrarContaDTO.valor());
        }
    }
}
