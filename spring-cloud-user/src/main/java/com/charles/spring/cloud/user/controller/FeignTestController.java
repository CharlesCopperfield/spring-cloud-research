package com.charles.spring.cloud.user.controller;

import com.charles.spring.cloud.framework.module.goods.service.GoodsService;
import com.charles.spring.cloud.framework.module.goods.service.OrderGoodsRelService;
import com.charles.spring.cloud.framework.module.user.bo.UserBO;
import com.charles.spring.cloud.framework.module.user.service.UserService;
import com.charles.spring.cloud.framework.utils.ReturnMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 8:47 PM
 */
@Api("测试Feign相关功能")
@RestController
@RequestMapping("/feignTest")
public class FeignTestController {

    @Autowired
    private UserService userService;


    @ApiOperation("threeLayerCallsForFeign")
    @GetMapping("/threeLayerCallsForFeign")
    public ReturnMessage<List<UserBO>> threeLayerCallsForFeign() {
        List<UserBO> userBOS = userService.queryAllUserBO();
        return ReturnMessage.success(userBOS);
    }

}
