package com.idus.market.dto;

import com.idus.market.domain.order.Orders;
import com.idus.market.domain.user.GenderType;
import com.idus.market.domain.user.User;
import com.querydsl.core.annotations.QueryProjection;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserDto {

  @Getter
  public static class GetUsersResponseDto {

    private String name;
    private String nick;
    private String phoneNumber;
    private String email;
    private Orders orders;

    @QueryProjection
    public GetUsersResponseDto(User user, Orders orders) {
      this.name = user.getName();
      this.nick = user.getNick();
      this.phoneNumber = user.getPhoneNumber();
      this.email = user.getEmail();
      this.orders = orders;
    }
  }

  @Getter
  @Builder
  @AllArgsConstructor
  public static class GetUsersRequestDto {

    private String name;
    private String email;
  }

  @Getter
  @Builder
  @AllArgsConstructor
  public static class CreateUserDto {

    @NotBlank(message = "이름은 공백일 수 없습니다")
    @Size(min = 1, max = 20, message = "이름의 길이는 최대 20자입니다")
    @Pattern(regexp = "^[가-힣|a-z|A-Z]+$", message = "이름은 영문 소문자, 영문 대문자, 한글만 입력할 수 있습니다")
    private String name;

    @NotBlank(message = "닉네임은 공백일 수 없습니다")
    @Size(min = 1, max = 30, message = "닉네임의 길이는 최대 30자입니다")
    @Pattern(regexp = "^[a-z]+$", message = "닉네임은 영문 소문자만 입력할 수 있습니다")
    private String nick;

    @NotBlank(message = "패스워드는 공백일 수 없습니다")
    @Size(min = 10, message = "패스워드의 길이는 최소 10자입니다")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).*$", message = "패스워드는 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함해야 합니다")
    private String password;

    @NotBlank
    @Size(max = 100, message = "이메일의 길이는 최대 100자입니다")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", message = "올바른 형식의 이메일 주소여야 합니다")
    private String email;

    @NotBlank(message = "전화번호는 공백일 수 없습니다")
    @Size(max = 20, message = "전화번호의 길이는 최대 20자입니다")
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "휴대폰 번호는 010-1234-5678 형태로 표기하세요")
    private String phoneNumber;

    @NotNull
    private GenderType gender;

    @ApiModelProperty(hidden = true)
    private String roles;

    public void setRoles(String roles) {
      this.roles = roles;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }

}
