# idus-market

idus's market service

이 프로젝트는 idus 인증서비스 및 회원정보, 주문정보 조회에 대한 API를 제공합니다.

---

### 1. 프로젝트 구성

- Spring Boot 2.4.4
- Gradle 6.7
- Swagger 2.9.2
- QueryDSL 4.4.0

### 2. 선행 조건

- JDK 11
- Gradle
- Docker
- Docker Compose (Windows에서는 Docker Desktop이 설치되어있다면, 이미 설치가 되어있습니다.)

### 3. 구성하기

#### 3.1. 컨테이너 구성

프로젝트 최상위 디렉터리 내 존재하는 "docker-compose.yml"에 작성된 스크립트 기반으로 도커 컨테이너가 자동 배포되고 실행됩니다.

```bash
$ docker-compose -f docker-compose.yml up -d
```

인증 토큰을 관리하는 Redis 서버와 관계형 데이터 저장목적의 MySQL가 자동으로 배포됩니다.

mysql 사용자 인증정보

|username | password |
|---------|----------|
|syaprk   |qwe1212!Q |

위 사용자는 MySQL 초기화 sql에 의해, idus_market_% 의 접근권한을 가지고있습니다.
```
참고) ./mysql-init-files/idus_market-database.sql
```




#### 3.2. 데이터베이스 샘플데이터

원활한 API 기능 테스트 수행을 위해, 사용자 및 주문 정보 샘플 데이터가 존재합니다. 아래 <표1>, <표2>는 샘플 데이터 표입니다.

<표1> 사용자 샘플데이터

| id | name        | nick  | password                                                             | phoneNumber   | email       | gender | roles                | createdAt                  | modifiedAt                 |
|----|-------------|-------|----------------------------------------------------------------------|---------------|-------------|--------|----------------------|----------------------------|----------------------------|
|  1 | 테스트Aa | testa | qwe1212!QQ                                                                | 010-0000-0000 | test@aa.a2  | Male   | ROLE_USER,ROLE_ADMIN | sql 작성 날짜 기준 | sql 작성 날짜 기준 |
|  2 | 테스트Bb | testb | qwe1212!QQ                                                                | 010-0000-0000 | test@aa.a3  | Female | ROLE_USER            | sql 작성 날짜 기준 | sql 작성 날짜 기준 |
|  3 | 테스트Cc | testc | qwe1212!QQ                                                                | 010-0000-0000 | test@aa.a4  | Male   | ROLE_USER            | sql 작성 날짜 기준 | sql 작성 날짜 기준 |
|  4 | 테스트Dd | testd | qwe1212!QQ                                                                | 010-0000-0000 | test@aa.a5  | Female | ROLE_USER,ROLE_ADMIN | sql 작성 날짜 기준 | sql 작성 날짜 기준 |
|  5 | 테스트Ee | teste | qwe1212!QQ                                                                | 010-0000-0000 | test@aa.a6  | Male   | ROLE_USER            | sql 작성 날짜 기준 | sql 작성 날짜 기준 |
|  6 | 테스트Ff | testf | qwe1212!QQ                                                                | 010-0000-0000 | test@aa.a7  |        | ROLE_USER,ROLE_ADMIN | sql 작성 날짜 기준 | sql 작성 날짜 기준 |
|  7 | 테스트Gg | testg | qwe1212!QQ                                                                | 010-0000-0000 | test@aa.a8  | Male   | ROLE_USER            | sql 작성 날짜 기준 | sql 작성 날짜 기준 |
|  8 | 테스트Hh | testh | qwe1212!QQ                                                                | 010-0000-0000 | test@aa.a9  |        | ROLE_USER            | sql 작성 날짜 기준 | sql 작성 날짜 기준 |
|  9 | 테스트Ii | testi | qwe1212!QQ                                                                | 010-0000-0000 | test@aa.a10 | Male   | ROLE_USER            | sql 작성 날짜 기준 | sql 작성 날짜 기준 |
| 10 | 테스트Jj | testj | qwe1212!QQ                                                                | 010-0000-0000 | test@aa.a11 |        | ROLE_USER            | sql 작성 날짜 기준 | sql 작성 날짜 기준 |
| 11 | 테스트Kk | testk | qwe1212!QQ                                                                | 010-0000-0000 | test@aa.a12 | Male   | ROLE_USER            | sql 작성 날짜 기준 | sql 작성 날짜 기준 |
| 12 | 테스트Ll | testl | qwe1212!QQ                                                                | 010-0000-0000 | test@aa.a13 | Female | ROLE_USER,ROLE_ADMIN | sql 작성 날짜 기준 | sql 작성 날짜 기준 |
| 13 | 테스트Mm | testm | qwe1212!QQ                                                                | 010-0000-0000 | test@aa.a13 |        | ROLE_USER            | sql 작성 날짜 기준 | sql 작성 날짜 기준 |
| 14 | 테스트Nn | testn | qwe1212!QQ                                                                | 010-0000-0000 | test@aa.a13 |        | ROLE_USER            | sql 작성 날짜 기준 | sql 작성 날짜 기준 |
| 15 | 테스트Oo | testo | qwe1212!QQ                                                                | 010-0000-0000 | test@aa.a13 | Male   | ROLE_USER            | sql 작성 날짜 기준 | sql 작성 날짜 기준 |
| 16 | 테스트Pp | testp | qwe1212!QQ                                                                | 010-0000-0000 | test@aa.a13 | Female | ROLE_USER,ROLE_ADMIN | sql 작성 날짜 기준 | sql 작성 날짜 기준 |
| 17 | 테스트Qq | testq | qwe1212!QQ                                                                | 010-0000-0000 | test@aa.a13 | Male   | ROLE_USER            | sql 작성 날짜 기준 | sql 작성 날짜 기준 |
| 18 | 테스트Rr | testr | qwe1212!QQ                                                                | 010-0000-0000 | test@aa.a13 | Female | ROLE_USER            | sql 작성 날짜 기준 | sql 작성 날짜 기준 |
| 19 | 테스트Ss | tests | qwe1212!QQ                                                                | 010-0000-0000 | test@aa.a13 | Male   | ROLE_USER            | sql 작성 날짜 기준 | sql 작성 날짜 기준 |
| 20 | 테스트Tt | testt | qwe1212!QQ                                                                | 010-0000-0000 | test@aa.a13 | Female | ROLE_USER            | sql 작성 날짜 기준 | sql 작성 날짜 기준 |


