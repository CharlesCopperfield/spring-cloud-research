package com.charles.spring.cloud.user.controller;

import brave.Tracer;
import com.charles.spring.cloud.framework.constants.ApplicationNameAndPathConstants;
import com.charles.spring.cloud.framework.module.goods.service.GoodsService;
import com.charles.spring.cloud.framework.module.goods.service.OrderGoodsRelService;
import com.charles.spring.cloud.framework.module.user.bo.UserBO;
import com.charles.spring.cloud.framework.module.user.service.UserService;
import com.charles.spring.cloud.framework.utils.ReturnMessage;
import com.charles.spring.cloud.user.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 8:47 PM
 */
@Api("测试Feign相关功能")
@RestController
@RequestMapping(ApplicationNameAndPathConstants.CONTROLLER_PATH_FLAG + "/feignTest")
public class FeignTestController {

    private static final Logger logger = LoggerFactory.getLogger(FeignTestController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private Tracer tracer;

    @ApiOperation("threeLayerCallsForFeign")
    @PostMapping("/threeLayerCallsForFeign")
    public ReturnMessage<List<UserBO>> threeLayerCallsForFeign(@RequestBody Map<String, Object> params) {
        List<UserBO> userBOS = userService.queryAllUserBO();
        return ReturnMessage.success(userBOS);
    }

}
