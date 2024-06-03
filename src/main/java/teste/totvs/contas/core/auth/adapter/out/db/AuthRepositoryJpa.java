package teste.totvs.contas.core.auth.adapter.out.db;

import org.springframework.data.repository.Repository;
import teste.totvs.contas.core.auth.domain.Auth;
import teste.totvs.contas.core.auth.domain.AuthDomainRepository;

import java.util.UUID;

public interface AuthRepositoryJpa extends AuthDomainRepository, Repository<Auth, UUID> {
}
