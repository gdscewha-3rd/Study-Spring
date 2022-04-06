# [01] 프로젝트 환경설정
## 프로젝트 생성

### 자바 설치 버전 확인

- 설정 - 앱 및 기능 → 앱목록에서 java 검색해서 버전 확인하기
- 명령 프롬프트 - `java -version`

[윈도우10 자바(Java) 버전 확인하기](https://mainia.tistory.com/6482)

### IntelliJ 설치

[[IntelliJ] Intellij 학생 인증, 무료 설치 방법](https://goddaehee.tistory.com/215)

[인텔리제이 다운로드 및 설치, IntelliJ IDEA 커뮤니티 에디션 설치하기](https://jhnyang.tistory.com/418)

+) 무설치 버전이란?

[https://dis1.tistory.com/5](https://dis1.tistory.com/5) 읽어보기

### 초기 프로젝트 생성

[Spring Initializr](https://start.spring.io/)

- **스프링 부트** 사용
- Project - Gradle Project
- Spring Boot 버전 - 숫자만으로 이루어진 버전 중 최신 버전 사용하기, (영문) 붙어있는 것은 정식 릴리즈 버전이 아님
- packaging - Jar
- Java - 11 버전
- Dependencies - Spring web, thymeleaf 사용

### 초기 프로젝트 파일 계층구조

- `.idea` : intelliJ가 사용하는 설정 파일들
- `gradle` - wrapper : gradle 쓰는 폴더
- `src - main, test`
    - `main` - `java`  : 실제 패키지랑 소스파일들을 포함
    - `main` - `resources` : 실제 자바 코드 파일을 제외한 나머지 포함 (xml, html, properties 등 설정 파일)
    - `test` : 테스트 코드들과 관련된 소스들이 들어감
    - 기본적으로 프로젝트 생성하면 `main`과 `test`폴더로 나누어져있음
        
        ⇒ 요즘 개발 트랜드에서 그만큼 테스트 코드가 중요하다는 의미
        
- `**build.gradle**`
    - 중요함
    - 버전 설정, 라이브러리 가져오는 역할
- `.gitignore`
    - git에 필요한 파일(소스코드 파일)만 올라가게 해줌

### main 함수 실행 결과

- `**Tomcat started on port portNum.**`
- `localhost:portNum`  접속
    - whitelabel ErrorPage 뜸 → 성공!
    - 실행 끄고 다시 접속하면 사이트에 연결할 수 없음 뜸
    

### mainintelliJ 번외

- 자바를 직접 실행 x , gradle 통해서 실행되는 경우가 있음
    - preference - buildTools - Gradle - buile and run using : intelliJ로 변경
    - preference - buildTools - Gradle - buile test using : intelliJ로 변경
    

## 라이브러리 살펴보기

### 라이브러리 의존관계

- gradle은 의존관계가 있는 라이브러리를 함께 다운로드함
- 지금은 모두 잘 알고 있기보다는 대표적인 라이브러리 몇개만 알아두기
- 실무에서는 로깅 위주로 사용 (강의에서는 system.out.println도 사용)

## View 환경설정

### welcome page 만들기

- resources/static/index.html 파일 생성하기
- static/index.html을 올려두면 welcompage(접속하면 처음 뜨는 화면) 기능 제공
- 템플릿 엔진 사용

### spring reference doc

- [spring.io](http://spring.io) → spring boot → documentation - 버전 찾아서 클릭

### 동작 환경 그림

## 빌드하고 실행하기

- 그전에는 intelliJ IDE 안에서 실행한 것
- 8080 서버 사용중이라고 뜸 ⇒ intelliJ IDE 안에서 실행했던건 중단하기
- 콘솔로 이동 → cmd로 이동
- ./gradlew → gradlew.bat 실행
- cmd에서 gradlew.bat 실행하는 방법 : gradlew
- gradlew build
