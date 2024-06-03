package teste.totvs.contas.core.conta;

import org.springframework.web.multipart.MultipartFile;

public interface ImportarContasCSVUseCase {

    Boolean handle(ImportarContasCSVCommand importarContasCSVCommand);

    record ImportarContasCSVCommand(
            MultipartFile file
    ){
    }
}
