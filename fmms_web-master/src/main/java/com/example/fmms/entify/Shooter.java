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
@TableName("shooter")
public class Shooter {
    @TableId(value = "playerkey", type = IdType.INPUT) // 机组编号（主键）
//    @TableField(value = "playerkey")
    private int playerkey;
    @TableField(value = "playerName")
    private String playerName;
    @TableField(value = "teamName")
    private String teamName;
    @TableField(value = "goals")
    private int goals;
}
