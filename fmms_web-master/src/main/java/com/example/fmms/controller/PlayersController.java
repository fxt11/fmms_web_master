package com.example.fmms.controller;



import com.example.fmms.entify.Players;
import com.example.fmms.service.PlayersService;

import com.example.fmms.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/players")
@Api(tags = "球员操作")
@CrossOrigin
public class PlayersController {
    @Autowired
    PlayersService playersService;

    @SneakyThrows
    @PostMapping("/add")
    @ApiOperation(value = "球员信息新增")
    public Result regin(@RequestBody Players players,@RequestParam(defaultValue = "1") Integer pageNum){
        try{
            playersService.add(players);
        }catch (Exception e){
            return Result.error().message(e.getMessage());
        }
        return list(pageNum).message("修改成功");
    }

    @PutMapping("/update")
    @ApiOperation(value = "球员信息修改")
    public Result update(@RequestBody Players players,@RequestParam(defaultValue = "1") Integer pageNum){
        try{
            playersService.update(players);
        }catch (Exception e){
            return Result.error().message(e.getMessage());
        }
        return list(pageNum).message("修改成功");
    }


    @DeleteMapping("/delete")
    @ApiOperation(value = "球员信息删除")
    public Result delete(@RequestParam String playerkey){
        playersService.delete(playerkey);
        return Result.ok();
    }

    @GetMapping("/selectNum")
    @ApiOperation(value = "通过人名查询")

    public Result selectNum(@RequestParam String playerName) {
        return Result.ok().data("page",playersService.selectNum(playerName));
    }

    @GetMapping("/selectTeam")
    @ApiOperation(value = "通过球队查询")

    public Result selectTeam(@RequestParam String team) {
        return Result.ok().data("page",playersService.selectNum(team));
    }

    @GetMapping("/selectNation")
    @ApiOperation(value = "通过国家查询")

    public Result selectNation(@RequestParam String nationality) {
        return Result.ok().data("page",playersService.selectNum(nationality));
    }

    @GetMapping("/list")
    @ApiOperation(value = "球队信息分页显示")

    public Result list(@RequestParam(defaultValue = "1") int pageNum) {
        return Result.ok().data("page", playersService.page(pageNum));
    }
}
