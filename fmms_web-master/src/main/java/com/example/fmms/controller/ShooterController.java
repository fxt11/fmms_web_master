package com.example.fmms.controller;

import com.example.fmms.entify.Shooter;
import com.example.fmms.service.ShooterService;
import com.example.fmms.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shooter")
@Api(tags = "射手榜操作")
@CrossOrigin
public class ShooterController {
    @Autowired
    ShooterService shooterService;

    @SneakyThrows
    @PostMapping("/add")
    @ApiOperation(value = "射手榜信息新增")
    public Result regin(@RequestBody Shooter Shooter,@RequestParam(defaultValue = "1") Integer pageNum){
        try{
        shooterService.add(Shooter);
        }catch (Exception e){
            return Result.error().message(e.getMessage());
        }
        return list(pageNum).message("修改成功");
    }

    @PutMapping("/update")
    @ApiOperation(value = "射手榜信息修改")
    public Result update(@RequestBody Shooter playerName,@RequestParam(defaultValue = "1") Integer pageNum){
        try{
        shooterService.update(playerName);
        }catch (Exception e){
            return Result.error().message(e.getMessage());
        }
        return list(pageNum).message("修改成功");
    }


    @DeleteMapping("/delete")
    @ApiOperation(value = "射手榜信息删除")
    public Result delete(@RequestParam String playerkey){
        shooterService.delete(playerkey);
        return Result.ok();
    }

    @GetMapping("/selectNum")
    @ApiOperation(value = "通过人名查询）")

    public Result selectNum(@RequestParam String playerName) {
        return Result.ok().data("page",shooterService.selectNum(playerName));
    }
    @GetMapping("/list")
    @ApiOperation(value = "射手榜信息分页显示")

    public Result list(@RequestParam(defaultValue = "1") int pageNum) {
        return Result.ok().data("page", shooterService.page(pageNum));
    }
}
