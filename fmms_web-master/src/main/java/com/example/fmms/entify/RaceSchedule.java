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
@TableName("raceschedule")
public class RaceSchedule {
    @TableId(value = "schedulekey", type = IdType.INPUT) // 机组编号（主键）
    @TableField(value = "schedulekey")
    private int schedulekey;
    @TableField(value = "rounds")
    private int rounds;
    @TableField(value = "scheduleTime")
    private String scheduleTime;
    @TableField(value = "Hteamkey")
    private int Hteamkey;
    @TableField(value = "Vteamkey")
    private int Vteamkey;
    @TableField(value = "Vname")
    private String Vname;
    @TableField(value = "Hname")
    private String Hname;
    @TableField(value = "currentSorce")
    private String currentSorce;
}
