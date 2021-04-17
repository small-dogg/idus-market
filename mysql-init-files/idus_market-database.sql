CREATE DATABASE `idus_market_write` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE `idus_market_read` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
set global time_zone = 'Asia/Seoul';
set time_zone = 'Asia/Seoul';

CREATE USER `sypark`;
ALTER USER `sypark` IDENTIFIED BY 'qwe1212!Q';
GRANT SELECT, INSERT, UPDATE, DELETE ON idus_market_write.* TO `sypark`;
GRANT SELECT ON idus_market_read.* TO `sypark`;

USE `idus_market_write`;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`          bigint(20)   NOT NULL AUTO_INCREMENT,
    `name`        varchar(20)  NOT NULL,
    `nick`        varchar(30)  NOT NULL,
    `password`    varchar(255) NOT NULL,
    `phoneNumber` varchar(20)  NOT NULL,
    `email`       varchar(100) NOT NULL,
    `gender`      varchar(8)  DEFAULT NULL,
    `roles`       varchar(255) NOT NULL,
    `createdAt`   datetime(6) DEFAULT NULL,
    `modifiedAt`  datetime(6) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table orders
(
    id         bigint not null auto_increment,
    createdAt  datetime(6),
    modifiedAt datetime(6),
    name       varchar(255),
    orderId    varchar(255),
    userId     bigint,
    primary key (id)
) engine = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

INSERT INTO `user`(`name`, `nick`, `password`, `phoneNumber`, `email`, `gender`, `roles`,
                   `createdAt`, `modifiedAt`)
VALUES ('테스트Aa', 'testa', '{bcrypt}$2a$10$bmTxU1mJ6oxiIKZloJxo9eH0yqZeGMcjo5tzDg167pJHwb1YUUEGW',
        '010-0000-0000', 'test@aa.a2', 'Male', 'ROLE_USER,ROLE_ADMIN', now(), now()),
       ('테스트Bb', 'testb', '{bcrypt}$2a$10$bmTxU1mJ6oxiIKZloJxo9eH0yqZeGMcjo5tzDg167pJHwb1YUUEGW',
        '010-0000-0000', 'test@aa.a3', 'Female', 'ROLE_USER', now(), now()),
       ('테스트Cc', 'testc', '{bcrypt}$2a$10$bmTxU1mJ6oxiIKZloJxo9eH0yqZeGMcjo5tzDg167pJHwb1YUUEGW',
        '010-0000-0000', 'test@aa.a4', 'Male', 'ROLE_USER', now(), now()),
       ('테스트Dd', 'testd', '{bcrypt}$2a$10$bmTxU1mJ6oxiIKZloJxo9eH0yqZeGMcjo5tzDg167pJHwb1YUUEGW',
        '010-0000-0000', 'test@aa.a5', 'Female', 'ROLE_USER,ROLE_ADMIN', now(), now()),
       ('테스트Ee', 'teste', '{bcrypt}$2a$10$bmTxU1mJ6oxiIKZloJxo9eH0yqZeGMcjo5tzDg167pJHwb1YUUEGW',
        '010-0000-0000', 'test@aa.a6', 'Male', 'ROLE_USER', now(), now()),
       ('테스트Ff', 'testf', '{bcrypt}$2a$10$bmTxU1mJ6oxiIKZloJxo9eH0yqZeGMcjo5tzDg167pJHwb1YUUEGW',
        '010-0000-0000', 'test@aa.a7', 'Female', 'ROLE_USER,ROLE_ADMIN', now(), now()),
       ('테스트Gg', 'testg', '{bcrypt}$2a$10$bmTxU1mJ6oxiIKZloJxo9eH0yqZeGMcjo5tzDg167pJHwb1YUUEGW',
        '010-0000-0000', 'test@aa.a8', 'Male', 'ROLE_USER', now(), now()),
       ('테스트Hh', 'testh', '{bcrypt}$2a$10$bmTxU1mJ6oxiIKZloJxo9eH0yqZeGMcjo5tzDg167pJHwb1YUUEGW',
        '010-0000-0000', 'test@aa.a9', 'Female', 'ROLE_USER', now(), now()),
       ('테스트Ii', 'testi', '{bcrypt}$2a$10$bmTxU1mJ6oxiIKZloJxo9eH0yqZeGMcjo5tzDg167pJHwb1YUUEGW',
        '010-0000-0000', 'test@aa.a10', 'Male', 'ROLE_USER', now(), now()),
       ('테스트Jj', 'testj', '{bcrypt}$2a$10$bmTxU1mJ6oxiIKZloJxo9eH0yqZeGMcjo5tzDg167pJHwb1YUUEGW',
        '010-0000-0000', 'test@aa.a11', 'Female', 'ROLE_USER', now(), now()),
       ('테스트Kk', 'testk', '{bcrypt}$2a$10$bmTxU1mJ6oxiIKZloJxo9eH0yqZeGMcjo5tzDg167pJHwb1YUUEGW',
        '010-0000-0000', 'test@aa.a12', 'Male', 'ROLE_USER', now(), now()),
       ('테스트Ll', 'testl', '{bcrypt}$2a$10$bmTxU1mJ6oxiIKZloJxo9eH0yqZeGMcjo5tzDg167pJHwb1YUUEGW',
        '010-0000-0000', 'test@aa.a13', 'Female', 'ROLE_USER,ROLE_ADMIN', now(), now()),
       ('테스트Mm', 'testm', '{bcrypt}$2a$10$bmTxU1mJ6oxiIKZloJxo9eH0yqZeGMcjo5tzDg167pJHwb1YUUEGW',
        '010-0000-0000', 'test@aa.a13', 'Male', 'ROLE_USER', now(), now()),
       ('테스트Nn', 'testn', '{bcrypt}$2a$10$bmTxU1mJ6oxiIKZloJxo9eH0yqZeGMcjo5tzDg167pJHwb1YUUEGW',
        '010-0000-0000', 'test@aa.a13', 'Female', 'ROLE_USER', now(), now()),
       ('테스트Oo', 'testo', '{bcrypt}$2a$10$bmTxU1mJ6oxiIKZloJxo9eH0yqZeGMcjo5tzDg167pJHwb1YUUEGW',
        '010-0000-0000', 'test@aa.a13', 'Male', 'ROLE_USER', now(), now()),
       ('테스트Pp', 'testp', '{bcrypt}$2a$10$bmTxU1mJ6oxiIKZloJxo9eH0yqZeGMcjo5tzDg167pJHwb1YUUEGW',
        '010-0000-0000', 'test@aa.a13', 'Female', 'ROLE_USER,ROLE_ADMIN', now(), now()),
       ('테스트Qq', 'testq', '{bcrypt}$2a$10$bmTxU1mJ6oxiIKZloJxo9eH0yqZeGMcjo5tzDg167pJHwb1YUUEGW',
        '010-0000-0000', 'test@aa.a13', 'Male', 'ROLE_USER', now(), now()),
       ('테스트Rr', 'testr', '{bcrypt}$2a$10$bmTxU1mJ6oxiIKZloJxo9eH0yqZeGMcjo5tzDg167pJHwb1YUUEGW',
        '010-0000-0000', 'test@aa.a13', 'Female', 'ROLE_USER', now(), now()),
       ('테스트Ss', 'tests', '{bcrypt}$2a$10$bmTxU1mJ6oxiIKZloJxo9eH0yqZeGMcjo5tzDg167pJHwb1YUUEGW',
        '010-0000-0000', 'test@aa.a13', 'Male', 'ROLE_USER', now(), now()),
       ('테스트Tt', 'testt', '{bcrypt}$2a$10$bmTxU1mJ6oxiIKZloJxo9eH0yqZeGMcjo5tzDg167pJHwb1YUUEGW',
        '010-0000-0000', 'test@aa.a13', 'Female', 'ROLE_USER', now(), now());

INSERT INTO `orders`(`orderId`, `name`, `createdAt`, `modifiedAt`, `userId`) value
    ('1JYDB23Z7Q9MF', '무료배송🏆 어버이날 카네이션 꽃 무드등 부모님선물', now(), now(), 1),
    ('US0Q0EX1HMJN', '🍓크림치즈딸기 스콘🍓', now() + 1, now() + 1, 2),
    ('1IAUULAKON22H', '인기💘마블대리석 톡+폰케이스 그립 커플 생일선물', now() + 2, now() + 2, 3),
    ('QDY8ZB72IA7N', '나만의 문구를 새긴 꽃 스마트톡 그립 + 핸드폰케이스', now() + 3, now() + 3, 4),
    ('UBUPQVMOLW85', '커플 이니셜 폰케이스 ❤', now() + 4, now() + 4, 5),
    ('U7WTRLLRUGXC', '🎁랜덤🎁2+1 스마트톡 가성비갑 2000원의 찐행복', now() + 5, now() + 5, 5),
    ('1H7RGLTUIJ49V', '재입고💕심플무드 볼드스트랩 체인 폰케이스 생일선물', now() + 6, now() + 6, 6),
    ('U7RALR4N361T', '🌈이니셜 커플 아이폰/갤럭시 컬러 폰케이스🌈', now() + 7, now() + 7, 7),
    ('1HRBEMY1KX25X', '❤️인물 스마트톡+폰케이스 커플 그립 생일선물 어버이날', now() + 8, now() + 8, 8),
    ('RHM5ZSN8I63M', '신상세트초특가할인♥ 잉크워터ver.3 커플 스마트톡', now() + 9, now() + 9, 9),
    ('QHSVNQ1KAPUP', '호밤고구마👍무농약인증🍠2021고구마플렉스‼', now() + 10, now() + 10, 11),
    ('VBK8DUBPVBCH', '37%할인🔥1+등급 한우 사골국 🔥48시간우려낸!', now() + 11, now() + 11, 13),
    ('VF998T8ZILGX', '🏆최우수작가상🏆쫀득쫀득 탱탱 전남장흥 \'무태장어\'', now() + 12, now() + 12, 15),
    ('URMVWXOXB8S1', '필름앨범📷 어버이날,커플,생일선물❤', now() + 13, now() + 13, 17),
    ('1IYEXLUTB0U2H', '어버이날 선물 🤩인간화환🤩 생일선물 커플 카네이션', now() + 14, now() + 14, 21),
    ('RLAM299CMHBL', '💮💮예약접수중/어버이날,스승의날 최고의 선물💮💮', now() + 15, now() + 15, 22);