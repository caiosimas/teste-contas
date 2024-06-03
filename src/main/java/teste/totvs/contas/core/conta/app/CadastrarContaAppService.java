package teste.totvs.contas.core.conta.app;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teste.totvs.contas.core.conta.CadastrarContaUseCase;
import teste.totvs.contas.core.conta.domain.Conta;
import teste.totvs.contas.core.conta.domain.ContaDomainRepository;

@Service
@Transactional
public class CadastrarContaAppService implements CadastrarContaUseCase {

    private ContaDomainRepository contaDomainRepository;

    @Override
    public void handle(CadastrarContaCommand cadastrarContaCommand) {
        this.contaDomainRepository.save(Conta.cadastrarConta(cadastrarContaCommand));
    }

    @Autowired
    public void setContaDomainRepository(ContaDomainRepository contaDomainRepository) {
        this.contaDomainRepository = contaDomainRepository;
    }
}
