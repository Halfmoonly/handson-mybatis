package org.lyflexi.onspring.v1.dao;


import org.lyflexi.onspring.v1.anno.SqlSelect;
import org.lyflexi.onspring.v1.entity.AccountInfo;
import org.springframework.stereotype.Component;

/**
 * [来个全套]
 *
 * @slogan: 高于生活，源于生活
 * @Description: TODO
 * @author: lyflexi
 * @date 2020/5/4 19:43
 */
@Component
public interface AccountMapper {

	@SqlSelect(value = "select * from account_info where account_id=?")
	AccountInfo qryById(String accountId);

}
