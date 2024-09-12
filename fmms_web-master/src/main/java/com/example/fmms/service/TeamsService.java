package com.example.fmms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fmms.entify.TeamPoints;
import com.example.fmms.entify.Teams;
import com.example.fmms.entify.Teams;
import com.example.fmms.entify.request.LoginRequest;
import com.example.fmms.exception.ServicesException;
import com.example.fmms.mapper.TeamPointsMapper;
import com.example.fmms.mapper.TeamsMapper;

import com.example.fmms.utils.JwtUtils;
import com.example.fmms.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TeamsService {
    @Autowired
    TeamsMapper teamsMapper;
    @Autowired
    TeamPointsMapper teamPointsMapper;

    //新增
    public void add(Teams teams) {
        QueryWrapper<Teams> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teamkey", teams.getTeamkey());
        if (teamsMapper.selectCount(queryWrapper) > 0) {
            throw new ServicesException("该球队编号已存在");
        }
        teamsMapper.insert(teams);
        TeamPoints aa = new TeamPoints(teams.getTeamkey(), teams.getTeamName(), 0, 0, 0, 0,0,  0, 0);
        teamPointsMapper.insert(aa);
    }

    //修改账号信息
    public void update(Teams teams) {
        teamsMapper.updateById(teams);
    }

    public void delete(String teamkey) {
        QueryWrapper<Teams> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teamkey", teamkey);
        teamsMapper.delete(queryWrapper);
        QueryWrapper<TeamPoints>queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("teamkey",teamkey);
        teamPointsMapper.delete(queryWrapper1);
    }

    public IPage<Teams> selectNum(String name) {
        QueryWrapper<Teams> QueryWrapper = new QueryWrapper();
        QueryWrapper.like("teamName", name)
                .or()
                .like("foreignName", name);
        Page<Teams> page = new Page<>(1, 10);
        IPage<Teams> iPage = teamsMapper.selectPage(page, QueryWrapper);
        return iPage;
    }

    public IPage<Teams> page(int pageNum) {
        Page<Teams> page = new Page<>(pageNum, 10);
        IPage<Teams> iPage = teamsMapper.selectPage(page, null);
        return iPage;
    }

}
