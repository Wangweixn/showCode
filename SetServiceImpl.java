package com.wqbr.gzoss.sys.setting.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wqbr.gzoss.sys.base.service.CommonService;
import com.wqbr.gzoss.sys.setting.domain.Seting;
import com.wqbr.gzoss.sys.setting.persistence.SetDao;
import com.wqbr.gzoss.sys.setting.service.SetService;

@Transactional(rollbackFor = Exception.class)
@Service(value = "setServiceImpl")
public class SetServiceImpl implements SetService {

	@Autowired
	private SetDao setDao;
	@Resource
	private CommonService commonService;
	
	@Override
	public List<Seting> findSetByCondition(Seting vo) throws Exception{
		return setDao.findSetByCondition(vo);
	}

	@Override
	public int findSetNumByCondition(Seting vo) throws Exception{
		return setDao.findSetNumByCondition(vo);
	}

	@Override
	public boolean findSetBySbjbjg(Seting vo) throws Exception{
		return setDao.findSetBySbjbjg(vo);
	}

	@Override
	public void insert(Seting vo) throws Exception{
		setDao.insert(vo);
	}

	@Override
	public void delete(Seting vo) throws Exception{
		setDao.delete(vo);
	}
	
}
