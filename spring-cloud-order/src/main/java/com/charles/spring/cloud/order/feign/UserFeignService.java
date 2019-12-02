package com.charles.spring.cloud.order.feign;

import com.charles.spring.cloud.framework.constants.ApplicationNameAndPathConstants;
import com.charles.spring.cloud.framework.module.user.service.UserService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 3:47 PM
 */
@FeignClient(name = ApplicationNameAndPathConstants.USER_APP_NAME,
        path = ApplicationNameAndPathConstants.USER_APP_BASEPATH,
        contextId = "UserFeignService")
public interface UserFeignService extends UserService {

}
