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
@TableName("users")

public class User {
    @TableField(value = "uname")
    private String uname;
    @TableId(value = "uaccount", type = IdType.INPUT) // 机组编号（主键）
    private String uaccount;
    @TableField(value = "upassword")
    private String upassword;
}
