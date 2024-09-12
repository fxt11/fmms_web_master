package com.example.fmms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fmms.entify.RaceSchedule;
import com.example.fmms.entify.Shooter;
import com.example.fmms.entify.Shooter;
import com.example.fmms.exception.ServicesException;
import com.example.fmms.mapper.ShooterMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShooterService {
    @Autowired
    ShooterMapper shooterMapper;

    //新增
    public void add(Shooter shooter) {
        QueryWrapper<Shooter> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("playerkey",shooter.getPlayerkey());
        if(shooterMapper.selectCount(queryWrapper)>0){
            throw new ServicesException("该球员编号已存在");
        }
        shooterMapper.insert(shooter);
    }

    //修改账号信息
    public void update(Shooter shooter) {
        shooterMapper.updateById(shooter);
    }

    public void delete(String playerkey) {
        QueryWrapper<Shooter>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("playerkey",playerkey);
        shooterMapper.delete(queryWrapper);
    }

    public IPage<Shooter> selectNum(String name) {
        QueryWrapper<Shooter> QueryWrapper = new QueryWrapper();
        QueryWrapper.like("playerName", name);
        Page<Shooter> page = new Page<>(1, 10);
        IPage<Shooter> iPage = shooterMapper.selectPage(page, QueryWrapper);
        return iPage;
    }

    public IPage<Shooter> page(int pageNum) {
        Page<Shooter> page = new Page<>(pageNum, 10);
        QueryWrapper<Shooter> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("goals");
        IPage<Shooter> iPage = shooterMapper.selectPage(page, queryWrapper);
        return iPage;
    }
}
