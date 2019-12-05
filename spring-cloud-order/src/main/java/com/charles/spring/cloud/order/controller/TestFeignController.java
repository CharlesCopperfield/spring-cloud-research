package com.charles.spring.cloud.order.controller;

import com.charles.spring.cloud.framework.constants.ApplicationNameAndPathConstants;
import com.charles.spring.cloud.framework.module.user.bo.UserBO;
import com.charles.spring.cloud.framework.utils.ReturnMessage;
import com.charles.spring.cloud.order.feign.UserFeignController;
import com.charles.spring.cloud.order.feign.UserFeignService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 5:27 PM
 */
@RestController
@RequestMapping(ApplicationNameAndPathConstants.CONTROLLER_PATH_FLAG + "/testFeign")
public class TestFeignController {

    @Autowired
    private UserFeignService userFeignService;
    @Autowired
    private UserFeignController userFeignController;

    @ApiOperation("userFeignService")
    @GetMapping("/userFeignService")
    public List<UserBO> userFeignService() {
        return userFeignService.queryAll();
    }

    @ApiOperation("userFeignController")
    @GetMapping("/userFeignController")
    public ReturnMessage userFeignController() {
        return userFeignController.queryAll();
    }
}
