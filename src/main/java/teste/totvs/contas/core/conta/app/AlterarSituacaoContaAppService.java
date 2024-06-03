package teste.totvs.contas.core.conta.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teste.totvs.contas.core.conta.AlterarSituacaoContaUseCase;
import teste.totvs.contas.core.conta.domain.ContaDomainRepository;
import teste.totvs.contas.core.conta.domain.enums.SituacaoContaEnum;
import teste.totvs.contas.core.infra.JwtAppService;

@Service
@Transactional
public class AlterarSituacaoContaAppService implements AlterarSituacaoContaUseCase {

    private ContaDomainRepository contaDomainRepository;

    @Override
    public void handle(AlterarSituacaoContaUseCase.AlterarSituacaoContaCommand alterarSituacaoContaCommand) {
        this.contaDomainRepository.findById(alterarSituacaoContaCommand.id(), JwtAppService.carregarUserID()).ifPresent(conta -> {
            if (conta.getSituacaoConta() != null && conta.getSituacaoConta().equals(alterarSituacaoContaCommand.situacaoConta())) {
                return;
            }

            if (SituacaoContaEnum.PAGA.equals(alterarSituacaoContaCommand.situacaoConta())) {
                conta.atualizarDataPagamento();
            }

            this.contaDomainRepository.save(conta.atualizarSituacaoConta(alterarSituacaoContaCommand));
        });
    }

    @Autowired
    public void setContaDomainRepository(ContaDomainRepository contaDomainRepository) {
        this.contaDomainRepository = contaDomainRepository;
    }
}
