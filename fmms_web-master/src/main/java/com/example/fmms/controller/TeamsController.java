package com.example.fmms.controller;

import com.example.fmms.entify.Teams;
import com.example.fmms.entify.User;
import com.example.fmms.entify.request.LoginRequest;
import com.example.fmms.service.TeamsService;
import com.example.fmms.service.UserService;
import com.example.fmms.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
@Api(tags = "球队操作")
@CrossOrigin
public class TeamsController {
    @Autowired
    TeamsService teamsService;

    @SneakyThrows
    @PostMapping("/add")
    @ApiOperation(value = "球队信息新增")
    public Result regin(@RequestBody Teams teams,@RequestParam(defaultValue = "1") Integer pageNum){

        try{
            teamsService.add(teams);
        }catch (Exception e){
            return Result.error().message(e.getMessage());
        }
        return list(pageNum).message("修改成功");
    }

    @PutMapping("/update")
    @ApiOperation(value = "球队信息修改")
    public Result update(@RequestBody Teams teamkey,@RequestParam(defaultValue = "1") Integer pageNum){
        try{
            teamsService.update(teamkey);
        }catch (Exception e){
            return Result.error().message(e.getMessage());
        }
        return list(pageNum).message("修改成功");
    }


    @DeleteMapping("/delete")
    @ApiOperation(value = "球队信息删除")
    public Result delete(@RequestParam String teamkey){
        teamsService.delete(teamkey);
        return Result.ok();
    }

    @GetMapping("/selectNum")
    @ApiOperation(value = "通过队名查询（中英都行）")

    public Result selectNum(@RequestParam String teamName) {
        return Result.ok().data("page",teamsService.selectNum(teamName));
    }
    @GetMapping("/list")
    @ApiOperation(value = "球队信息分页显示")

    public Result list(@RequestParam(defaultValue = "1") int pageNum) {
        return Result.ok().data("page", teamsService.page(pageNum));
    }

}
