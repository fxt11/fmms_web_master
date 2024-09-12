package com.example.fmms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fmms.entify.RaceSchedule;
import com.example.fmms.service.RaceScheduleService;
import com.example.fmms.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/RaceSchedule")
@Api(tags = "赛事管理")
public class RaceScheduleController {
    @Autowired
    RaceScheduleService raceScheduleService;

    @GetMapping("/raceinfo")
    @ApiOperation(value = "赛事信息")
    public Result getAllRaceSchedule(@RequestParam(defaultValue = "1") Integer pageNum){
        return Result.ok().data("page",raceScheduleService.getAllRaceSchedule(pageNum));
    }

    @GetMapping("/select")
    @ApiOperation(value = "查询赛事")
    public Result select(@RequestParam String hname){
        IPage<RaceSchedule> iPage = new Page<>();
        try {
            iPage = raceScheduleService.selectRaceSchedule(hname);
        } catch (Exception e) {
            return Result.error().message(e.getMessage());
        }
        return Result.ok().data("page", iPage);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新建赛事")
    public Result add(@RequestBody RaceSchedule raceSchedule, @RequestParam(defaultValue = "1") Integer pageNum) {
        try {
            raceScheduleService.addRaceSchedule(raceSchedule);
        } catch (Exception e) {
            return Result.error().message(e.getMessage());
        }
        return getAllRaceSchedule(pageNum).message("添加成功");
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改赛事信息")
    public Result update(@RequestBody RaceSchedule raceSchedule, @RequestParam(defaultValue = "1") Integer pageNum) {
        try {
            raceScheduleService.updateRaceSchedule(raceSchedule);
        } catch (Exception e) {
            return Result.error().message(e.getMessage());
        }
        return getAllRaceSchedule(pageNum).message("修改成功");
    }

    @GetMapping("/delete")
    @ApiOperation("删除赛事")
    public Result delete(@RequestParam Integer schedulekey, @RequestParam(defaultValue = "1") Integer pageNum){
        try {
            raceScheduleService.deleteRaceSchedule(schedulekey);
        } catch (Exception e) {
            return Result.error().message(e.getMessage());
        }
        return getAllRaceSchedule(pageNum).message("删除成功");
    }

    @PostMapping("/addgoals")
    @ApiOperation(value = "添加球员进球")
    public Result addgoals(@RequestParam String playerName,@RequestParam(defaultValue = "1") Integer pageNum){
        try {
            raceScheduleService.addgoals(playerName);
        } catch (Exception e) {
            return Result.error().message(e.getMessage());
        }
        return getAllRaceSchedule(pageNum).message("设置成功");
    }

    @PostMapping("/integration")
    @ApiOperation(value = "更新球队积分")
    public Result integration(@RequestBody RaceSchedule raceSchedule,@RequestParam(defaultValue = "1") Integer pageNum){
        try {
            raceScheduleService.integration(raceSchedule);
        } catch (Exception e) {
            return Result.error().message(e.getMessage());
        }
        return getAllRaceSchedule(pageNum).message("设置成功");
    }
}
