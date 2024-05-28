package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.annotation.CheckToken;
import com.tencent.wxcloudrun.annotation.LoginToken;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cr.
 * @date 2024/5/28
 */
@Api(tags = "Login API", description = "登录测试")
@RestController
public class LoginController {


    /**
     * 登录
     *
     * @param userId
     * @param username
     * @return
     */
    @ApiOperation("登录测试")
    @PostMapping("/login")
    @LoginToken
    public ApiResponse login(@RequestParam Long userId,
                             @RequestParam String username) {
        if (userId == null || username == null) {
            return ApiResponse.error("请检查登录参数");
        }
        String token = JwtUtils.geneJsonWebToken(userId, username);
        // 前端保存到请求头token
        return ApiResponse.ok(token);
    }

    @CheckToken
    @GetMapping("/getMessage")
    @ApiOperation("拦截测试")
    public String getMessage() {
        return "你已通过验证";
    }
}
