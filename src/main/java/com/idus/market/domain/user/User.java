package com.idus.market.domain.user;

import com.idus.market.domain.BaseTimeEntity;
import com.idus.market.dto.UserDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity(name = "user")
@Getter
@Data
public class User extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 20, nullable = false)
  private String username;

  @Column(length = 30, nullable = false)
  private String nick;

  @Column(nullable = false)
  private String password;

  @Column(length = 20, nullable = false)
  private String phoneNumber;

  @Column(length = 100, nullable = false)
  private String email;

  @Column(nullable = false)
  private String role;

  @Enumerated(value = EnumType.STRING)
  @Column(length = 8)
  private GenderType gender;

  public List<String> getRoleList() {
    if (this.role.length() > 0) {
      return Arrays.asList(this.role.split(","));
    }
    return new ArrayList<>();
  }

  public User(UserDto userDto) {
    this.username = userDto.getUsername();
    this.nick = userDto.getNick();
    this.password = userDto.getPassword();
    this.phoneNumber = userDto.getPhoneNumber();
    this.email = userDto.getEmail();
    this.role = userDto.getRoles();
    this.gender = userDto.getGender();
  }
}
