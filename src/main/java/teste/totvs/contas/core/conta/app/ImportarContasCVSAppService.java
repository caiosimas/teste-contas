package teste.totvs.contas.core.conta.app;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teste.totvs.contas.core.conta.ImportarContasCSVUseCase;
import teste.totvs.contas.core.conta.adapter.in.api.dto.CadastrarContaCSV;
import teste.totvs.contas.core.conta.domain.Conta;
import teste.totvs.contas.core.conta.domain.ContaDomainRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Service
@Transactional
public class ImportarContasCVSAppService implements ImportarContasCSVUseCase {

    private ContaDomainRepository contaDomainRepository;

    @Override
    public Boolean handle(ImportarContasCSVCommand importarContasCSVCommand) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(importarContasCSVCommand.file().getInputStream()))) {
            CsvToBean<CadastrarContaCSV> csvToBean = new CsvToBeanBuilder<CadastrarContaCSV>(reader)
                    .withType(CadastrarContaCSV.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<CadastrarContaCSV> cadastrarContaCSVS = csvToBean.parse();
            if (cadastrarContaCSVS == null || cadastrarContaCSVS.isEmpty()) {
                return false;
            }

            cadastrarContaCSVS.forEach(contaCSV -> this.contaDomainRepository.save(Conta.cadastrarConta(contaCSV)));
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Autowired
    public void setContaDomainRepository(ContaDomainRepository contaDomainRepository) {
        this.contaDomainRepository = contaDomainRepository;
    }
}
