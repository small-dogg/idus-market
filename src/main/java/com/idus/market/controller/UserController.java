package com.idus.market.controller;

import com.idus.market.domain.user.User;
import com.idus.market.dto.UserDto.GetUsersRequestDto;
import com.idus.market.dto.UserDto.GetUsersResponseDto;
import com.idus.market.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  private final UserService userService;

  @GetMapping
  @ApiOperation(value = "여러 회원 목록 조회", notes = "여러 회원 목록을 조회합니다")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "username", value = "이름"),
      @ApiImplicitParam(name = "email", value = "이메일")
  })
  public List<GetUsersResponseDto> getUsers(Pageable pageable,
      GetUsersRequestDto getUsersRequestDto) {
    return userService.findAll(pageable, getUsersRequestDto);
  }

  @GetMapping("/{username}")
  @ApiOperation(value = "단일 회원 상세 정보 조회", notes = "특정 사용자를 조회합니다")
  @ApiImplicitParam(name = "email", value = "이메일")
  public User getUser(@PathVariable String email) {
    return userService.findByEmail(email).get();
  }
}
