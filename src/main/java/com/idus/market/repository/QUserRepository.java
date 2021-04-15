package com.idus.market.repository;


import com.idus.market.domain.order.QOrders;
import com.idus.market.domain.user.QUser;
import com.idus.market.dto.UserDto.GetUsersResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class QUserRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public List<GetUsersResponseDto> findAllByUsernameIsContainingWithLastOrder(String username) {
    QUser qUser = QUser.user;
    QOrders qOrders1 = new QOrders("Orders1");
    QOrders qOrders2 = new QOrders("Orders2");

    List<GetUsersResponseDto> getUsersResponseDtoList = jpaQueryFactory
        .select(new GetUsersResponseDto(qUser.username, qUser.nick, qOrders2.name))
        .from(qUser)
        .leftJoin(qOrders1).on(qUser.id.eq(qOrders1.userId))
        .leftJoin(qOrders2).on(qUser.id.eq(qOrders2.userId)
            .and(qOrders2.createdAt.after(qOrders1.createdAt)))
        .where(qOrders2.createdAt.isNull())
        .where(qUser.username.eq(username))
        .fetch();

    return getUsersResponseDtoList;
  }


}

