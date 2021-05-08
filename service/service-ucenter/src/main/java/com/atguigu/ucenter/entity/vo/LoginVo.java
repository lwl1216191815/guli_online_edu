package com.atguigu.ucenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录vo
 */
@Data
@ApiModel(value = "登录对象",description = "登录对象")
public class LoginVo {

    @ApiModelProperty(value="手机号")
    private String phone;
    @ApiModelProperty(value="密码")
    private String password;
}
