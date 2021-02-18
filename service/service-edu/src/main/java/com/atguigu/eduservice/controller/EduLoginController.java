package com.atguigu.eduservice.controller;

import com.atguigu.common.utils.R;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {
    /**
     * 登录方法，返回token
     * @return
     */
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    /**
     * 查询用户信息的方法
     * @return
     */
    @GetMapping("info")
    public R info(){
        Map<String,Object> data = new HashMap<>();
        data.put("roles","[admin]");
        data.put("name","admin");
        data.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return R.ok().data(data);
    }
}
