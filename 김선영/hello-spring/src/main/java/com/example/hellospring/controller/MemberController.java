package com.example.hellospring.controller;

import com.example.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService meberService;

    @Autowired
    public MemberController(MemberService meberService) {
        this.meberService = meberService;
    }
}
