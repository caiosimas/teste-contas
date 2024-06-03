package teste.totvs.contas.core.auth.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import teste.totvs.contas.core.auth.AutenticarUseCase;
import teste.totvs.contas.core.auth.domain.AuthDomainRepository;
import teste.totvs.contas.core.auth.exception.SenhaInvalidaException;
import teste.totvs.contas.core.infra.JwtAppService;

@Service
public class AutenticarAppService implements AutenticarUseCase {

    private AuthDomainRepository authDomainRepository;
    private JwtAppService jwtAppService;

    @Override
    public String handle(AutenticarCommand autenticarCommand) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDetails user = this.loadUserByUsername(autenticarCommand.login());
        this.validarSenha(autenticarCommand, encoder, user);

        return this.jwtAppService.gerarToken(user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        AuthProjection authProjetion = this.authDomainRepository.findByLoginOrThrowNotFound(login);

        return User
                .builder()
                .username(authProjetion.getUsername())
                .password(authProjetion.getPassword())
                .build();
    }

    private void validarSenha(AutenticarCommand command, PasswordEncoder encoder, UserDetails user) {
        boolean senhaExistente = encoder.matches(command.senha(), user.getPassword());

        if (!senhaExistente) throw new SenhaInvalidaException();
    }

    @Autowired
    public void setAuthDomainRepository(AuthDomainRepository authDomainRepository) {
        this.authDomainRepository = authDomainRepository;
    }

    @Autowired
    public void setJwtAppService(JwtAppService jwtAppService) {
        this.jwtAppService = jwtAppService;
    }
}
