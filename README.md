# recipe
초기 자본이 많이 들어가는 플랫폼 사업 형태의 BM을 구상하고
이를 바탕으로 제작한 웹 프로젝트 입니다..

사용된 기능
open-jdk 19 / spring-boot 3.0.2 / Oracle / thymeleaf / hibernate JPA
Html, css, javascript, JQuery, BootStrap
eclipese2022-12, intelij 2022.3.1

2023-02-15 초기 형태 완성

2023-02-16 OAuth2 로그인 도입 시도 -> AuthenticationPrincipal 사용이 제한되어서 
각종 기능과 뷰 페이지에 다수의 수정이 필요한 관계로 주석형태로 기능만 남겨둠 
pom의 oauth2 의존성 주입, security폴더의 SecurityOauth2Service 주석취소,
SecurityConfig 의 주석부분 변경
apprecation.properties의 Oauth2주석 취소
프론트의 header 부분 주석 수정을 통해서 로그인 방식 변경 가능

2023-02-20 Oauth2 로그인에 대해 더 학습하여서 적용완료
UserDetail부분을 일반 세션 로그인과 묶는 방식을 통해서 Oauth2 로그인에서도 세션저장소에
객체를 저장하도록 수정함

2023-02-21~24 로그인 메일 확인 로직 추가
기타 데이터 삽입 및 html 수정을 통해 편의성 개선

2023-02-27 실시간 알림기능을 추가
기능 중 레시피의 댓글 부분만 구현해놓음
