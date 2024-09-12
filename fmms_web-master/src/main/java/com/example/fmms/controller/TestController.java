package com.example.fmms.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @Resource
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/list")
    public List<Map<String, Object>> userList() {
        String sql = "select * from users";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        return result;
    }
}
