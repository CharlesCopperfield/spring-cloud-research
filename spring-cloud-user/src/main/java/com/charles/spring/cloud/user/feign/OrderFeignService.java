package com.charles.spring.cloud.user.feign;

import com.charles.spring.cloud.framework.constants.ApplicationNameAndPathConstants;
import com.charles.spring.cloud.framework.module.order.service.OrderService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 3:47 PM
 */
@FeignClient(name = ApplicationNameAndPathConstants.ORDER_APP_NAME,
        path = ApplicationNameAndPathConstants.ORDER_APP_BASEPATH,
        contextId = "OrderFeignService")
public interface OrderFeignService extends OrderService {

}
