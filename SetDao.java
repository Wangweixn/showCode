package com.wqbr.gzoss.sys.setting.persistence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wqbr.gzoss.sys.setting.domain.Seting;

@Component
public class SetDao {

	@Autowired
	private SetMapper setMapper;

	public List<Seting> findSetByCondition(Seting vo) {
		RowBounds rb = new RowBounds(vo.getStartIndex(), vo.getPageSize());
		return setMapper.findSetByCondition(rb,vo);
	}

	public int findSetNumByCondition(Seting vo) {
		return setMapper.findSetNumByCondition(vo);
	}

	public boolean findSetBySbjbjg(Seting vo) {
		return setMapper.findSetBySbjbjg(vo);
	}

	public void insert(Seting vo) {
		setMapper.insert(vo);
	}

	public void delete(Seting vo) {
		setMapper.delete(vo);
	}
}
