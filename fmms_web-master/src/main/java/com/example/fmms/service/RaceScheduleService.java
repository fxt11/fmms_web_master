package com.example.fmms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fmms.entify.*;
import com.example.fmms.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaceScheduleService {
    @Autowired
    RaceScheduleMapper raceScheduleMapper;
    @Autowired
    PlayersMapper playersMapper;
    @Autowired
    TeamsMapper teamsMapper;
    @Autowired
    TeamPointsMapper teamPointsMapper;
    @Autowired
    ShooterMapper shooterMapper;

    //展示获取所有赛程信息
    public IPage getAllRaceSchedule(Integer pageNum) {
        Page<RaceSchedule> page = new Page<>(pageNum, 10);
        IPage<RaceSchedule> iPage = raceScheduleMapper.selectPage(page,null);
        return iPage;
    }

    // 添加赛事信息
    public void addRaceSchedule(RaceSchedule raceSchedule) throws Exception{
        QueryWrapper<RaceSchedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("schedulekey",raceSchedule.getSchedulekey());
        if(raceScheduleMapper.selectCount(queryWrapper) > 0){
            throw new Exception("该赛程已经存在，请检查赛程信息");
        }
        raceScheduleMapper.insert(raceSchedule);
    }
    // 修改赛事信息
    public void updateRaceSchedule(RaceSchedule raceSchedule) throws Exception{
        UpdateWrapper<RaceSchedule> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("schedulekey",raceSchedule.getSchedulekey());
        if(raceScheduleMapper.selectCount(updateWrapper) == 0){
            throw new Exception("要修改的赛程不存在，请检查赛程信息");
        }
        raceScheduleMapper.update(raceSchedule,updateWrapper);
    }
    // 删除赛事
    public void deleteRaceSchedule(Integer schedulekey) throws Exception{
        QueryWrapper<RaceSchedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("schedulekey",schedulekey);
        if(raceScheduleMapper.selectCount(queryWrapper) == 0){
            throw new Exception("要删除的赛程不存在，请检查赛程信息");
        }
        raceScheduleMapper.delete(queryWrapper);
    }

    // 查询赛事信息，根据主队或者客队名字进行检索
    public  IPage selectRaceSchedule(String teamName) throws Exception{
        Page<RaceSchedule> page = new Page<>(1, 10);
        QueryWrapper<RaceSchedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("hname",teamName)
                .or()
                .like("vname",teamName);
        IPage<RaceSchedule> iPage = raceScheduleMapper.selectPage(page,null);
        return iPage;
    }

    // 为进球球员和球队更新数据
    public void addgoals(String playerName) throws Exception{
        // 为射手榜中球员添加进球数数据
        QueryWrapper<Shooter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("playerName",playerName);
        // 如果射手榜存在该球员,则进球数加一
        if(shooterMapper.selectCount(queryWrapper) > 0){
            UpdateWrapper<Shooter> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("playerName",playerName);
            Shooter shooter = shooterMapper.selectOne(queryWrapper);
            shooter.setGoals(shooter.getGoals() + 1);
            shooterMapper.update(shooter,updateWrapper);
        }else{
            // 如果射手榜中不存在该球员，则添加进入新的数据
            QueryWrapper<Players> queryWrapper1 = new QueryWrapper<>();
            Players player = playersMapper.selectOne(queryWrapper1.eq("playerName",playerName));
            if(player == null){
                throw new Exception("不存在该球员，请检查球员信息");
            }
            Shooter shooter = new Shooter(player.getPlayerkey(),player.getPlayerName(),player.getTeam(),1);
            shooterMapper.insert(shooter);
        }
//        // 为球员所在的球队信息添加进球数
//        QueryWrapper<Players> queryWrapper2 = new QueryWrapper<>();
//        queryWrapper2.eq("playerName",playerName);
//        Players players = playersMapper.selectOne(queryWrapper2);
//        Integer teamkey = players.getTeamkey();
//        UpdateWrapper<Teams> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.eq("teamkey",teamkey);
//        Teams team = teamsMapper.selectOne(updateWrapper);
//        team.setGoals(team.getGoals() + 1);
//        teamsMapper.update(team,updateWrapper);
//        // 为球员所在的球队积分榜中添加进球数
//        UpdateWrapper<TeamPoints> updateWrapper1 = new UpdateWrapper<>();
//        updateWrapper1.eq("teamkey",teamkey);
//        TeamPoints teamPoints = teamPointsMapper.selectOne(updateWrapper1);
//        teamPoints.setGoal(teamPoints.getGoal() + 1);
//        teamPointsMapper.update(teamPoints,updateWrapper1);
    }

    // 比赛结束时，更新球队积分信息以及进失球信息
    public void integration(RaceSchedule raceSchedule)throws Exception{
        String score = raceSchedule.getCurrentSorce();
        String[] temp = score.split("-");
        Integer hgoals = Integer.parseInt(temp[0]);
        Integer vgoals = Integer.parseInt(temp[1]);
        UpdateWrapper<TeamPoints> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("teamkey",raceSchedule.getHteamkey());
        TeamPoints Hteam = teamPointsMapper.selectOne(updateWrapper);
        UpdateWrapper<TeamPoints> updateWrapper1 = new UpdateWrapper<>();
        updateWrapper1.eq("teamkey",raceSchedule.getVteamkey());
        TeamPoints Vteam = teamPointsMapper.selectOne(updateWrapper1);
        //更新积分榜的进球数与失球数
        Hteam.setGoal(Hteam.getGoal() + hgoals);
        Hteam.setFumble(Hteam.getFumble() + vgoals);
        Vteam.setGoal(Vteam.getGoal() + vgoals);
        Vteam.setFumble(Vteam.getFumble() + hgoals);
        teamPointsMapper.update(Hteam,updateWrapper);
        teamPointsMapper.update(Vteam,updateWrapper1);
        // 更新积分榜的积分
        if(hgoals > vgoals){
            Hteam.setWin(Hteam.getWin() + 1);
            Hteam.setNetinTergral(Hteam.getNetinTergral() + 3);
            Vteam.setLose(Vteam.getLose() + 1);
            teamPointsMapper.update(Hteam,updateWrapper);
            teamPointsMapper.update(Vteam,updateWrapper1);
        }else if(hgoals < vgoals){
            Vteam.setWin(Vteam.getWin() + 1);
            Vteam.setNetinTergral(Vteam.getNetinTergral() + 3);
            Hteam.setLose(Hteam.getLose() + 1);
            teamPointsMapper.update(Vteam,updateWrapper1);
            teamPointsMapper.update(Hteam,updateWrapper);
        }else {
            Hteam.setTequal(Hteam.getTequal() + 1);
            Vteam.setTequal(Vteam.getTequal() + 1);
            Hteam.setNetinTergral(Hteam.getNetinTergral() + 1);
            Vteam.setNetinTergral(Vteam.getNetinTergral() + 1);
            teamPointsMapper.update(Vteam,updateWrapper1);
            teamPointsMapper.update(Hteam,updateWrapper);
        }
        // 更新球队表的进失球
        UpdateWrapper<Teams> updateWrapper2 = new UpdateWrapper<>();
        updateWrapper2.eq("teamkey",raceSchedule.getHteamkey());
        Teams Hteams = teamsMapper.selectOne(updateWrapper2);
        UpdateWrapper<Teams> updateWrapper3 = new UpdateWrapper<>();
        updateWrapper3.eq("teamkey",raceSchedule.getVteamkey());
        Teams Vteams = teamsMapper.selectOne(updateWrapper3);
        Hteams.setGoals(Hteams.getGoals() + hgoals);
        Hteams.setFumbles(Hteams.getFumbles() + vgoals);
        Vteams.setGoals(Vteams.getGoals() + vgoals);
        Vteams.setFumbles(Vteams.getFumbles() + hgoals);
        teamsMapper.update(Hteams,updateWrapper2);
        teamsMapper.update(Vteams,updateWrapper3);
    }

}
