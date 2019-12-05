package com.charles.spring.cloud.framework.module.user.service;

import com.charles.spring.cloud.framework.constants.ApplicationNameAndPathConstants;
import com.charles.spring.cloud.framework.module.user.bo.UserBO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 9:31 AM
 */
@RestController
@RequestMapping(ApplicationNameAndPathConstants.USER_APP_BASEPATH +
        "/user" + ApplicationNameAndPathConstants.INNER_SERVICE_PATH_FLAG)
public interface UserService {

    @GetMapping("/queryAll")
    List<UserBO> queryAll();

    @GetMapping("/queryAllUserBO")
    List<UserBO> queryAllUserBO();
}
