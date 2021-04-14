package com.idus.market.domain.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.idus.market.domain.BaseTimeEntity;
import com.idus.market.domain.user.User;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Builder
public class Orders extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "USER_ID")
  @JsonBackReference
  private User user;
}
