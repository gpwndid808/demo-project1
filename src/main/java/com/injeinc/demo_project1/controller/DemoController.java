package com.injeinc.demo_project1.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.injeinc.demo_project1.entity.Board;
import com.injeinc.demo_project1.service.DemoService;

@RestController
public class DemoController {

    private final DemoService demoservice;
    
    public DemoController(DemoService demoservice) {
        this.demoservice = demoservice;
    }

    @GetMapping("/list")
    public List<Board> retvLstBoard() {
        return demoservice.retvLstBoard();
    }
}
