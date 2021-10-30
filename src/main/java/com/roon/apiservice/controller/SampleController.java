package com.roon.apiservice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@RequestMapping("/sample/")
@Controller
public class SampleController {
    @GetMapping("/all")
    public String all(){
       log.info("모두 접근 가능한 페이지");
       return "all";
    }

    @GetMapping("/member")
    public String member(){
        log.info("멤버 이상 권한만 접근 가능한 페이지");
        return "member";
    }

    @GetMapping("/admin")
    public String admin(){
        log.info("관리자만 접근 가능한 페이지");
        return "admin";
    }
}
