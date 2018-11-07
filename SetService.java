package com.wqbr.gzoss.sys.setting.service;

import java.util.List;

import com.wqbr.gzoss.sys.setting.domain.Seting;


public interface SetService {

	/**
	 *@作者:Mr.WangWX
	 *@日期:2018年9月30日
	 *@描述:查询保存的数据
	 */
	public List<Seting> findSetByCondition(Seting vo) throws Exception;

	/**
	 *@作者:Mr.WangWX
	 *@日期:2018年9月30日
	 *@描述:查询保存的数据条数
	 */
	public int findSetNumByCondition(Seting vo) throws Exception;

	/**
	 *@作者:Mr.WangWX
	 *@日期:2018年9月30日
	 *@描述:查询是否保存
	 */
	public boolean findSetBySbjbjg(Seting vo) throws Exception;

	/**
	 *@作者:Mr.WangWX
	 *@日期:2018年9月30日
	 *@描述:保存
	 */
	public void insert(Seting vo) throws Exception;

	/**
	 *@作者:Mr.WangWX
	 *@日期:2018年9月30日
	 *@描述:删除
	 */
	public void delete(Seting vo) throws Exception;

}
