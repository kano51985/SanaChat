package com.sana.support.controller;

import cn.hutool.core.util.ObjUtil;
import com.sana.common.domain.dto.LoginDTO;
import com.sana.common.domain.entity.SanaUser;
import com.sana.common.response.R;
import com.sana.support.service.ISanaUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("auth")
public class LoginController {
    private final ISanaUserService sanaUserService;

    public LoginController(ISanaUserService sanaUserService) {
        this.sanaUserService = sanaUserService;
    }

    @PostMapping("login")
    private R sanaLogin(@RequestBody LoginDTO loginDTO) {
        SanaUser user = sanaUserService.auth(loginDTO);
        if (ObjUtil.isNotNull(user)) {
            return R.success("登录成功！",user.getUserId());
        }
        return R.error();
    }

}
