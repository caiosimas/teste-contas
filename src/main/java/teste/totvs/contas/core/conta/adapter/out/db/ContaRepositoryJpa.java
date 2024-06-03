package teste.totvs.contas.core.conta.adapter.out.db;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import teste.totvs.contas.core.conta.CarregarContaPorIdUseCase;
import teste.totvs.contas.core.conta.CarregarContasPorFiltrosUseCase;
import teste.totvs.contas.core.conta.domain.Conta;
import teste.totvs.contas.core.conta.domain.ContaDomainRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ContaRepositoryJpa extends ContaDomainRepository, CrudRepository<Conta, UUID> {

    @Query(value = "SELECT o FROM Conta o WHERE o.id = :id")
    CarregarContaPorIdUseCase.ContaProjection findContaDTOById(UUID id);

    @Query(value = "SELECT o FROM Conta o WHERE (" +
            "o.dataVencimento BETWEEN :vencimentoInicial AND :vencimentoFinal) " +
            "AND (:descricao IS NULL OR o.descricao LIKE %:descricao%)")
    List<CarregarContasPorFiltrosUseCase.ContaProjection> findByFilters(LocalDateTime vencimentoInicial, LocalDateTime vencimentoFinal, String descricao, Pageable pageable);

    @Query(value = "SELECT SUM(o.valor) FROM Conta o WHERE o.dataPagamento BETWEEN :dataInicial AND :dataFinal AND o.situacaoConta = 3")
    Double carergarValorPagoPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal);

}
