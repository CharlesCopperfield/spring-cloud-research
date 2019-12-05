package com.charles.spring.cloud.order.feign;

import com.charles.spring.cloud.framework.constants.ApplicationNameAndPathConstants;
import com.charles.spring.cloud.framework.module.goods.service.GoodsService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 3:47 PM
 */
@FeignClient(name = ApplicationNameAndPathConstants.GOODS_APP_NAME,
        path = ApplicationNameAndPathConstants.GOODS_APP_BASEPATH,
        contextId = "GoodsFeignService")
public interface GoodsFeignService extends GoodsService {

}
