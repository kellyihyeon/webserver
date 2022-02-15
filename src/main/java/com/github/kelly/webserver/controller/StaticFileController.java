package com.github.kelly.webserver.controller;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;
/**
 * 역할:
 *  요청 들어온 파일을 읽어서 웹 브라우저의 응답으로 내보낸다.
 *
 */
public class StaticFileController implements Controller{

    /**
     * this:
     * request line 을 읽는다 -> HttpRequest.requestLine 구현
     * requestLine.getUrl(); -> /home.html

     * POST /signUp.html 로 요청하는 경우는? 없다. GET /signUp.html -> 회원가입 폼이 있는 창 반환 -> 폼에 데이터 채워서 POST /signup 으로 요청해야 함.
     * resource - static - home.html 파일이 있는지 검사해야 한다.
     * file read -> buffer 로 읽는다.
     * /welcome -> WelcomeController ( -> redirect(/home.html) -> HomeController 이렇게 하자.
     * HomeController 멤버필드에 body 를 두고 읽은 buffer 를 body 에 설정해둔다.
     */

    // 1. 파일 확장자부터 뜯기. 마켓컬리에는 index.php 도 있었다.
    //    근데 왜 뜯어야 하는가? 컨트롤러에 마임타입 넘겨주려고.
    // 2. 파일 데이터 읽고 바인딩 해놓기
//    private void readContents() {
//        String path = Objects.requireNonNull(this.getClass().getResource("/static")).getFile();
//        String url = httpRequest.getRequestLine().getUrl(); // /home.html
//
//        File staticFile = new File(path + url);
//
//        try {
//            if (staticFile.exists()) {  // test.html -> 있는 경우: TestController 로 연결해줘야 하잖아.
//                // 얘도 map 이 있어야 되구나... test.html -> TestController
//                //                           home.html -> HomeController
//                // home.html, HomeController 넣을 것인지, home, html, HomeController 넣을 것인지?
//                // static 파일 요청한 친구들은 -> response 에 Content-Type 에 저 확장자에 따라서 응답이 달라지는데?? 아오....
//                // static Resolver 는 성격이 좀 다르다. 이거는 support 하면 StaticController 를 연결해서
//                // StaticController 에 mime 타입이랑 바디 넘겨주고 이 데이터를 process 에서 가공하게끔 만들어놓고 handler 에 return 해야 할 것 같은데?
//                List<String> list = Files.readAllLines(staticFile.toPath());    // 이거를 Controller 에 줘야 뿌려줄 수 있지 않나?
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }


    @Override
    public void process(HttpRequest httpRequest, HttpResponse httpResponse) {
        System.out.println("StaticFileController.process");

    }
}
