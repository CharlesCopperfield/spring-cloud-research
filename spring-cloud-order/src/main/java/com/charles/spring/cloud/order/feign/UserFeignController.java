package com.charles.spring.cloud.order.feign;

import com.charles.spring.cloud.framework.constants.ApplicationNameAndPathConstants;
import com.charles.spring.cloud.framework.module.user.bo.UserBO;
import com.charles.spring.cloud.framework.utils.ReturnMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * contextId = "UserFeignController"很重要,
 * 因为spring给该接口生成beanName=contextId.FeignClientSpecification
 * 的bean并且注册到beanFactory中,如果不指定contextId,则默认生成名字
 * 为name.FeignClientSpecification,这样会导致name不能相同,即项目中
 * 不能有两个相同name的类存在,否则启动报错.
 *
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 5:16 PM
 */
@FeignClient(name = ApplicationNameAndPathConstants.USER_APP_NAME,
        path = ApplicationNameAndPathConstants.USER_APP_BASEPATH + "/user",
        contextId = "UserFeignController")
@RequestMapping("/feign")
public interface UserFeignController {

    @GetMapping("/queryAll")
    public ReturnMessage<List<UserBO>> queryAll();
}
