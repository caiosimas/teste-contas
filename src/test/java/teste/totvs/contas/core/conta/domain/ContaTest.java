package teste.totvs.contas.core.conta.domain;


import org.junit.jupiter.api.Test;
import teste.totvs.contas.core.conta.AlterarSituacaoContaUseCase;
import teste.totvs.contas.core.conta.AtualizarContaUseCase;
import teste.totvs.contas.core.conta.CadastrarContaUseCase;
import teste.totvs.contas.core.conta.adapter.in.api.dto.CadastrarContaCSV;
import teste.totvs.contas.core.conta.domain.enums.SituacaoContaEnum;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContaTest {

    public static final LocalDateTime DATA_VENCIMENTO = LocalDateTime.now();
    public static final LocalDateTime DATA_PAGAMENTO = LocalDateTime.now();
    public static final String DESCRICAO = "Conta de luz";
    public static final SituacaoContaEnum SITUACAO_CONTA = SituacaoContaEnum.ABERTA;
    public static final Double VALOR = 137.80;

    @Test
    void cadastrarContaCommand() {
        CadastrarContaUseCase.CadastrarContaCommand contaCommand = new CadastrarContaUseCase.CadastrarContaCommand(
                DATA_VENCIMENTO, DATA_PAGAMENTO, DESCRICAO, SITUACAO_CONTA, VALOR
        );

        Conta conta = Conta.cadastrarConta(contaCommand);

        assertAll("Verifica se todos os atributos foram relacionados com sucesso",
                () -> assertEquals(DATA_VENCIMENTO, conta.getDataVencimento()),
                () -> assertEquals(DATA_PAGAMENTO, conta.getDataPagamento()),
                () -> assertEquals(DESCRICAO, conta.getDescricao()),
                () -> assertEquals(SITUACAO_CONTA, conta.getSituacaoConta()),
                () -> assertEquals(VALOR, conta.getValor()));
    }

    @Test
    void cadastrarContaCVS() {
        CadastrarContaCSV cadastrarContaCSV = new CadastrarContaCSV();
        cadastrarContaCSV.setDataVencimento(DATA_VENCIMENTO);
        cadastrarContaCSV.setDataPagamento(DATA_PAGAMENTO);
        cadastrarContaCSV.setDescricao(DESCRICAO);
        cadastrarContaCSV.setSituacaoContaEnum(SITUACAO_CONTA);
        cadastrarContaCSV.setValor(VALOR);

        Conta conta = Conta.cadastrarConta(cadastrarContaCSV);

        assertAll("Verifica se todos os atributos foram relacionados com sucesso",
                () -> assertEquals(DATA_VENCIMENTO, conta.getDataVencimento()),
                () -> assertEquals(DATA_PAGAMENTO, conta.getDataPagamento()),
                () -> assertEquals(DESCRICAO, conta.getDescricao()),
                () -> assertEquals(SITUACAO_CONTA, conta.getSituacaoConta()),
                () -> assertEquals(VALOR, conta.getValor()));
    }

    @Test
    void atualizarConta() {
        CadastrarContaUseCase.CadastrarContaCommand contaCommand = new CadastrarContaUseCase.CadastrarContaCommand(
                DATA_VENCIMENTO, DATA_PAGAMENTO, DESCRICAO, SITUACAO_CONTA, VALOR
        );

        Conta conta = Conta.cadastrarConta(contaCommand);
        Double novoValor = 200.0;

        AtualizarContaUseCase.AtualizarContaCommand atualizarContaCommand = new AtualizarContaUseCase.AtualizarContaCommand(
                conta.getId(), DATA_VENCIMENTO, DATA_PAGAMENTO, DESCRICAO, SITUACAO_CONTA, novoValor
        );

        conta.atualizarConta(atualizarContaCommand);

        assertAll("Verifica se todos os atributos foram relacionados com sucesso",
                () -> assertEquals(DATA_VENCIMENTO, conta.getDataVencimento()),
                () -> assertEquals(DATA_PAGAMENTO, conta.getDataPagamento()),
                () -> assertEquals(DESCRICAO, conta.getDescricao()),
                () -> assertEquals(SITUACAO_CONTA, conta.getSituacaoConta()),
                () -> assertEquals(novoValor, conta.getValor()));
    }

    @Test
    void atualizarSituacaoConta() {
        CadastrarContaUseCase.CadastrarContaCommand contaCommand = new CadastrarContaUseCase.CadastrarContaCommand(
                DATA_VENCIMENTO, DATA_PAGAMENTO, DESCRICAO, SITUACAO_CONTA, VALOR
        );

        Conta conta = Conta.cadastrarConta(contaCommand);
        SituacaoContaEnum novaSituacaoConta = SituacaoContaEnum.CANCELADA;

        AlterarSituacaoContaUseCase.AlterarSituacaoContaCommand alterarSituacaoContaCommand = new AlterarSituacaoContaUseCase.AlterarSituacaoContaCommand(
                conta.getId(), novaSituacaoConta
        );

        conta.atualizarSituacaoConta(alterarSituacaoContaCommand);

        assertAll("Verifica se todos os atributos foram relacionados com sucesso",
                () -> assertEquals(DATA_VENCIMENTO, conta.getDataVencimento()),
                () -> assertEquals(DATA_PAGAMENTO, conta.getDataPagamento()),
                () -> assertEquals(DESCRICAO, conta.getDescricao()),
                () -> assertEquals(novaSituacaoConta, conta.getSituacaoConta()),
                () -> assertEquals(VALOR, conta.getValor()));
    }

    @Test
    void atualizarDataPagamento() {
        CadastrarContaUseCase.CadastrarContaCommand contaCommand = new CadastrarContaUseCase.CadastrarContaCommand(
                DATA_VENCIMENTO, DATA_PAGAMENTO, DESCRICAO, SITUACAO_CONTA, VALOR
        );

        Conta conta = Conta.cadastrarConta(contaCommand);

        AlterarSituacaoContaUseCase.AlterarSituacaoContaCommand alterarSituacaoContaCommand = new AlterarSituacaoContaUseCase.AlterarSituacaoContaCommand(
                conta.getId(), SituacaoContaEnum.PAGA
        );

        conta.atualizarSituacaoConta(alterarSituacaoContaCommand);

        assertAll("Verifica se todos os atributos foram relacionados com sucesso",
                () -> assertEquals(DATA_VENCIMENTO, conta.getDataVencimento()),
                () -> assertEquals(DESCRICAO, conta.getDescricao()),
                () -> assertEquals(SituacaoContaEnum.PAGA, conta.getSituacaoConta()),
                () -> assertEquals(VALOR, conta.getValor()));
    }
}
