package com.charles.spring.cloud.user.controller;

import com.charles.spring.cloud.framework.constants.ApplicationNameAndPathConstants;
import com.charles.spring.cloud.framework.module.user.bo.UserBO;
import com.charles.spring.cloud.framework.module.user.service.UserService;
import com.charles.spring.cloud.framework.utils.ReturnMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 9:32 AM
 */
@Api("用户服务")
@RestController
@RequestMapping(ApplicationNameAndPathConstants.CONTROLLER_PATH_FLAG + "/user")
@Slf4j
public class UserController {


    @Autowired
    private UserService userService;

    @ApiOperation("查询所有用户")
    @GetMapping("/queryAll")
    public ReturnMessage<List<UserBO>> queryAll() {
        List<UserBO> userBOS = userService.queryAll();
        return ReturnMessage.success(userBOS);
    }


}
