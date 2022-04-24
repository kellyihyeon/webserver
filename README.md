<div align="center">

<h1><b>HTTP Web Server</b> </h1>

![web-server](https://user-images.githubusercontent.com/73330352/164957623-d6935f1d-171d-4894-9c9f-2afdbbf710fb.png)

</div>

<br>
<br>

## **🚀 프로젝트 소개**
- 웹 서버가 어떻게 동작을 하는지 이해하기 위해서 밑바닥부터 직접 스펙을 구현해보았습니다.
- 구현한 웹 서버를 이용해 세션방식의 로그인 기능을 만들어보았습니다.
- 구현 프로세스와 코드가 리팩토링 되는 과정을 기록하였습니다.
  - 👉🏼 https://github.com/kellykang-tech/webserver/blob/main/PROCESS.md

- 개발 기간
  - 2022.02.02 ~ 현재 (구현은 완료, 리팩토링 진행 중)
- JDK 1.8, JUnit5
<br>
<br>

## **🚀 개발 내용**
- 자바 서버 소켓을 활용한 서버 통신
  - 클라이언트와 서버의 통신을 위해 자바 ServerSocket 사용

- RFC 2616 스펙을 만족하는 HTTP request parser
  - HTTP Request Message 스펙에 맞춰 클라이언트의 요청을 parsing

- RFC 2616 스펙을 만족하는 HTTP response builder
  - HTTP Response Message 스펙에 맞춰 response 구조 빌딩
  
- 스레드 풀을 활용한 다중 요청 처리
  - 클라이언트의 다중 요청을 처리하기 위해 멀티 스레드 사용
  - 스레드를 관리하기 위해 스레드 풀 생성

- 세션 처리를 위한 쿠키 구현
  - 클라이언트를 식별하고 그 상태를 유지 하기 위해 세션과 쿠키를 구현
  - SessionManager를 생성하고, SessionManager가 세션의 라이프 사이클을 관리하도록 함

- 직접 구현한 웹 서버로 세션 방식 로그인 구현
  - 사용자가 로그인 했을 때 세션을 부여하고, 다음 요청 시 쿠키의 세션 id를 확인해 같은 사용자가 맞는지 확인
  
- 웹 서버 사용 편의성을 위한 부가기능 제공
  - 스프링의 DispatcherServlet처럼 Front Controller 패턴을 활용한 DispatcherServlet 생성
  - 사용자 정의 컨트롤러
  - 스태틱 파일을 서빙해주는 컨트롤러
  - 잘못된 경로에 대한 처리를 하는 컨트롤러

- 기능 검증을 위한 단위 테스트 작성
  - domain layer, service layer 메소드의 기능 검증을 위해 단위 테스트 작성

<br>
<br>

## **🚀 프로젝트 주요 관심사**
- HTTP 스펙을 만족하는 서버 구현하기
- HTTP RFC 2616 요청 스펙에 맞춰서 요청 객체 만들기
- HTTP RFC 2616 응답 스펙에 맞춰서 응답 객체 만들기
- 세션과 세션 처리를 위한 세션 매니져
- 다중 요청 처리 관련해서 공부해야 할 개념
  - 멀티 스레드
  - 스레드 풀
- service layer 단위 테스트 코드 작성하기

<br>
<br>

## **🚀 프로젝트 디렉토리 구조**
🟢interface 🔵class
```
📁src
    📁main
        📁java
            📁com.github.kelly
                📁controller
                    🟢Controller
                    🔵LoginContoller
                    🔵LogOutController
                    🔵SignUpController
                    🔵WelcomeController
                📁domain
                    🔵Member
                    🔵MemberRepository
                📁http
                    📁cookie
                        🔵Cookie
                        🔵CookieParser
                        🔵CookieTypes
                    📁request
                        🔵HttpMethod
                        🔵HttpRequest
                        🔵QueryString
                        🔵RequestBody
                        🔵RequestHeaders
                        🔵RequestLine
                    📁response
                        🔵HttpResponse
                        🔵HttpStatus
                    📁session
                        🔵Session
                        🔵SessionManager
                📁utils
                    🔵FileReadUtil
                📁webserver
                    📁controller
                        🔵NotFoundController
                        🔵StaticFileController
                    📁dispatcher
                        🔵DispatcherServlet
                        🔵RequestKey
                        🟢RequestResolver
                        🔵RequestResolverManager
                        🔵StaticFileRequestResolver
                        🔵UserDefinedRequestResolver
                    🔵HttpRequestHandler
                    🔵Webserver
                🔵Application

    📁test
        📁java
            📁com.github.kelly
                📁domain
                    🔵MemberRepositoryTest
                    🔵MemberTest
                📁http
                    📁cookie
                        🔵CookieParserTest
                        🔵CookieTest
                    📁request
                        🔵HttpRequestTest
                        🔵QueryStringTest
                        🔵RequestBodyTest
                        🔵RequestHeadersTest
                        🔵ReuqestLineTest
                    📁response
                        🔵HttpResponseTest
                    📁session
                        🔵SessionManagerTest
                        🔵SessionTest
                    📁webserver.dispatcher
                        🔵DispatcherServletTest
                        🔵RequestResolverManagerTest
                        🔵StaticFileRequestResolverTest
                        🔵UserDefinedRequestResolverTest
```