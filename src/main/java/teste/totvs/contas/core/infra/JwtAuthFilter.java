package teste.totvs.contas.core.infra;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import teste.totvs.contas.core.auth.app.AutenticarAppService;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtAppService jwtAppServive;
    private AutenticarAppService autenticarAppService;

    public JwtAuthFilter(AutenticarAppService autenticarAppService, JwtAppService jwtAppServive) {
        this.autenticarAppService = autenticarAppService;
        this.jwtAppServive = jwtAppServive;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer")) {

            String token = authorization.split(" ")[1];
            var isValid = this.jwtAppServive.tokenValido(token);

            if (isValid) {

                String loginUsuario = this.jwtAppServive.obterLoginUsuario(token);
                UserDetails usuario = this.autenticarAppService.loadUserByUsername(loginUsuario);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        User.builder()
                                .username(usuario.getUsername())
                                .password(usuario.getPassword())
                                .build(),
                        null,
                        usuario.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                throw new TokenInvalidoException();
            }
        }

        filterChain.doFilter(request, response);
    }
}
