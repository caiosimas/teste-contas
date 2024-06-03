package teste.totvs.contas.core.auth.adapter.in.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import teste.totvs.contas.core.auth.AutenticarUseCase;
import teste.totvs.contas.core.auth.RegistrarUseCase;
import teste.totvs.contas.core.auth.adapter.in.api.dto.AutenticarDTO;
import teste.totvs.contas.core.auth.adapter.in.api.dto.RegistrarDTO;
import teste.totvs.contas.core.auth.exception.SenhaInvalidaException;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private AutenticarUseCase autenticarUseCase;
    private RegistrarUseCase registrarUseCase;

    @PostMapping("/logar")
    public ResponseEntity<String> autenticar(final @RequestBody AutenticarDTO autenticarDTO) {
        try {
            return ResponseEntity.ok(this.autenticarUseCase.handle(AutenticarUseCase.AutenticarCommand.toCommand(autenticarDTO)));
        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Boolean> cadastrar(final @RequestBody RegistrarDTO registrarDTO) {
        this.registrarUseCase.handle(RegistrarUseCase.RegistrarCommand.toCommand(registrarDTO));
        return ResponseEntity.ok(true);
    }

    @Autowired
    public void setAutenticarUseCase(AutenticarUseCase autenticarUseCase) {
        this.autenticarUseCase = autenticarUseCase;
    }

    @Autowired
    public void setRegistrarUseCase(RegistrarUseCase registrarUseCase) {
        this.registrarUseCase = registrarUseCase;
    }
}
