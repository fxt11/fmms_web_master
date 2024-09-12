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
@TableName("teampoints")
public class TeamPoints {
    @TableId(value = "teamkey", type = IdType.INPUT) // 机组编号（主键）
    @TableField(value = "teamkey")
    private int teamkey;
    @TableField(value = "teamName")
    private String teamName;
    @TableField(value = "netinTergral")
    private int netinTergral;
    @TableField(value = "cap")
    private int cap;
    @TableField(value = "win")
    private int win;
    @TableField(value = "tequal")
    private int tequal;
    @TableField(value = "lose")
    private int lose;
    @TableField(value = "goal")
    private int goal;
    @TableField(value = "fumble")
    private int fumble;
}
