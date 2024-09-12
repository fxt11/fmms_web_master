package com.example.fmms.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fmms.entify.Teams;
import org.apache.ibatis.annotations.Mapper;
//import sun.print.BackgroundLookupListener;

@Mapper
@TableName("teams")
public interface TeamsMapper extends BaseMapper<Teams> {
}

