package teste.totvs.contas.core.auth.domain;

import org.springframework.data.jpa.repository.Query;
import teste.totvs.contas.core.auth.AutenticarUseCase;
import teste.totvs.contas.core.auth.exception.UsuarioNaoEncontradoException;

import java.util.Optional;

public interface AuthDomainRepository {

    Auth save(Auth login);

    @Query(value = "SELECT o FROM Auth o WHERE o.username = :login")
    Optional<AutenticarUseCase.AuthProjection> findByLogin(String login);

    default AutenticarUseCase.AuthProjection findByLoginOrThrowNotFound(String login) {
        return this.findByLogin(login).orElseThrow(UsuarioNaoEncontradoException::new);
    }
}
