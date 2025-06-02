package org.lyflexi.onspringv2.v2.dao;


import org.lyflexi.onspringv2.v2.anno.SqlSelect;
import org.lyflexi.onspringv2.v2.entity.AccountInfo;

/**
 * [来个全套]
 *
 * @slogan: 高于生活，源于生活
 * @Description: TODO
 * @author: lyflexi
 * @date 2020/5/4 16:44
 */

public interface AccountMapper {

	@SqlSelect(value = "select * from account_info where account_id=?")
	AccountInfo qryAccount(Integer accountId);
}
