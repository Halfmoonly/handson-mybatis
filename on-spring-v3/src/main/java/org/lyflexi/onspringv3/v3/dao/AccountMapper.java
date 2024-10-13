package org.lyflexi.onspringv3.v3.dao;


import org.lyflexi.onspringv3.v3.anno.TulingSelect;
import org.lyflexi.onspringv3.v3.entity.AccountInfo;

/**
 * [来个全套]
 *
 * @slogan: 高于生活，源于生活
 * @Description: TODO
 * @author: smlz
 * @date 2020/5/4 16:44
 */

public interface AccountMapper {

	@TulingSelect(value = "select * from account_info where account_id=?")
	AccountInfo qryAccount(Integer accountId);
}