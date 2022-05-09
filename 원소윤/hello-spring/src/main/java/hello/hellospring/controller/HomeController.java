package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //정적 파일보다 controller 우선순위가 높음
    @GetMapping("/")
        public String home() {
            return "home";
    }
}
