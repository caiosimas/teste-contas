package teste.totvs.contas.core.conta.domain;

import jakarta.persistence.*;
import teste.totvs.contas.core.conta.AlterarSituacaoContaUseCase;
import teste.totvs.contas.core.conta.AtualizarContaUseCase;
import teste.totvs.contas.core.conta.CadastrarContaUseCase;
import teste.totvs.contas.core.conta.adapter.in.api.dto.CadastrarContaCSV;
import teste.totvs.contas.core.conta.domain.enums.SituacaoContaEnum;
import teste.totvs.contas.core.infra.JwtAppService;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CONTA")
public class Conta {

    @Id
    private UUID id;

    private LocalDateTime dataVencimento;

    private LocalDateTime dataPagamento;

    private String descricao;

    @Enumerated(EnumType.ORDINAL)
    private SituacaoContaEnum situacaoConta;

    private Double valor;

    private UUID userID;

    public static Conta cadastrarConta(CadastrarContaUseCase.CadastrarContaCommand cadastrarContaCommand) {
        Conta conta = new Conta();
        conta.id = UUID.randomUUID();
        conta.dataVencimento = cadastrarContaCommand.dataVencimento();
        conta.dataPagamento = cadastrarContaCommand.dataPagamento();
        conta.descricao = cadastrarContaCommand.descricao();
        conta.situacaoConta = cadastrarContaCommand.situacaoContaEnum();
        conta.valor = cadastrarContaCommand.valor();
        conta.userID = JwtAppService.carregarUserID();

        return conta;
    }

    public static Conta cadastrarConta(CadastrarContaCSV cadastrarContaCSV) {
        Conta conta = new Conta();
        conta.id = UUID.randomUUID();
        conta.dataVencimento = cadastrarContaCSV.getDataVencimento();
        conta.dataPagamento = cadastrarContaCSV.getDataPagamento();
        conta.descricao = cadastrarContaCSV.getDescricao();
        conta.situacaoConta = cadastrarContaCSV.getSituacaoContaEnum();
        conta.valor = cadastrarContaCSV.getValor();
        conta.userID = JwtAppService.carregarUserID();

        return conta;
    }

    public Conta atualizarConta(AtualizarContaUseCase.AtualizarContaCommand atualizarContaCommand) {
        this.dataVencimento = atualizarContaCommand.dataVencimento();
        this.dataPagamento = atualizarContaCommand.dataPagamento();
        this.descricao = atualizarContaCommand.descricao();
        this.situacaoConta = atualizarContaCommand.situacaoContaEnum();
        this.valor = atualizarContaCommand.valor();

        return this;
    }

    public Conta atualizarSituacaoConta(AlterarSituacaoContaUseCase.AlterarSituacaoContaCommand alterarSituacaoContaCommand) {
        this.situacaoConta = alterarSituacaoContaCommand.situacaoConta();

        return this;
    }

    public void atualizarDataPagamento() {
        this.dataPagamento = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public SituacaoContaEnum getSituacaoConta() {
        return situacaoConta;
    }

    public Double getValor() {
        return valor;
    }
}
