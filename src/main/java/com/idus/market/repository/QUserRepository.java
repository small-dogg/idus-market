package com.idus.market.repository;


import static org.reflections.util.Utils.isEmpty;

import com.idus.market.domain.order.QOrders;
import com.idus.market.domain.user.QUser;
import com.idus.market.dto.QUserDto_GetUsersResponseDto;
import com.idus.market.dto.UserDto.GetUsersRequestDto;
import com.idus.market.dto.UserDto.GetUsersResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class QUserRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public List<GetUsersResponseDto> findAllByUsernameIsContainingWithLastOrder(Pageable pageable,
      GetUsersRequestDto getUsersRequestDto) {

    QUser qUser = new QUser("u");
    QOrders qOrders = new QOrders("o1");

    QOrders qOrdersComparer = new QOrders("o2");

    List<GetUsersResponseDto> getUsersResponseDtoList = jpaQueryFactory
        .select(new QUserDto_GetUsersResponseDto(qUser, qOrders))
        .from(qUser)
        .leftJoin(qOrders).on(qUser.id.eq(qOrders.userId))
        .leftJoin(qOrdersComparer).on(qUser.id.eq(qOrdersComparer.userId)
            .and(qOrdersComparer.createdAt.after(qOrders.createdAt)))
        .where(qOrdersComparer.createdAt.isNull())
        .where(
            isEmpty(getUsersRequestDto.getUsername()) ? null
                : qUser.username.eq(getUsersRequestDto.getUsername()),
            isEmpty(getUsersRequestDto.getEmail()) ? null
                : qUser.email.eq(getUsersRequestDto.getEmail())
        ).offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    //pageable default 0page, 20size
//    List<GetUsersResponseDto> getUsersResponseDtoList = new ArrayList<>();
//        .where(qUser.username.eq(username))
//        .fetch();

    return getUsersResponseDtoList;
  }


}

