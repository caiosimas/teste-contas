package teste.totvs.contas.core.auth.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teste.totvs.contas.core.auth.RegistrarUseCase;
import teste.totvs.contas.core.auth.domain.Auth;
import teste.totvs.contas.core.auth.domain.AuthDomainRepository;

@Service
@Transactional
public class RegistrarAppService implements RegistrarUseCase {

    private AuthDomainRepository authDomainRepository;

    @Override
    public void handle(RegistrarCommand registrarCommand) {
        this.authDomainRepository.save(Auth.registrarAuth(registrarCommand));
    }

    @Autowired
    public void setAuthDomainRepository(AuthDomainRepository authDomainRepository) {
        this.authDomainRepository = authDomainRepository;
    }
}
