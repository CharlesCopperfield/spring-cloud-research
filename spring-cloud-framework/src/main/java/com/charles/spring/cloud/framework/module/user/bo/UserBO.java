package com.charles.spring.cloud.framework.module.user.bo;

import com.charles.spring.cloud.framework.module.order.bo.OrderBO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 9:46 AM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBO {

    private Integer id;

    private String username;

    private String passwork;

    private List<OrderBO> orders;

}
