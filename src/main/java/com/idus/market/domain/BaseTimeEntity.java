package com.idus.market.domain;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

  @ApiModelProperty(hidden = true)
  @CreatedDate
  public LocalDateTime createdAt;

  @ApiModelProperty(hidden = true)
  @LastModifiedDate
  public LocalDateTime modifiedAt;

}
