## Section 5. 회원 관리 에제_웹 MVC 개발
### 회원 웹 기능_홈 화면 추가
**hello.hellospring/controller/HomeController**
```java
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
```
<br>

**resources/templates/home.html**
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<body>

<div class="container">
    <div>
        <h1>Hello Spring</h1>
        <p>회원 기능</p>
        <p>
            <a href="/members/new">회원 가입</a> 
            <a href="/members">회원 목록</a>
        </p>
    </div>
</div> <!-- /container -->

</body>
</html>
```
<br>

**결과 화면**

<img width="397" alt="스크린샷 2022-04-06 오후 6 57 40" src="https://user-images.githubusercontent.com/80838501/161950025-44da6d00-acf8-4c4a-ba87-2a66f8dd1439.png">

<br>
<br>
<br>

### 회원 웹 기능_등록
#### 1. 형태(껍데기) 만들기
**hello.hellospring/controller/MemberController**
```java
@GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
```
→ createMemberForm.html으로 연결
<br>
<br>

**resources/templates/createMemberForm.html**
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<body>

<div class="container">
    <form action="/members/new" method="post">
        <div class="form-group">
            <label for="name">이름</label>
            <input type="text" id="name" name="name" placeholder="이름을 입력하세요">
        </div>
        <button type="submit">등록</button> 
    </form>
</div> <!-- /container -->

</body>
</html>
```
<br>

**결과 화면**

<img width="400" alt="스크린샷 2022-04-06 오후 6 59 40" src="https://user-images.githubusercontent.com/80838501/161950280-228fa5f3-4fad-4d47-ad01-e3be5c82d10f.png">

→ 이름을 입력하고 등록 버튼을 누르면, name이라는 이름의 key와 입력한 value가 서버로 넘어간다.
<br>
<br>
<br>

#### 2. Controller 만들기
**hello.hellospring/controller/MemberForm**
```java
public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```
<br>

**hello.hellspring/controller/MemberController**
```java
@GetMapping("/members/new") //회원 가입
    public String createForm() {
        return "members/createMemberForm";
    }

@PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName()); //전달받은 form에서 getName을 통해 입력한 name값 가져오기

        memberService.join(member); //회원 가입

        return "redirect:/"; //
    }
```
→ 데이터를 등록할 때는 Post, 조회할 때는 Get을 사용한다.
<br>
<br>
<br>


### 회원 웹 기능_조회
**hello.hellospring/controller/MemberController**
```java
@GetMapping("/members") //회원 목록
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); //model에 회원 list 담기

        return "members/memberList";
    }
```
→ member list 자체를 model에 담아서 View template에 넘긴다.
<br>
<br>

**resources/templates/members/memberList.html**
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<div class="container">
    <div>
        <table>
            <thead>
            <tr>
                <th>#</th>
                <th>이름</th> </tr>
            </thead>
            <tbody>
            <tr th:each="member : ${members}"> //모든 member들 루프 둘기
                <td th:text="${member.id}"></td> //id와
                <td th:text="${member.name}"></td> //name 출력
            </tr>
            </tbody>
        </table>
    </div>
</div> <!-- /container -->
</body>
</html>
```
<br>
<br>

**결과 화면**

<img width="443" alt="스크린샷 2022-04-06 오후 7 24 41" src="https://user-images.githubusercontent.com/80838501/161954772-8cf47327-dc78-4658-8fc7-3cceb288cc21.png">

<br>

**렌더링된 모습**

<img width="308" alt="스크린샷 2022-04-06 오후 7 32 18" src="https://user-images.githubusercontent.com/80838501/161955963-260c9e80-79b9-44f4-9499-024983bd1e97.png">

<br>

**주의)**
```
현재 memory를 사용하고 있기 때문에, 서버를 내리면 데이터가 다 지워진다. 따라서 데이터들을 파일이나 데이터베이스에 저장해야 한다.
```
