package com.example.fmms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fmms.entify.Admin;
import com.example.fmms.entify.request.LoginRequest;
import com.example.fmms.entify.User;
import com.example.fmms.exception.ServicesException;
import com.example.fmms.mapper.AdminMapper;
import com.example.fmms.mapper.UserMapper;
import com.example.fmms.utils.JwtUtils;
import com.example.fmms.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService  {
    @Autowired
    UserMapper userMapper;
    AdminMapper adminMapper;
    //登录
//    public String login(LoginRequest request) {
//        QueryWrapper<Admin> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("mgname",request.getUsername());
//        Admin t=adminMapper.selectOne(queryWrapper);
//        if(t==null){
//            throw new ServicesException("找不到该用户名");
//        }
//        if(!(t.getMgpasssword().equals(request.getPassword()))){
//            throw new ServicesException("密码错误");
//        }
//        //成功则返回带有token的数据包
//        //根据用户uid生成token
//        String token = JwtUtils.generateToken(t.getMgname());
//        return token;
//    }
    //注册
    public void regin(User user) {
        QueryWrapper<User>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("uname",user.getUname());
        if(userMapper.selectCount(queryWrapper)>0){
            throw new ServicesException("该账号名已存在");
        }
        userMapper.insert(user);
    }
    //修改账号信息
    public void update(User user) {
        userMapper.updateById(user);
    }

//    @Override
//    public User enquiry(String uname) {
//       return userMapper.enquiry_byuname(uname);
//    }
    public void delete(String uname) {
        QueryWrapper<User>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("uname",uname);
        userMapper.delete(queryWrapper);
    }
    public Result info(String token) {
        String uid = JwtUtils.getClaimsByToken(token).getSubject();
        String name = userMapper.selectById(uid).getUname();
        String account = userMapper.selectById(uid).getUaccount();
        String password = userMapper.selectById(uid).getUpassword();
        return Result.ok().data("uid", uid).data("name", name).data("account", account).data("password", password);
    }
    public IPage<User> selectName(String name) {
        QueryWrapper<User> QueryWrapper = new QueryWrapper();
        QueryWrapper.like("uname", name);
        Page<User> page = new Page<>(1, 10);
        IPage<User> iPage = userMapper.selectPage(page, QueryWrapper);
        return iPage;
    }
    public IPage<User> page(int pageNum) {
        Page<User> page = new Page<>(pageNum, 10);
        IPage<User> iPage = userMapper.selectPage(page, null);
        return iPage;
    }
}
