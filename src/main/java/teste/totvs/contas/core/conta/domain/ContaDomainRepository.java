package teste.totvs.contas.core.conta.domain;

import org.springframework.data.domain.Pageable;
import teste.totvs.contas.core.conta.CarregarContaPorIdUseCase;
import teste.totvs.contas.core.conta.CarregarContasPorFiltrosUseCase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContaDomainRepository {

    Conta save(Conta conta);

    Optional<Conta> findById(UUID id, UUID userID);

    CarregarContaPorIdUseCase.ContaProjection findContaDTOById(UUID id, UUID userID);

    Double carergarValorPagoPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, UUID userID);

    List<CarregarContasPorFiltrosUseCase.ContaProjection> findByFilters(LocalDateTime vencimentoInicial, LocalDateTime vencimentoFinal, String descricao, Pageable pageable, UUID userID);
}
