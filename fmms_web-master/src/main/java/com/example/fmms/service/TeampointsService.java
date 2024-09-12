package com.example.fmms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fmms.entify.TeamPoints;
import com.example.fmms.exception.ServicesException;
import com.example.fmms.mapper.TeamPointsMapper;

import com.example.fmms.mapper.TeamsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TeampointsService {
    @Autowired
    TeamPointsMapper teamPointsMapper;
    @Autowired
    TeamsMapper teamsMapper;
    //新增
    public void add(TeamPoints teamPoints) {
        QueryWrapper<TeamPoints> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("teamkey",teamPoints.getTeamkey());
        if(teamPointsMapper.selectCount(queryWrapper)>0){
            throw new ServicesException("该球队编号已存在");
        }
        teamPointsMapper.insert(teamPoints);
    }

    //修改信息
    public void update(TeamPoints TeamPoints) {
        teamPointsMapper.updateById(TeamPoints);
    }

    public void delete(String teamkey) {
        QueryWrapper<TeamPoints>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("teamkey",teamkey);
        teamPointsMapper.delete(queryWrapper);

    }

    public IPage<TeamPoints> selectNum(String name) {
        QueryWrapper<TeamPoints> QueryWrapper = new QueryWrapper();
        QueryWrapper.like("teamName", name);
        Page<TeamPoints> page = new Page<>(1, 10);
        IPage<TeamPoints> iPage = teamPointsMapper.selectPage(page, QueryWrapper);
        return iPage;
    }

    public IPage<TeamPoints> page(int pageNum) {
        Page<TeamPoints> page = new Page<>(pageNum, 10);
        QueryWrapper<TeamPoints> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("netinTergral");
        IPage<TeamPoints> iPage = teamPointsMapper.selectPage(page, queryWrapper);
        return iPage;
    }
}
