package org.lyflexi.onspring.v1.dao;


import org.lyflexi.onspring.v1.anno.SqlSelect;
import org.lyflexi.onspring.v1.entity.ProductInfo;
import org.springframework.stereotype.Component;

/**
 * [来个全套]
 *
 * @slogan: 高于生活，源于生活
 * @Description: TODO
 * @author: lyflexi
 * @date 2020/5/5 14:03
 */
@Component
public interface ProductMapper {

	@SqlSelect(value = "select * from product_info where product_id=?")
	ProductInfo qryById(Integer productId);
}
