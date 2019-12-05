package com.charles.spring.cloud.order.controller;

import com.charles.spring.cloud.framework.constants.ApplicationNameAndPathConstants;
import com.charles.spring.cloud.framework.module.order.bo.OrderBO;
import com.charles.spring.cloud.framework.utils.ReturnMessage;
import com.charles.spring.cloud.framework.module.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 9:32 AM
 */
@Api("订单服务")
@RestController
@RequestMapping(ApplicationNameAndPathConstants.CONTROLLER_PATH_FLAG +"/order")
public class OrderController {

    @Autowired
    private OrderService orderService;



    @ApiOperation("查询所有")
    @GetMapping("/querAll")
    public ReturnMessage querAll() {
        List<OrderBO> orderBOS = orderService.queryAll();
        return ReturnMessage.success(orderBOS);
    }



}
