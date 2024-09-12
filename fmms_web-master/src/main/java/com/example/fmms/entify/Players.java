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
@TableName("players")
public class Players {
    @TableId(value = "playerkey", type = IdType.INPUT) // 机组编号（主键）
    @TableField(value = "playerkey")
    private int playerkey;
    @TableField(value = "teamkey")
    private int teamkey;
    @TableField(value = "playerName")
    private String playerName;
    @TableField(value = "teamFunction")
    private String teamFunction;
    @TableField(value = "birthday")
    private String birthday;
    @TableField(value = "height")
    private String height;
    @TableField(value = "weight")
    private String weight;
    @TableField(value = "nationality")
    private String nationality;
    @TableField(value = "team")
    private String team;
}
