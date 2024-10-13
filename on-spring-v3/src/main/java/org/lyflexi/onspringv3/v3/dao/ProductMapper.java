package org.lyflexi.onspringv3.v3.dao;


import org.lyflexi.onspringv3.v3.anno.TulingSelect;
import org.lyflexi.onspringv3.v3.entity.ProductInfo;

/**
 * [来个全套]
 *
 * @slogan: 高于生活，源于生活
 * @Description: TODO
 * @author: lyflexi
 * @date 2020/5/4 15:01
 */

public interface ProductMapper {

	@TulingSelect(value = "select * from product_info where product_id=?")
	ProductInfo qryProductInfoById(Integer productId);
}
