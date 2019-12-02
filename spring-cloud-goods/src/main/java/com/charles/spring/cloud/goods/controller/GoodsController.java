package com.charles.spring.cloud.goods.controller;

import com.charles.spring.cloud.framework.module.goods.bo.GoodsBO;
import com.charles.spring.cloud.framework.module.goods.service.GoodsService;
import com.charles.spring.cloud.framework.utils.ReturnMessage;
import com.charles.spring.cloud.framework.module.user.bo.UserBO;
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
@Api("商品服务")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation("查询所有商品")
    @GetMapping("/queryAll")
    public ReturnMessage<List<GoodsBO>> queryAll() {
        List<GoodsBO> goodsBOS = goodsService.queryAll();
        return ReturnMessage.success(goodsBOS);
    }


}
