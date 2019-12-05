package com.charles.spring.cloud.goods.controller;

import com.charles.spring.cloud.framework.constants.ApplicationNameAndPathConstants;
import com.charles.spring.cloud.framework.module.goods.bo.GoodsBO;
import com.charles.spring.cloud.framework.module.goods.bo.OrderGoodsRelBO;
import com.charles.spring.cloud.framework.module.goods.service.OrderGoodsRelService;
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
 * @time: 11/10/2019 9:32 AM
 */
@Api("订单商品关系服务")
@RestController
@RequestMapping(ApplicationNameAndPathConstants.CONTROLLER_PATH_FLAG + "/orderGoodsRel")
public class OrderGoodsRelController {

    @Autowired
    private OrderGoodsRelService orderGoodsRelService;

    @ApiOperation("查询所有")
    @GetMapping("/queryAll")
    public ReturnMessage<List<OrderGoodsRelBO>> queryAll() {
        List<OrderGoodsRelBO> orderGoodsRelBOS = orderGoodsRelService.queryAll();
        return ReturnMessage.success(orderGoodsRelBOS);
    }


}
