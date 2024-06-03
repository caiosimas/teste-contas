package teste.totvs.contas.core.auth.domain;

import org.springframework.data.jpa.repository.Query;
import teste.totvs.contas.core.auth.AutenticarUseCase;
import teste.totvs.contas.core.auth.exception.UsuarioNaoEncontradoException;

import java.util.Optional;
import java.util.UUID;

public interface AuthDomainRepository {

    Auth save(Auth login);

    @Query(value = "SELECT o FROM Auth o WHERE o.username = :login")
    Optional<AutenticarUseCase.AuthProjection> findByLogin(String login);

    @Query(value = "SELECT o FROM Auth o WHERE o.id = :id")
    Optional<AutenticarUseCase.AuthProjection> findById(UUID id);

    default AutenticarUseCase.AuthProjection findByLoginOrThrowNotFound(String login) {
        return this.findByLogin(login).orElseThrow(UsuarioNaoEncontradoException::new);
    }

    default AutenticarUseCase.AuthProjection findByid(UUID id) {
        return this.findById(id).orElseThrow(UsuarioNaoEncontradoException::new);
    }

}
