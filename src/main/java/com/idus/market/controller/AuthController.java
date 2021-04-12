package com.idus.market.controller;

import com.idus.market.config.CommonResponse;
import com.idus.market.config.auth.PrincipalDetails;
import com.idus.market.config.exception.LoginFailedException;
import com.idus.market.config.jwt.TokenProvider;
import com.idus.market.config.jwt.TokenService;
import com.idus.market.dto.AuthDto;
import com.idus.market.dto.UserDto;
import com.idus.market.service.AuthService;
import com.idus.market.service.UserService;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthService authService;
  private final UserService userService;
  private final TokenProvider tokenProvider;
  private final TokenService tokenService;

  @PostMapping("login")
  @ApiOperation(value = "로그인", notes = "회원 로그인을 수행합니다.")
  public CommonResponse login(AuthDto authDto) {
    PrincipalDetails principalDetails;
    try {
      principalDetails = authService.login(authDto);
    } catch (LoginFailedException loginFailedException) {
      return CommonResponse.builder()
          .code("LOGIN_ERROR")
          .status(401)
          .message(loginFailedException.getMessage())
          .build();
    }

    String token = tokenProvider.createToken(principalDetails);

    return CommonResponse.builder()
        .code("LOGIN_SUCCESS")
        .status(200)
        .message(token)
        .build();
  }

  @PostMapping("logout")
  @ApiOperation(value = "로그아웃", notes = "회원 로그아웃을 수행합니다.")
  public CommonResponse logout(String token) {
    if (!tokenService.deleteToken(token)) {
      return CommonResponse.builder()
          .code("TOKEN_EXPIRED_ERROR")
          .status(400)
          .message("유효하지 않은 토큰입니다")
          .build();
    }
    return CommonResponse.builder()
        .code("TOKEN_EXPIRED_SUCCESS")
        .status(200)
        .message("로그아웃을 성공하였습니다")
        .build();
  }

  @PostMapping("join")
  @ApiOperation(value = "회원가입", notes = "회원 가입을 수행합니다.")
  public CommonResponse join(@Valid UserDto userDto, Errors errors) {
    if (errors.hasErrors()) {
      return CommonResponse.builder()
          .code("REGISTERED_ERROR")
          .status(400)
          .message(errors.getAllErrors().get(0).getDefaultMessage())
          .build();
    }

    if (userService.findByEmail(userDto.getEmail()).isPresent() ||
        userService.findByUsername(userDto.getUsername()).isPresent()) {
      return CommonResponse.builder()
          .code("REGISTERED_ERROR")
          .status(400)
          .message("이미 존재하는 계정이거나, 이메일 주소가 중복되었습니다.")
          .build();
    }

    authService.join(userDto);

    return CommonResponse.builder()
        .code("JOIN_SUCCESS")
        .status(200)
        .build();
  }
}