<표2> 주문 정보 샘플데이터

| id | orderId       | name                                                                           | createdAt                  | modifiedAt                 | userId |
|----|---------------|-------------------------------------------------|----------------------------|----------------------------|--------|
|  1 | 1JYDB23Z7Q9MF | 무료배송🏆 어버이날 카네이션 꽃 무드등 부모님선물       | sql 작성 날짜 기준 | sql 작성 날짜 기준 |      1 |
|  2 | US0Q0EX1HMJN  | 🍓크림치즈딸기 스콘🍓                                              | sql 작성 날짜 기준 | sql 작성 날짜 기준 |      2 |
|  3 | 1IAUULAKON22H | 인기💘마블대리석 톡+폰케이스 그립 커플 생일선물          | sql 작성 날짜 기준 | sql 작성 날짜 기준 |      3 |
|  4 | QDY8ZB72IA7N  | 나만의 문구를 새긴 꽃 스마트톡 그립 + 핸드폰케이스        | sql 작성 날짜 기준 | sql 작성 날짜 기준 |      4 |
|  5 | UBUPQVMOLW85  | 커플 이니셜 폰케이스 ❤                                              | sql 작성 날짜 기준 | sql 작성 날짜 기준 |      5 |
|  6 | U7WTRLLRUGXC  | 🎁랜덤🎁2+1 스마트톡 가성비갑 2000원의 찐행복               | sql 작성 날짜 기준 | sql 작성 날짜 기준 |      5 |
|  7 | 1H7RGLTUIJ49V | 재입고💕심플무드 볼드스트랩 체인 폰케이스 생일선물     | sql 작성 날짜 기준 | sql 작성 날짜 기준 |      6 |
|  8 | U7RALR4N361T  | 🌈이니셜 커플 아이폰/갤럭시 컬러 폰케이스🌈               | sql 작성 날짜 기준 | sql 작성 날짜 기준 |      7 |
|  9 | 1HRBEMY1KX25X | ❤️인물 스마트톡+폰케이스 커플 그립 생일선물 어버이날 | sql 작성 날짜 기준 | sql 작성 날짜 기준 |      8 |
| 10 | RHM5ZSN8I63M  | 신상세트초특가할인♥ 잉크워터ver.3 커플 스마트톡           | sql 작성 날짜 기준 | sql 작성 날짜 기준 |      9 |
| 11 | QHSVNQ1KAPUP  | 호밤고구마👍무농약인증🍠2021고구마플렉스‼                | sql 작성 날짜 기준 | sql 작성 날짜 기준 |     11 |
| 12 | VBK8DUBPVBCH  | 37%할인🔥1+등급 한우 사골국 🔥48시간우려낸!                  | sql 작성 날짜 기준 | sql 작성 날짜 기준 |     13 |
| 13 | VF998T8ZILGX  | 🏆최우수작가상🏆쫀득쫀득 탱탱 전남장흥 '무태장어'      | sql 작성 날짜 기준 | sql 작성 날짜 기준 |     15 |
| 14 | URMVWXOXB8S1  | 필름앨범📷 어버이날,커플,생일선물❤                           | sql 작성 날짜 기준 | sql 작성 날짜 기준 |     17 |
| 15 | 1IYEXLUTB0U2H | 어버이날 선물 🤩인간화환🤩 생일선물 커플 카네이션      | sql 작성 날짜 기준 | sql 작성 날짜 기준 |     18 |
| 16 | RLAM299CMHBL  | 💮💮예약접수중/어버이날,스승의날 최고의 선물💮💮     | sql 작성 날짜 기준 | sql 작성 날짜 기준 |     18 |

