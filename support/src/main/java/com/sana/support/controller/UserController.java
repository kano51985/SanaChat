package com.sana.support.controller;

import cn.hutool.core.util.ObjUtil;
import com.sana.common.domain.dto.UserStateDTO;
import com.sana.common.domain.entity.SanaContacts;
import com.sana.common.response.R;
import com.sana.support.service.ISanaUserService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("user")
public class UserController {
    private final ISanaUserService sanaUserService;

    public UserController(ISanaUserService sanaUserService) {
        this.sanaUserService = sanaUserService;
    }

    @PostMapping("state")
    private R userState(@RequestBody UserStateDTO userStateDTO) {
        log.info("setting user state to redis............USER_ID===>{}" , userStateDTO.getId());
        if (userStateDTO.getId()!= null) {
            // 保存用户状态
            boolean flag = sanaUserService.setUserState(userStateDTO);
            if (flag) {
                return R.success("设置状态成功！",userStateDTO.getId());
            }
        }
        return R.error();
    }

    @GetMapping("contacts/{userId}")
    private R contacts(@PathVariable("userId") String id) {
        System.out.println("id = " + id);
        log.info("getting user contacts..............");
        SanaContacts userContacts = sanaUserService.getUserContacts(id);
        System.out.println("userContacts = " + userContacts);
        if (ObjUtil.isNotNull(userContacts)) {
            return R.success("获取成功！" , userContacts);
        }
        return R.error();
    }
}
