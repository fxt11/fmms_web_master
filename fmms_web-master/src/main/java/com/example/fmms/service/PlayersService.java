package com.example.fmms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fmms.entify.Players;
import com.example.fmms.entify.Teams;
import com.example.fmms.exception.ServicesException;
import com.example.fmms.mapper.PlayersMapper;

import com.example.fmms.mapper.TeamsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PlayersService {
    @Autowired
    PlayersMapper playersMapper;
    @Autowired
    TeamsMapper teamsMapper;
    //新增
    public void add(Players players) {
        QueryWrapper<Players> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("playerkey",players.getPlayerkey());
        if(playersMapper.selectCount(queryWrapper)>0){
            throw new ServicesException("该球员编号已存在");
        }
        QueryWrapper<Teams> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("teamkey",players.getTeamkey());
        if(teamsMapper.selectCount(queryWrapper1)<=0){
            throw new ServicesException("该球队编号不存在");
        }
        playersMapper.insert(players);
    }

    //修改账号信息
    public void update(Players players) {
        playersMapper.updateById(players);
    }

    public void delete(String playerkey) {
        QueryWrapper<Players>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("playerkey",playerkey);
        playersMapper.delete(queryWrapper);
    }

    public IPage<Players> selectNum(String name) {
        QueryWrapper<Players> QueryWrapper = new QueryWrapper();
        QueryWrapper.like("playerName", name);
        Page<Players> page = new Page<>(1, 10);
        IPage<Players> iPage = playersMapper.selectPage(page, QueryWrapper);
        return iPage;
    }

    public IPage<Players> selectTeam(String name) {
        QueryWrapper<Players> QueryWrapper = new QueryWrapper();
        QueryWrapper.like("team", name);
        Page<Players> page = new Page<>(1, 10);
        IPage<Players> iPage = playersMapper.selectPage(page, QueryWrapper);
        return iPage;
    }

    public IPage<Players> selectNation(String name) {
        QueryWrapper<Players> QueryWrapper = new QueryWrapper();
        QueryWrapper.like("nationality", name);
        Page<Players> page = new Page<>(1, 10);
        IPage<Players> iPage = playersMapper.selectPage(page, QueryWrapper);
        return iPage;
    }

    public IPage<Players> page(int pageNum) {
        Page<Players> page = new Page<>(pageNum, 10);
        IPage<Players> iPage = playersMapper.selectPage(page, null);
        return iPage;
    }
}
