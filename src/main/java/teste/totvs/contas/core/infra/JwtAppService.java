package teste.totvs.contas.core.infra;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class JwtAppService {

    private static final int EXPIRACAO = 2880; // 2 DIAS horas
    private static final String CHAVE_ASSINATURA = "aEkyQ1hTcnF1aXRNcGFYNW5Zcm9wVUc=";

    public String gerarToken(UserDetails usuario) {
        ZonedDateTime expirationTime = ZonedDateTime.now().plusMinutes(EXPIRACAO);

        return Jwts.builder()
                .setSubject(usuario.getUsername())
                .setExpiration(Date.from(expirationTime.toInstant()))
                .signWith(SignatureAlgorithm.HS512, CHAVE_ASSINATURA)
                .compact();
    }

    public boolean tokenValido(String token) {
        try {
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data =
                    dataExpiracao.toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(data);
        } catch (Exception e) {
            return false;
        }
    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException {
        return obterClaims(token).getSubject();
    }

    public static Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(CHAVE_ASSINATURA)
                .parseClaimsJws(token)
                .getBody();
    }
}
