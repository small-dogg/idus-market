package com.idus.market.config.jwt;

import com.idus.market.config.auth.PrincipalDetails;
import com.idus.market.config.auth.PrincipalDetailsService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class TokenProvider {

  private final PrincipalDetailsService principalDetailsService;
  private final TokenService tokenService;
  private long tokenValidTime = 20 * 60 * 1000L;

  private transient byte[] keyHMAC = "A&'/}Z57M(2hNg=;LE?~]YtRMS5(yZ<vcZTA3N-($>2j:ZeX-BGftaVk`)jKP~q?,jk)EMbgt*kW'("
      .getBytes(StandardCharsets.UTF_8);

  public String createToken(PrincipalDetails principalDetails) {
    Map<String, Object> headerClaims = new HashMap<>();
    headerClaims.put("type", "JWT");
    Map<String, Object> claims = new HashMap<>();
    claims.put("email", principalDetails.getUser().getEmail());

    Date now = new Date();

    String token = Jwts.builder()
        .setSubject(principalDetails.getUser().getEmail())
        .setClaims(claims)
        .setExpiration(new Date(now.getTime() + tokenValidTime))
        .setIssuer(principalDetails.getUser().getEmail())
        .setIssuedAt(now)
        .setHeader(headerClaims)
        .signWith(SignatureAlgorithm.HS512, this.keyHMAC)
        .compact();
    tokenService.createToken(token);
    return token;
  }

  public Authentication getAuthentication(String token) {
    String subject = (String) Jwts.parser().setSigningKey(keyHMAC).parseClaimsJws(token).getBody()
        .get("email");
    PrincipalDetails principalDetails = (PrincipalDetails) principalDetailsService
        .loadUserByUsername(subject);
    principalDetails.setToken(token);

    return new UsernamePasswordAuthenticationToken(principalDetails, "",
        principalDetails.getAuthorities());
  }

  public boolean validateToken(String token) {
    return tokenService.validateToken(token);
  }

  public String resolveToken(HttpServletRequest request) {
    String xAuthToken = request.getHeader("X-Auth-Token");
    if (xAuthToken == null || xAuthToken == "") {
      return null;
    }
    return xAuthToken.split(" ")[1];
  }
}
