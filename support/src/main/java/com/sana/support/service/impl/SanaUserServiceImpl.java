package com.sana.support.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.sana.common.constants.AccountStateConstants;
import com.sana.common.constants.CacheConstants;
import com.sana.common.domain.dto.LoginDTO;
import com.sana.common.domain.dto.UserStateDTO;
import com.sana.common.domain.entity.SanaContactors;
import com.sana.common.domain.entity.SanaUser;
import com.sana.common.domain.vo.BasicUserInfoVO;
import com.sana.support.repository.ContactorsRepository;
import com.sana.support.repository.SanaUserRepository;
import com.sana.support.service.ISanaUserService;
import com.sana.support.utils.RedisCacheUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class SanaUserServiceImpl implements ISanaUserService {
    @Resource
    private SanaUserRepository sanaUserRepository;
    @Resource
    private ContactorsRepository contactsRepository;
    private final RedisCacheUtil redisCacheUtil;

    public SanaUserServiceImpl(RedisCacheUtil redisCacheUtil) {
        this.redisCacheUtil = redisCacheUtil;
    }

    @Override
    public String auth(LoginDTO loginDTO) {
        SanaUser user = sanaUserRepository.findSanaUserByUserIdAndAndPassword(loginDTO.getId(),loginDTO.getPassword());
        log.info("getting user from mongo =================> {}" , user);
        //TODO: 此处应返回枚举类，如果状态不正常需要给用户对应的通知
        if (user != null && AccountStateConstants.ACCOUNT_AVALIABLE.equals(user.getStatus())) {
            UUID uuid = UUID.randomUUID();
            String real = uuid.toString().replaceAll("-", "");
            return real; // 用户存在且状态可用
        }
        return null;
    }

    @Override
    public boolean setUserState(UserStateDTO userStateDTO) {
        // 1. 获取用户信息
        SanaUser user = sanaUserRepository.findByUserId(userStateDTO.getId());
        log.info("setUserState step 1:getting user info ===================> user:{}",user);
        if (ObjectUtil.isNotNull(user)) {
            // 2. 存储信息到redis
            try {
                redisCacheUtil.setCacheObject(CacheConstants.USER_STATE_KEY + userStateDTO.getId() , user);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        return false;
    }

    @Override
    public SanaContactors getUserContacts(String id) {
        log.info("getting user contacts from mongodb......................");
        SanaContactors contacts = contactsRepository.findByUserId(id);
        return contacts;
    }

    @Override
    public BasicUserInfoVO getUserBasicInfo(String id) {
        SanaUser user = sanaUserRepository.findByUserId(id);
        BasicUserInfoVO userInfoVO = new BasicUserInfoVO();
        userInfoVO.setId(user.getUserId());
        userInfoVO.setNickname(user.getNickname());
        userInfoVO.setAvatar(user.getAvatar());

        return userInfoVO;
    }
}
