package com.example.fmms.entify;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("teams")
public class Teams {
    @TableId(value = "teamkey", type = IdType.INPUT) // 机组编号（主键）
    @TableField(value = "teamkey")
    private int teamkey;
    @TableField(value = "teamName")
    private String teamName;
    @TableField(value = "foreignName")
    private String foreignName;
    @TableField(value = "goals")
    private int goals;
    @TableField(value = "fumbles")
    private int fumbles;
    @TableField(value = "foundedDate")
    private String foundedDate;
    @TableField(value = "headCoach")
    private String headCoach;
    @TableField(value = "teamHome")
    private String teamHome;
}
