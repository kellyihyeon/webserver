<div align="center">

<h1>🔨 <b>밑바닥부터 구현하는 웹서버 (ver.TDD)</b> 🔨 </h1>

<b>웹서버의 내부 동작 원리가 궁금하다면 직접 만들어보자.</b>

<h6>"A programming language is for thinking about programs, not for expressing programs you've already thought of. It should be a pencil, not a pen."</h6>

</div>

<br>
<br>

## **[ CONTENT ]**

### **파일 읽고 쓰기**
- 자바 API 이용하기
- text 파일 읽고 쓰기
- 이미지 파일 읽고 쓰기
<br>

### **네트워크**
- 자바 서버 소켓으로 통신하기
- 스트림 가져오기
- 웹 브라우저에 text 내보내기
<br>

### **HTTP (RFC 2616)**
- 웹 브라우저의 요청 데이터 읽기

- 읽어온 요청 데이터 파싱하기
  - request 구현
    - request line
    - request headers
    - request body (YAGNI)

- `쓰레드` 개념과 이해

- response 구현
  - status line
  - response headers

- 웹 브라우저의 요청이 POST 일 때
  - post 요청으로 보낸 데이터는 어디에 있을까?
  - post 요청 할 뷰 만들기 
  - HttpRequest 에서는 이 요청을 어떻게 처리할 것인가?

- 클라이언트가 get 요청으로 데이터를 보낸다면?
  - 이 데이터를 어떻게 가져올 것인가?
<br>

### **Web Server**
- 요청 handler 구현
  - HTTP Request Handler 정의 참고  
  ```text
  프로그램이다.  
  URL 로 식별되고 HTTP 요청을 처리한다.  
  HTTP 호출에 의해 전송되는 데이터(URL 에 쿼리 스트링으로 코딩되는)를 수신하고 처리한다.  
  핸들러가 데이터를 처리하고 나면 요청한 사람에게 보낼 응답을 만든다.  
  ```
  - 소켓
  - 스트림

- Dispatcher 구현
  - Front Controller:  
    A controller that handles all requests for a web site
  - v0. happy path:  
    - 클라이언트의 요청에 대한 서버의 적절한 응답을 매핑해주는 역할을 하는 객체에 요청에 대한 응답 컨트롤러를 반환할 것을 요청
    - 요청 handler에 컨트롤러 반환

- 편의성을 위해 만드는 객체
  - 클라이언트가 스태틱 파일을 요청한다면?
  
  - 클라이언트의 '/welcome' 요청에 대해 static 파일을 응답해주고 싶다면?
  
  - v1. staticFile 요청 (/home.html)    
      - 스태틱 파일 서빙하는 객체  
  - v2. 리소스 요청 (/welcome)  
      - 사용자 정의 컨트롤러 반환하는 객체

  - FileReadUtil 객체 하나 만들어서 파일 읽어오기

- 요청 Resolver를 통합해서 적절한 Resolver를 반환해주는 객체 필요
<br>  

### **간단한 서비스 만들기**
- user A가 요청을 보낸다면 서버에서는 user A를 어떻게 다른 사용자와 구별할 수 있을까?
<br>

### **Stateful**
- 쿠키 만들기
  - 쿠키가 무엇인지?
  - 쿠키는 어디에?  
  ```text
  request headers.
  Idea-sample=d1e23a4b-c5bb-67s8-94a4-asdfasdf342
  ```
  
- 세션 만들기
  - 세션이 무엇인지?
<br>
<br>

## **[ 핵심 ]**
- HTTP 스펙을 만족하는 서버를 만들자.
- HTTP 요청 분석
- HTTP 응답
- 세션
- 다중 요청 처리 
  - 멀티스레드
  - 스레드 풀
  - 스레드 로컬
<br>
<br>

## **[ TO DO ]**
- [ ] 깨진 테스트 코드 수정
- [ ] 트러블 슈팅 - favicon 해결
- [x] README 정리
- [ ] 컨텍스트로 묶어보기
- [ ] 스레드 로컬 이해하고, 적용해보기
- [ ] NotFoundController를 매번 생성해서 내려주는 부분 리팩토링
- [ ] StaticFileController   
      서버 -> 웹브라우저로 데이터 보내서 파싱 가능한지?
- [ ] Cookie   
      Optional 설정 추가 (만료일 · 지속시간, 특정 도메인 및 경로 제한 설정)
- [ ] 스레드 풀: 고정 스레드풀 -> 코어, 맥시멈, 대기시간, 큐 설정
- [ ] Composite pattern 이해하고 리팩토링 해보기
- [ ] Trouble Shooting 2번, 3번 해결하고 기록하기
<br>
<br>

## **[ Learning Target ]**
1. Junit을 이용해 **단위 테스트**하기
2. **리팩토링** 하기
3. **TDD** 기반으로 프로그래밍 하기 (⭐최종 목표)
<br>
<br>


## **[ Troubleshooting ]**
- [1. URL에 쿼리 스트링이 없을 때](https://github.com/kellykang-tech/Troubleshooting/blob/main/URL%EC%97%90-%EC%BF%BC%EB%A6%AC%EC%8A%A4%ED%8A%B8%EB%A7%81%EC%9D%B4-%EC%97%86%EC%9D%84-%EB%95%8C-NullPointerException.md)
- [2. 요청이 왜 무시되지?]()
- [3. 매번 객체를 새로 생성할 필요가 없는데 어떻게 리팩토링 하지?]()
<br>
<br>


## **[ Reference ]**
### **HTTP**
- https://developer.mozilla.org/ko/docs/Web/HTTP/Messages
- https://developer.mozilla.org/ko/docs/Web/HTTP/Status
- https://developer.mozilla.org/ko/docs/Web/HTTP/Redirections
- https://developer.mozilla.org/ko/docs/Web/HTTP/Basics_of_HTTP/MIME_types
- https://developer.mozilla.org/ko/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types
- https://datatracker.ietf.org/doc/html/rfc2616

### **Cookie**
- https://developer.mozilla.org/ko/docs/Web/HTTP/Cookies
- https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/Set-Cookie
- https://docs.oracle.com/javaee/7/api/javax/servlet/http/Cookie.html
- https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/Set-Cookie#permanent_cookie
- https://docs.oracle.com/javaee/7/api/javax/servlet/http/Cookie.html
- https://datatracker.ietf.org/doc/html/rfc6265

### **Session**
- https://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpSession.html

### **Web Server**
- http://thierryroussel.free.fr/java/books/martinfowler/www.martinfowler.com/isa/frontController.html
- https://en.wikipedia.org/wiki/URL_redirection


### **Thread**
- https://www.javatpoint.com/java-threadlocal-class
- https://www.javatpoint.com/java-thread-pool
- https://levelup.gitconnected.com/lets-learn-java-threads-e156481883cb

### **ETC**
- https://www.srihash.org/
- https://ko.wikipedia.org/wiki%EC%BB%B4%ED%8F%AC%EC%A7%80%ED%8A%B8_%ED%8C%A8%ED%84%B4