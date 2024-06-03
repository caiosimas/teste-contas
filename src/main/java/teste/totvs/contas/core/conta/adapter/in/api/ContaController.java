package teste.totvs.contas.core.conta.adapter.in.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import teste.totvs.contas.core.conta.*;
import teste.totvs.contas.core.conta.adapter.in.api.dto.AlterarSituacaoContaDTO;
import teste.totvs.contas.core.conta.adapter.in.api.dto.AtualizarContaDTO;
import teste.totvs.contas.core.conta.adapter.in.api.dto.CadastrarContaDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = "/ws-api/conta")
public class ContaController {

    private CadastrarContaUseCase cadastrarContaUseCase;
    private AtualizarContaUseCase atualizarContaUseCase;
    private AlterarSituacaoContaUseCase alterarSituacaoContaUseCase;
    private CarregarContaPorIdUseCase carregarContaPorIdUseCase;
    private CarregarContasPorFiltrosUseCase carregarContasPorFiltrosUseCase;
    private CarregarValorPagoPorPeriodoUseCase carregarValorPagoPorPeriodoUseCase;
    private ImportarContasCSVUseCase importarContasCSVUseCase;

    @PostMapping
    public ResponseEntity<Boolean> cadastrarConta(@RequestBody CadastrarContaDTO cadastrarContaDTO) {
        this.cadastrarContaUseCase.handle(CadastrarContaUseCase.CadastrarContaCommand.toCommand(cadastrarContaDTO));
        return ResponseEntity.ok(true);
    }

    @PutMapping
    public ResponseEntity<Boolean> atualizarConta(@RequestBody AtualizarContaDTO atualizarContaDTO) {
        this.atualizarContaUseCase.handle(AtualizarContaUseCase.AtualizarContaCommand.toCommand(atualizarContaDTO));

        return ResponseEntity.ok(true);
    }

    @PatchMapping
    public ResponseEntity<Boolean> atualizarSituacaoConta(@RequestBody AlterarSituacaoContaDTO alterarSituacaoContaDTO) {
        this.alterarSituacaoContaUseCase.handle(AlterarSituacaoContaUseCase.AlterarSituacaoContaCommand.toCommand(alterarSituacaoContaDTO));
        return ResponseEntity.ok(true);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CarregarContaPorIdUseCase.ContaProjection> carregarContaPorId(@PathVariable UUID id) {
        CarregarContaPorIdUseCase.ContaProjection contaProjection = this.carregarContaPorIdUseCase.handle(CarregarContaPorIdUseCase.CarregarContaPorIdCommand.toCommand(id));
        return ResponseEntity.ok(contaProjection);

    }

    @GetMapping(path = "/carregar-contas")
    public ResponseEntity<List<CarregarContasPorFiltrosUseCase.ContaProjection>> carregarContas(
            @RequestParam(name = "data-vencimento-inicial", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataVencimentoInicial,
            @RequestParam(name = "data-vencimento-final", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataVencimentoFinal,
            @RequestParam(name = "descricao", required = false) String descricao,
            @RequestParam(name = "pagina", required = false, defaultValue = "0") Integer pagina,
            @RequestParam(name = "maximo-por-pagina", required = false, defaultValue = "5") Integer maximoPorPagina
    ) {
        List<CarregarContasPorFiltrosUseCase.ContaProjection> contaProjections = this.carregarContasPorFiltrosUseCase.handle(new CarregarContasPorFiltrosUseCase.CarregarContasPorFiltroCommand(
                dataVencimentoInicial,
                dataVencimentoFinal,
                descricao,
                PageRequest.of(pagina, maximoPorPagina)
        ));

        return ResponseEntity.ok(contaProjections);
    }

    @GetMapping(path = "/carregar-valor-pago")
    public ResponseEntity<Double> carregarValorPagoPorPeriodo(
            @RequestParam(name = "data-inicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam(name = "data-final") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal
    ) {
        Double valorTotal = this.carregarValorPagoPorPeriodoUseCase.handle(new CarregarValorPagoPorPeriodoUseCase.CarregarValorPorPeriodoCommand(
                dataInicial,
                dataFinal
        ));

        return ResponseEntity.ok(valorTotal);
    }

    @PostMapping(path = "/importar-contas")
    public ResponseEntity<Boolean> importarContasCSV(@RequestBody MultipartFile file) {
        return ResponseEntity.ok(this.importarContasCSVUseCase.handle(new ImportarContasCSVUseCase.ImportarContasCSVCommand(file)));
    }

    @Autowired
    public void setCadastrarContaUseCase(CadastrarContaUseCase cadastrarContaUseCase) {
        this.cadastrarContaUseCase = cadastrarContaUseCase;
    }

    @Autowired
    public void setAtualizarContaUseCase(AtualizarContaUseCase atualizarContaUseCase) {
        this.atualizarContaUseCase = atualizarContaUseCase;
    }

    @Autowired
    public void setAlterarSituacaoContaUseCase(AlterarSituacaoContaUseCase alterarSituacaoContaUseCase) {
        this.alterarSituacaoContaUseCase = alterarSituacaoContaUseCase;
    }

    @Autowired
    public void setCarregarContaPorIdUseCase(CarregarContaPorIdUseCase carregarContaPorIdUseCase) {
        this.carregarContaPorIdUseCase = carregarContaPorIdUseCase;
    }

    @Autowired
    public void setCarregarContasPorFiltrosUseCase(CarregarContasPorFiltrosUseCase carregarContasPorFiltrosUseCase) {
        this.carregarContasPorFiltrosUseCase = carregarContasPorFiltrosUseCase;
    }

    @Autowired
    public void setCarregarValorPagoPorPeriodoUseCase(CarregarValorPagoPorPeriodoUseCase carregarValorPagoPorPeriodoUseCase) {
        this.carregarValorPagoPorPeriodoUseCase = carregarValorPagoPorPeriodoUseCase;
    }

    @Autowired
    public void setImportarContasCSVUseCase(ImportarContasCSVUseCase importarContasCSVUseCase) {
        this.importarContasCSVUseCase = importarContasCSVUseCase;
    }
}
