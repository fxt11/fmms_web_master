package com.example.fmms.controller;

import com.example.fmms.entify.request.LoginRequest;
import com.example.fmms.entify.Admin;
import com.example.fmms.service.AdminService;
import com.example.fmms.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/admin")
@Api(tags = "管理员操作")
public class AdminController {


    @Autowired
    AdminService adminService;

    @PostMapping ("/login")
    @ApiOperation(value = "管理员登录")
    public Result login(@RequestBody LoginRequest request) {
        String token=adminService.login(request);
        return Result.ok().data("token", token);
    }
    @PostMapping("/regin")
    @ApiOperation(value = "管理员注册")
    public Result regin(@RequestBody Admin admin){
        adminService.regin(admin);
        return Result.ok();
    }
    @PutMapping("/update")
    @ApiOperation(value = "管理员信息更新")
    public Result update(@RequestBody Admin admin){
        adminService.update(admin);
        return Result.ok();
    }
    //    @GetMapping("/{uname}")
//    public Result enquiry(@RequestBody String uname){
//        User user =userService.enquiry(uname);
//        return Result.success(user);
//    }
    @DeleteMapping("/delete")
    @ApiOperation(value = "管理员信息删除")
    public Result delete(@RequestParam String uname){
        adminService.delete(uname);
        return Result.ok();
    }
    @GetMapping("/selectNum")
    @ApiOperation(value = "通过姓名查询")
    //前端发送当前页，查询姓名
    public Result selectNum(@RequestParam String name) {
        return Result.ok().data("page",adminService.selectName(name));
    }
    @GetMapping("/list")
    @ApiOperation(value = "人员信息分页显示")
    //前端发送查询姓名
    public Result list(@RequestParam(defaultValue = "1") int pageNum) {
        return Result.ok().data("page", adminService.page(pageNum));
    }


    //登出按钮,清空token
    @PostMapping("/logout")
    @ApiOperation(value = "登出")
    public Result logout() {
        return Result.ok();
    }

}
