package com.example.fmms.controller;
import com.example.fmms.entify.request.LoginRequest;
import com.example.fmms.entify.User;
import com.example.fmms.service.AdminService;
import com.example.fmms.service.UserService;
import com.example.fmms.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/user")
@Api(tags = "用户操作")
@CrossOrigin
public class UserController {
   @Autowired
   UserService userService;
    @Autowired
   AdminService adminService;
    @SneakyThrows
    @PostMapping ("/login")
    @ApiOperation(value = "用户登录")
    public Result login(@RequestBody LoginRequest request) {
        String token=adminService.login(request);
        return Result.ok().data("token", token);
    }

    @PostMapping("/regin")
    @ApiOperation(value = "用户注册")
    public Result regin(@RequestBody User user){
        userService.regin(user);
        return Result.ok();
    }
    //用户信息修改
    @PutMapping("/update")
    @ApiOperation(value = "用户信息修改")
    public Result update(@RequestBody User user){
        userService.update(user);
        return Result.ok();
    }
    //用户信息删除
    @DeleteMapping("/delete")
    @ApiOperation(value = "用户信息删除")
    public Result delete(@RequestParam String uname){
        userService.delete(uname);
        return Result.ok();
    }
    //用户头像
    @GetMapping("/info")
    @ApiOperation(value = "头像")
    public Result info(String token) {
        return userService.info(token);
    }
    @GetMapping("/selectNum")
    @ApiOperation(value = "通过姓名查询")
    //前端发送当前页，查询姓名
    public Result selectNum(@RequestParam String uname) {
        return Result.ok().data("page",userService.selectName(uname));
    }
    @GetMapping("/list")
    @ApiOperation(value = "人员信息分页显示")
    //前端发送查询姓名
    public Result list(@RequestParam(defaultValue = "1") int pageNum) {
        return Result.ok().data("page", userService.page(pageNum));
    }

    //登出按钮,清空token
    @PostMapping("/logout")
    @ApiOperation(value = "登出")
    public Result logout() {
        return Result.ok();
    }
}
