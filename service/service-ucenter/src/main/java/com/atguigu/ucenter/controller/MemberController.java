package com.atguigu.ucenter.controller;


import com.atguigu.common.utils.JwtUtils;
import com.atguigu.common.utils.R;
import com.atguigu.ucenter.entity.vo.LoginVo;
import com.atguigu.ucenter.entity.vo.RegisterVo;
import com.atguigu.ucenter.entity.vo.UserInfoVo;
import com.atguigu.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author liuwenlong
 * @since 2021-05-06
 */
@RestController
@RequestMapping("/ucenter/member")
@Api(tags = "用户登录控制")
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**
     *
     * 用户登录
     * @param vo
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public R login(@RequestBody LoginVo vo){
        String token = memberService.login(vo);
        return R.ok().data("token",token);
    }


    @PostMapping("/register")
    @ApiOperation("用户注册")
    public R register(RegisterVo vo){
        memberService.register(vo);
        return R.ok();
    }

    @GetMapping("/getUserInfoByToken")
    @ApiOperation("根据token获取用户登录信息")
    public R getUserInfoByToken(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UserInfoVo vo = memberService.getUserInfoById(memberId);
        return R.ok().data("item",vo);
    }
}

