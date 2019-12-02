package com.charles.spring.cloud.framework.module.order.bo;

import com.charles.spring.cloud.framework.module.goods.bo.GoodsBO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 1:35 PM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderBO {

    private Integer id;

    private String orderNumber;

    private String orderAddress;

    private Integer userId;

    private List<GoodsBO> goods;

}
