package com.charles.spring.cloud.framework.module.goods.service;

import com.charles.spring.cloud.framework.constants.ApplicationNameAndPathConstants;
import com.charles.spring.cloud.framework.module.goods.bo.OrderGoodsRelBO;
import com.charles.spring.cloud.framework.module.user.bo.UserBO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 8:25 PM
 */
@RestController
@RequestMapping(ApplicationNameAndPathConstants.GOODS_APP_BASEPATH +
        "/orderGoodsRel" + ApplicationNameAndPathConstants.INNER_SERVICE_PATH_SUFFIX)
public interface OrderGoodsRelService {


    @GetMapping("/queryAll")
    List<OrderGoodsRelBO> queryAll();
}
