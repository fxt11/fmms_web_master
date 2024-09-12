package com.example.fmms.controller;

import com.example.fmms.entify.TeamPoints;
import com.example.fmms.entify.Teams;
import com.example.fmms.service.TeampointsService;
import com.example.fmms.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teampoints")
@Api(tags = "球队积分操作")
@CrossOrigin
public class TeamPointsController {
    @Autowired
    TeampointsService teampointsService;

    @SneakyThrows
    @PostMapping("/add")
    @ApiOperation(value = "球队积分信息新增")
    public Result regin(@RequestBody TeamPoints teamspoints,@RequestParam(defaultValue = "1") Integer pageNum){
        try{
            teampointsService.add(teamspoints);
        }catch (Exception e){
            return Result.error().message(e.getMessage());
        }
        return list(pageNum).message("修改成功");
    }

    @PutMapping("/update")
    @ApiOperation(value = "球队积分信息修改")
    public Result update(@RequestBody TeamPoints teamkey,@RequestParam(defaultValue = "1") Integer pageNum){
        try{
            teampointsService.update(teamkey);
        }catch (Exception e){
            return Result.error().message(e.getMessage());
        }
        return list(pageNum).message("修改成功");
    }


    @DeleteMapping("/delete")
    @ApiOperation(value = "球队积分信息删除")
    public Result delete(@RequestParam String teamkey){
        teampointsService.delete(teamkey);
        return Result.ok();
    }

    @GetMapping("/selectNum")
    @ApiOperation(value = "通过队名查询")

    public Result selectNum(@RequestParam String teamName) {
        return Result.ok().data("page",teampointsService.selectNum(teamName));
    }
    @GetMapping("/list")
    @ApiOperation(value = "球队积分信息分页显示")

    public Result list(@RequestParam(defaultValue = "1") int pageNum) {
        return Result.ok().data("page", teampointsService.page(pageNum));
    }
}
