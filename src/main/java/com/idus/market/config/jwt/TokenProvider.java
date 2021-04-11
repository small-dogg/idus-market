package com.idus.market.config.jwt;

import com.idus.market.config.auth.PrincipalDetails;
import com.idus.market.config.auth.PrincipalDetailsService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
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

  private transient byte[] keyHMAC = "A&'/}Z57M(2hNg=;LE?~]YtRMS5(yZ<vcZTA3N-($>2j:ZeX-BGftaVk`)jKP~q?,jk)EMbgt*kW'("
      .getBytes(StandardCharsets.UTF_8);

  public String createToken(PrincipalDetails principalDetails) {
    Map<String, Object> headerClaims = new HashMap<>();
    headerClaims.put("type", "JWT");
    Map<String, Object> claims = new HashMap<>();
    claims.put("username", principalDetails.getUsername());

    Calendar c = Calendar.getInstance();
    Date now = c.getTime();
    c.add(Calendar.MINUTE, 1);
    Date expirationDate = c.getTime();

    String token = Jwts.builder()
        .setSubject("login")
        .setExpiration(expirationDate)
        .setIssuer(principalDetails.getUsername())
        .setIssuedAt(now)
        .setNotBefore(now)
        .setClaims(claims)
        .setHeader(headerClaims)
        .signWith(SignatureAlgorithm.HS512, this.keyHMAC)
        .compact();

    tokenService.createToken(token);

    return token;
  }

  public Authentication getAuthentication(String token) {
    String subject = Jwts.parser().setSigningKey(keyHMAC).parseClaimsJws(token).getBody()
        .getSubject();
    PrincipalDetails principalDetails = (PrincipalDetails) principalDetailsService
        .loadUserByUsername(subject);
    principalDetails.setToken(token);

    return new UsernamePasswordAuthenticationToken(principalDetails, "",
        principalDetails.getAuthorities());
  }

  public boolean validateToken(String token) {
    return false;
  }

  public String resolveToken(HttpServletRequest request) {
    String xAuthToken = request.getHeader("X-Auth-Token");
    //TODO Request로부터 토큰정보를 정상적으로 받아올 수 없는 이슈 있음 "/api/v1/auth를 제외한 나머지 대상들은 헤더정보에 Token값을 작성하여 정상 전달되도록 조치필요
    return "";
  }
}
