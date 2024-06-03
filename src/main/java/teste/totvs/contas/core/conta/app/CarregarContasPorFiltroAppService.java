package teste.totvs.contas.core.conta.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teste.totvs.contas.core.conta.CarregarContasPorFiltrosUseCase;
import teste.totvs.contas.core.conta.domain.ContaDomainRepository;
import teste.totvs.contas.core.infra.JwtAppService;

import java.util.List;

@Service
public class CarregarContasPorFiltroAppService implements CarregarContasPorFiltrosUseCase {

    private ContaDomainRepository contaDomainRepository;

    @Override
    public List<ContaProjection> handle(CarregarContasPorFiltroCommand carregarContasPorFiltroCommand) {
        return this.contaDomainRepository.findByFilters(
                carregarContasPorFiltroCommand.dataVencimentoInicial(),
                carregarContasPorFiltroCommand.dataVencimentoFinal(),
                carregarContasPorFiltroCommand.descricao(),
                carregarContasPorFiltroCommand.pageable(),
                JwtAppService.carregarUserID()
        );
    }

    @Autowired
    public void setContaDomainRepository(ContaDomainRepository contaDomainRepository) {
        this.contaDomainRepository = contaDomainRepository;
    }
}
