package com.example.fmms.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fmms.entify.TeamPoints;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@TableName("teampoints")
public interface TeamPointsMapper extends BaseMapper<TeamPoints> {
}
