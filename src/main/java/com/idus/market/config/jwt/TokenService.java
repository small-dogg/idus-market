package com.idus.market.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Configuration
public class TokenService {

  private final StringRedisTemplate redisTemplate;

  @Transactional
  public boolean createToken(String token) {
    ValueOperations<String, String> logoutValueOperations = redisTemplate.opsForValue();
    logoutValueOperations.set(token, token);
    return true;
  }

  @Transactional
  public boolean deleteToken(String token) {
    return redisTemplate.delete(token);
  }

  @Transactional
  public boolean validateToken(String token) {
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey(token).parseClaimsJws(token);
      ValueOperations<String, String> logoutValueOperations = redisTemplate.opsForValue();
      if (logoutValueOperations.get(token) != null) {
        return false;
      }
      return !claims.getBody().getExpiration().before(new Date());
    } catch (Exception e) {
      return false;
    }
  }
}
