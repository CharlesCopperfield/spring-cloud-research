package com.charles.spring.cloud.framework.module.goods.bo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderGoodsRelBO {

    private Integer id;

    private Integer goodsId;

    private Integer orderId;

    private Integer goodsNum;

}