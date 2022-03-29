# Spring 입문 스터디
## 1주차 - [입문] 섹션 0,1,2
### 프로젝트 환경설정
- https://start.spring.io/  
스프링 부트 기반으로 스프링 관련된 프로젝트를 만들어주는 사이트.  
해당 사이트에서 원하는 언어, 옵션을 선택해서 프로젝트를 생성할 수 있다.  
Dependencies에서 Spring Web과 Thymeleaf를 추가했다.  

- Thymeleaf  
HTML을 만들어주는 템플릿 엔진.

- 파일을 다운로드 받은 후, intelliJ에서 실행했다.  
환결설정이 마무리 되었다면, src\main\java\hello.hellospring\HelloSpringApplication.java
를 Run 하면 실행할 수 있다.  
접속은 localhost:8080 

- 라이브러리끼리 의존이 되어있는데, 프로젝트에서 이를 관리해준다.  
따라서 라이브러리 하나를 다운받아도 딸려오는 라이브러리들이 많이 존재..
- System.out.println은 거의 안 쓴다고 한다.  
logging에 대해 알고 싶으면 slf4j와 logback에 대해 검색해보자


#### view 만들기
src\main\resources\static 에 index.html을 만들어 월컴 페이지를 생성하자  
src\main\java\hello.hellospring 아래에 controller 라는 패키지를 생성했다  
그리고 그 아래 HelloController 라는 자바 클래스를 생성했다  

그리고 resource\templates로 가서 hello.html을 생성하자  
`<html xmlns:th="http://www.thymeleaf.org">` 를 추가하면 타임리프 문법을 쓸 수 있다.  

내장된 톰캣 서버가 url을 읽고 스프링에게 물어본다.  
그리고 컨트롤러의 `@GetMapping("hello")`와 url이 매칭이 된다.  
그러면 그 메소드를 불러오는데, 이때 스프링이 모델을 만들어서 메소드에 넣어준다.  
모델에는 데이터와 값를 넣고 리턴을 한다.  `return "hello";`를 하면 templates\hello.html을 부른 것과 같다.  
이 역할을 하는 것이 뷰 리졸버이다.  

spring-boot-devtools 이용하면 서버 재시작 없이 html 컴파일로만으로도 view 파일 변경이 가능하다.  

cmd창에서 빌드를하고, 만들어진 빌드 파일\lib 에서  
`. java -jar hello-spring-0.0.1-SNAPSHOT.jar`를 실행하면 실행이 된다.  
배포할때도 이 파일만 올려서 실행하면 된다고 하니 Good ~  

### 스프링 웹 개발 기초


