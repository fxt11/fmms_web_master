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
@TableName("administrators")
public class Admin {
    @TableId(value = "mgaccount", type = IdType.INPUT) // 机组编号（主键）
    private String mgaccount;
    @TableField(value = "mgpasssword")
    private String mgpasssword;
    @TableField(value = "mgname")
    private String mgname;
}
