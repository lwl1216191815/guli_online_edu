package com.atguigu.ucenter.service.impl;

import com.atguigu.common.utils.JwtUtils;
import com.atguigu.common.utils.MD5;
import com.atguigu.servicebase.exception.GuliException;
import com.atguigu.ucenter.entity.Member;
import com.atguigu.ucenter.entity.vo.LoginVo;
import com.atguigu.ucenter.entity.vo.RegisterVo;
import com.atguigu.ucenter.entity.vo.UserInfoVo;
import com.atguigu.ucenter.mapper.MemberMapper;
import com.atguigu.ucenter.service.MemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author liuwenlong
 * @since 2021-05-06
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public String login(LoginVo vo) {
        String password = vo.getPassword();
        String phone = vo.getPhone();
        if(StringUtils.isBlank(phone) || StringUtils.isBlank(password)){
            throw new GuliException(20001,"登录失败");
        }
        QueryWrapper<Member> mQuery = new QueryWrapper<>();
        mQuery.eq("mobile",phone);
        Member member = baseMapper.selectOne(mQuery);
        if(member == null){
            throw new GuliException(20001,"登录失败");
        }
        if(!member.getPassword().equals(MD5.encrypt(password))){
            throw new GuliException(20001,"登录失败");
        }
        if(member.getIsDisabled()){
            throw new GuliException(20001,"登录失败,此用户已被禁用");
        }
        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo vo) {
        String phone = vo.getMobile();
        String nickname = vo.getNickname();
        String password = vo.getPassword();
        String code = vo.getCode();
        if(StringUtils.isBlank(phone) || StringUtils.isBlank(nickname)
        || StringUtils.isBlank(password) || StringUtils.isBlank(code)){
            throw new GuliException(20001,"注册失败");
        }

        String redisCode = redisTemplate.opsForValue().get(phone);
        if(!code.equals(redisCode)){
            throw new GuliException(20001,"注册失败");
        }

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",phone);
        Integer count = baseMapper.selectCount(queryWrapper);
        if(count > 0){
            throw new GuliException(20001,"注册失败");
        }
        Member entity = new Member();
        vo.setPassword(MD5.encrypt(vo.getPassword()));
        BeanUtils.copyProperties(vo,entity);
        baseMapper.insert(entity);
    }

    @Override
    public UserInfoVo getUserInfoById(String memberId) {
        Member member = baseMapper.selectById(memberId);
        UserInfoVo vo = new UserInfoVo();
        BeanUtils.copyProperties(member,vo);
        return vo;
    }
}
