<div align="center">

<h1>🔨 <b>밑바닥부터 구현하는 웹서버 (ver.TDD)</b> 🔨 </h1>

<b>웹서버의 내부 동작 원리가 궁금하다면 직접 만들어보자.</b>

<h6>" A programming language is for thinking about programs, not for expressing programs you've already thought of. It should be a pencil, not a pen."</h6>

</div>

<br>
<br>

## **[ INDEX ]**

### **파일 읽고 쓰기**
- 자바 API 이용하기
- text 파일 읽고 쓰기
- 이미지 파일 읽고 쓰기


### **네트워크**
- 소켓 생성하기
- InputStream, OutputStream 이용하기
- 웹 브라우저에 text 내보내기

### **HTTP (RFC 2616)**
- 웹 브라우저의 요청 데이터 읽기

- 읽어온 요청 데이터 파싱하기
  - request 구현
    - request line
    - request headers

- 쓰레드 개념과 이해

- request 의 책임
  - 책임: 웹 브라우저 요청 
  - 책임: 소켓 생성, 스트림 생성
  - request 의 역할은 어디까지인가? 역할 분리하기

- request handler 구현
  - HTTP Request Handler  
  프로그램이다.  
  URL 로 식별되고 HTTP 요청을 처리한다.  
  HTTP 호출에 의해 전송되는 데이터(URL 에 쿼리 스트링으로 코딩되는)를 수신하고 처리한다.  
  핸들러가 데이터를 처리하고 나면 요청한 사람에게 보낼 응답을 만든다.  
 
  - 소켓
  - InputStream
  - OutputStream

- 웹 브라우저의 요청이 POST 일 때
  -  POST 요청 할 뷰 만들기 
  -  HttpRequest 에서는 이 요청을 어떻게 처리할 것인가?

- 브라우저에 응답 보내기
  - HttpResponse 구현

<br>
<br>

## **[ Learning Target ]**
1. Junit을 이용해 **단위 테스트**하기
2. **TDD** 기반으로 프로그래밍 하기
3. **리팩토링** 하기

<br>
<br>

## **[ Reference ]**
- https://developer.mozilla.org/ko/docs/Web/HTTP/Messages