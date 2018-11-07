package com.wqbr.gzoss.sys.setting.persistence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.wqbr.gzoss.sys.setting.domain.Seting;

@Repository
public interface SetMapper {

	/**
	 *@作者:Mr.WangWX
	 *@日期:2018年9月30日
	 *@描述:查询保存的数据列表展示
	 */
	public List<Seting> findSetByCondition(RowBounds rb, Seting vo);

	/**
	 *@作者:Mr.WangWX
	 *@日期:2018年9月30日
	 *@描述:查询保存的数据列表展示条数
	 */
	public int findSetNumByCondition(Seting vo);

	/**
	 *@作者:Mr.WangWX
	 *@日期:2018年9月30日
	 *@描述:查询是否保存
	 */
	public boolean findSetBySbjbjg(Seting vo);

	/**
	 *@作者:Mr.WangWX
	 *@日期:2018年9月30日
	 *@描述:保存
	 */
	public void insert(Seting vo);

	/**
	 *@作者:Mr.WangWX
	 *@日期:2018年9月30日
	 *@描述:删除
	 */
	public void delete(Seting vo);

}
