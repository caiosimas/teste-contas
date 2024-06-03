package teste.totvs.contas.core.conta.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teste.totvs.contas.core.conta.CarregarContaPorIdUseCase;
import teste.totvs.contas.core.conta.domain.ContaDomainRepository;
import teste.totvs.contas.core.infra.JwtAppService;

@Service
public class CarregarContaPorIdAppService implements CarregarContaPorIdUseCase {

    private ContaDomainRepository contaDomainRepository;

    @Override
    public ContaProjection handle(CarregarContaPorIdCommand carregarContaPorIdCommand) {
        return contaDomainRepository.findContaDTOById(carregarContaPorIdCommand.id(), JwtAppService.carregarUserID());
    }

    @Autowired
    public void setContaDomainRepository(ContaDomainRepository contaDomainRepository) {
        this.contaDomainRepository = contaDomainRepository;
    }
}
