package com.idus.market.domain.user;

import com.idus.market.domain.BaseTimeEntity;
import com.idus.market.dto.CreateUserDto;
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
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity(name = "user")
@Getter
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

  public User(CreateUserDto createUserDto) {
    this.username = createUserDto.getUsername();
    this.nick = createUserDto.getNick();
    this.password = createUserDto.getPassword();
    this.phoneNumber = createUserDto.getPhoneNumber();
    this.email = createUserDto.getEmail();
    this.role = createUserDto.getRoles();
    this.gender = createUserDto.getGender();
  }

  public List<String> getRoleList() {
    if (this.role.length() > 0) {
      return Arrays.asList(this.role.split(","));
    }
    return new ArrayList<>();
  }
}