### 4. 빌드하기

이 프로젝트를 빌드하기 위해, gradle 커맨드를 사용하고 java 커맨드를 통해, 빌드 결과물 jar 파일을 실행합니다.

#### 4.1. Gradle Build & java -jar
Gradle 커맨드를 사용하여, 프로젝트 빌드를 수행합니다.
```bash
$ gradle build
```

Java 커맨드를 사용하여, 빌드 결과물 jar 파일을 실행합니다.
```bash
$ java -jar ./build/libs/market-1.0.0.jar
```

### 5. Swagger 사용법
Swagger 페이지 : http://localhost:8080/swagger-ui.html

#### 5.1 회원가입
유효 값에 따라 항목을 입력 후, 회원가입을 진행합니다.

#### 5.2 로그인
회원가입 시 작성한 이매일과 비밀번호를 입력하여 얻어진 message 키 값(X-Auth-Token)을 획득합니다.
획득한 값을 좌측 상단 Authorize 버튼을 클릭하여 입력합니다.
X-Auth-Token은 (bearer 공백 키값) 구성으로 작성합니다.

```
입력 예시)bearer eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJpc3MiOiJ0ZXN0QGFhLmE5IiwiZXhwIjoxNjE4NjQ4MTE0LCJpYXQiOjE2MTg2NDY5MTQsImVtYWlsIjoidGVzdEBhYS5hOSJ9.Hr8zeUPauJFIuRvRLkQxjMS5owVQxJ_OHPRiOYhmcRve6ShZyNDKaIOxZrnSrlfu5GeruI2_qEjwANhLGxNaWQ
```

#### 5.3 토큰
발급받은 토큰은 발급 시간 기준 15분동안 유효합니다.
토큰이 만료될 경우, 권한이 존재하는 API 호출시 HTTP STATUS 403 코드와 에러가 발생합니다.

#### 5.4 API 리스트
|API                |HTTP 메서드 | 권한                   |설명                            |
|-------------------|-----------|-----------------------|--------------------------------|
|/api/v1/auth/join  |POST      |Anonymous               |회원가입을 수행합니다              |
|/api/v1/auth/login |POST      |Anonymous               |로그인을 수행합니다                |
|/api/v1/auth/logout|POST      |Anonymous               |로그아웃을 수행합니다              |
|/api/v1/orders     |GET       |Admin                   |사용자의 주문정보를 조회합니다      |
|/api/v1/orders     |POST      |Authenticated Principle |로그인된 사용자의 주문을 등록합니다 |
|/api/v1/user       |GET       |Admin                   |사용자 목록을 조회합니다           |
|/api/v1/email      |GET       |Admin                   |사용자를 조회합니다               |