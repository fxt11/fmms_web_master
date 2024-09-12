package com.example.fmms.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fmms.entify.RaceSchedule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@TableName("raceschedule")
public interface RaceScheduleMapper extends BaseMapper<RaceSchedule> {
}
