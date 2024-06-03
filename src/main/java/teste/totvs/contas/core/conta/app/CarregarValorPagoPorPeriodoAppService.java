package teste.totvs.contas.core.conta.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teste.totvs.contas.core.conta.CarregarValorPagoPorPeriodoUseCase;
import teste.totvs.contas.core.conta.domain.ContaDomainRepository;
import teste.totvs.contas.core.infra.JwtAppService;

@Service
public class CarregarValorPagoPorPeriodoAppService implements CarregarValorPagoPorPeriodoUseCase {

    private ContaDomainRepository contaDomainRepository;

    @Override
    public Double handle(CarregarValorPorPeriodoCommand carregarValorPorPeriodoCommand) {
        return this.contaDomainRepository.carergarValorPagoPorPeriodo(carregarValorPorPeriodoCommand.dataInicial(), carregarValorPorPeriodoCommand.dataFinal(), JwtAppService.carregarUserID());
    }

    @Autowired
    public void setContaDomainRepository(ContaDomainRepository contaDomainRepository) {
        this.contaDomainRepository = contaDomainRepository;
    }
}
