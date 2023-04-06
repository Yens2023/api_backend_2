package api.java.backend.config.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    @Value("${app.jwt.secret}")
    private String jwtSecret;
    @Value("${app.jwt.expiration-ms}")
    private  int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){

        UserDetails user = (UserDetails)authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((user.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret) //ES512
                .compact();

    }

    public  String getUserNameFromJwtToken(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJwt(token)
                .getBody()
                .getSubject();
    }

    public  boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(authToken);
            return true;
        }catch (SignatureException e){
            //log.error("Invalid JWT signature: {}", e.getMessage());
        }catch (MalformedJwtException e){
            //log.error("Invalid JWT token: {}", e.getMessage());
        }catch (ExpiredJwtException e){
            //log.error("JWT token is expired: {}", e.getMessage());
        }catch (UnsupportedJwtException e){
            //log.error("JWT token is unsupported: {}", e.getMessage());
        }catch (IllegalArgumentException e){
            //log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return  false;
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }

}
