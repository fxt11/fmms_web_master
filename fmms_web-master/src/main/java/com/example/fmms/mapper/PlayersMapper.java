package com.example.fmms.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.example.fmms.entify.Players;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@TableName("players")
public interface PlayersMapper extends BaseMapper<Players> {
}
