package kr.co.kyobo.hst.baseLineHst.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kyobo.mapper.maximo.bill.BaseLineMapper;
import kr.co.kyobo.mapper.maximo.hst.BaseLineHstMapper;

@Service
public class BaseLineHstService {
	
	@Autowired
	public BaseLineHstMapper baseLineHstMapper;
	
	public int findCount(Map<String, Object> params) {
		return baseLineHstMapper.findCount(params);
	}
	
	public List<Map<String, Object>> findAll(Map<String, Object> params) {
		return baseLineHstMapper.findAll(params);
	}
	
}
