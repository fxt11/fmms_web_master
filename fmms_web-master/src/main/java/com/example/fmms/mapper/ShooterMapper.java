package com.example.fmms.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fmms.entify.Shooter;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@TableName("shooter")
public interface ShooterMapper extends BaseMapper<Shooter> {
}
