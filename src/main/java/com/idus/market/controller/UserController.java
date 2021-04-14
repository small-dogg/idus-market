package com.idus.market.controller;

import com.idus.market.domain.user.User;
import com.idus.market.dto.UserDto.GetUsersRequestDto;
import com.idus.market.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
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
  @ApiOperation(value = "사용자 리스트 조회", notes = "사용자 목록을 조회합니다")
//  @ApiImplicitParams({
//      @ApiImplicitParam(name = "target", value = "정렬 대상"),
//      @ApiImplicitParam(name = "orderby", value = "정렬 기준"),
//      @ApiImplicitParam(name = "page", value = "특정 페이지 (첫페이지 : 0)"),
//      @ApiImplicitParam(name = "size", value = "한 페이지당 출력할 목록 수")
//  })
  public List<User> getUsers(Pageable pageable, GetUsersRequestDto getUsersRequestDto) {
    return userService.findAll(pageable, getUsersRequestDto);
  }

  @GetMapping("/{username}")
  @ApiOperation(value = "사용자 조회", notes = "특정 사용자를 조회합니다")
  @ApiImplicitParam(name = "username", value = "이름")
  public User getUser(@PathVariable String username) {
    // TODO 단일 회원 상세 정보 조회
    return userService.findByUsername(username).get();
  }
}
