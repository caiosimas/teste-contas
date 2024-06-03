package teste.totvs.contas.core.conta.app;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teste.totvs.contas.core.conta.AtualizarContaUseCase;
import teste.totvs.contas.core.conta.domain.ContaDomainRepository;
import teste.totvs.contas.core.infra.JwtAppService;

@Service
@Transactional
public class AtualizarContaAppService implements AtualizarContaUseCase {

    private ContaDomainRepository contaDomainRepository;

    @Override
    public void handle(AtualizarContaCommand atualizarContaCommand) {
        this.contaDomainRepository.findById(atualizarContaCommand.id(), JwtAppService.carregarUserID()).ifPresent(conta -> this.contaDomainRepository.save(conta.atualizarConta(atualizarContaCommand)));
    }

    @Autowired
    public void setContaDomainRepository(ContaDomainRepository contaDomainRepository) {
        this.contaDomainRepository = contaDomainRepository;
    }
}
