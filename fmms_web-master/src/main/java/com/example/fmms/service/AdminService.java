package com.example.fmms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fmms.entify.User;
import com.example.fmms.entify.request.LoginRequest;
import com.example.fmms.entify.Admin;
import com.example.fmms.mapper.AdminMapper;
import com.example.fmms.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.fmms.exception.ServicesException;
import com.example.fmms.utils.JwtUtils;
import com.example.fmms.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AdminService  {
    @Autowired
    AdminMapper adminmapper;



    //登录
    public String login(LoginRequest request) {
        QueryWrapper<Admin> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("mgname",request.getUsername());
        Admin t=adminmapper.selectOne(queryWrapper);
        if(t==null){
            throw new ServicesException("找不到该用户名");
        }
        if(!(t.getMgpasssword().equals(request.getPassword()))){
            throw new ServicesException("密码错误");
        }
        //成功则返回带有token的数据包
        //根据用户uid生成token
        String token = JwtUtils.generateToken(t.getMgname());
        return token;

    }


    //注册
    public void regin(Admin admin) {
        QueryWrapper<Admin>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("mgname",admin.getMgname());
        if(adminmapper.selectCount(queryWrapper)>0){
            throw new ServicesException("该账号名已存在");
        }
        adminmapper.insert(admin);
    }


    //修改账号信息
    public void update(Admin admin) {
        adminmapper.updateById(admin);
    }

//    @Override
//    public User enquiry(String uname) {
//       return userMapper.enquiry_byuname(uname);
//    }


    public void delete(String mgname) {
        QueryWrapper<Admin>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("mgname",mgname);
        adminmapper.delete(queryWrapper);
    }
    public IPage<Admin> selectName(String name) {
        QueryWrapper<Admin> QueryWrapper = new QueryWrapper();
        QueryWrapper.like("mgname", name);
        Page<Admin> page = new Page<>(1, 10);
        IPage<Admin> iPage = adminmapper.selectPage(page, QueryWrapper);
        return iPage;
    }

    public IPage<Admin> page(int pageNum) {
        Page<Admin> page = new Page<>(pageNum, 10);
        IPage<Admin> iPage = adminmapper.selectPage(page, null);
        return iPage;


    }
}
