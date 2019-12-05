package com.charles.spring.cloud.framework.module.order.service;

import com.charles.spring.cloud.framework.constants.ApplicationNameAndPathConstants;
import com.charles.spring.cloud.framework.module.order.bo.OrderBO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 9:31 AM
 */
@RestController
@RequestMapping("/order" + ApplicationNameAndPathConstants.INNER_SERVICE_PATH_FLAG)
public interface OrderService {

    @GetMapping("/queryAll")
    List<OrderBO> queryAll();

    @GetMapping("/queryAllOrderBOWithGoodsBOs")
    List<OrderBO> queryAllOrderBOWithGoodsBOs();
}
