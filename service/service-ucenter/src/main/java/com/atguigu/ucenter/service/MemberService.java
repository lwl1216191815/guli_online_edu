package com.atguigu.ucenter.service;

import com.atguigu.ucenter.entity.Member;
import com.atguigu.ucenter.entity.vo.LoginVo;
import com.atguigu.ucenter.entity.vo.RegisterVo;
import com.atguigu.ucenter.entity.vo.UserInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author liuwenlong
 * @since 2021-05-06
 */
public interface MemberService extends IService<Member> {
    /**
     * 登录，返回token
     * @param vo
     * @return
     */
    String login(LoginVo vo);

    /**
     * 用户注册
     * @param vo
     */
    void register(RegisterVo vo);

    /**
     * 获取用户信息
     * @param memberId
     * @return
     */
    UserInfoVo getUserInfoById(String memberId);
}
