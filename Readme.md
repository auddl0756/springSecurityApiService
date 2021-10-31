# Spring security api server 연습
## 참고 
코드로 배우는 스프링부트 part 5

## 목적
1. 도대체 토큰이 뭔지?<br>
   JWT가 뭔지<br>
   spring security랑 토큰이 뭔 상관인지?
   
2. api 서버가 특별한 뭔가가 있는 것인지?<br>
   그냥 http api 제공하는 서버랑 뭐가 다른지?

3. api 서버에서 어떤 보안과 인증이 필요한지?<br>
   spring security가 어떻게 api 서버에서 보안,인증 처리해주는지

4. 필터는 어떻게 쓰이는지

## 핵심 포인트
- 인증(Authentication) vs 인가(Authorization)
- access denied(403,Forbidden) vs bad credential(401,Unauthorized)

## 부족한 부분
- CSRF 원리
- spring security는 httpSession을 이용한다?
- jpa fetch type LAZY 꼭 써야 할까?<br>
 eager로 하면 연관된거 다 가져오긴 할텐데<br>
- api 예외 처리? (예를 들어 transaction rollback 됐을 때)  <br>
일단 단순하게 개발하자.

## 기타 특이 사항
### 설정
- application.properties는 commit 하지 않았음
- mysql 연결 시 8버전 부터 serverTimeZone 설정해줘야 했음
  
### 구현
- controller에서 void 리턴보단 명시적으로 string 리턴하는게 나은 듯
- jpa entity에서 enum 타입 속성은 @Enumerated 붙이고 String으로 저장하도록 옵션 주자.
- JPA에서 @EntityGraph는 왜 쓰는건지?
- delete도 트랜잭션 설정해야 겠지?<br>
  delete 시에 격리 수준이 줄 영향 잘 상상 안 됨

