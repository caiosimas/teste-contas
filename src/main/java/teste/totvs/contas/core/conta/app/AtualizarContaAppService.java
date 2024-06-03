package teste.totvs.contas.core.conta.app;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teste.totvs.contas.core.conta.AtualizarContaUseCase;
import teste.totvs.contas.core.conta.domain.ContaDomainRepository;

@Service
@Transactional
public class AtualizarContaAppService implements AtualizarContaUseCase {

    private ContaDomainRepository contaDomainRepository;

    @Override
    public void handle(AtualizarContaCommand atualizarContaCommand) {
        this.contaDomainRepository.findById(atualizarContaCommand.id()).ifPresent(conta -> this.contaDomainRepository.save(conta.atualziarConta(atualizarContaCommand)));
    }

    @Autowired
    public void setContaDomainRepository(ContaDomainRepository contaDomainRepository) {
        this.contaDomainRepository = contaDomainRepository;
    }
}
