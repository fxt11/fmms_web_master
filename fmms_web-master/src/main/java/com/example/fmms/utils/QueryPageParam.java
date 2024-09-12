package com.example.fmms.utils;

import lombok.Data;

import java.util.HashMap;

@Data
//用于包装前端传来的页信息
public class QueryPageParam {
    //默认
    private static int PAGE_SIZE=20;
    private static int PAGE_NUM=1;
    //从前端接收的
    private int pageSize=20;
    private int pageNum=1;
    private HashMap param;
}
