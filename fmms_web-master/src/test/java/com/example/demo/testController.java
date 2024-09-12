package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class testController {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @GetMapping("/list")
    public List<Map<String, Object>> userList() {
        String sql = "select * from user";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        return result;
    }
}
