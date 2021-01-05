package kr.co.kyobo.hst.rateHst.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kyobo.mapper.maximo.bill.RateMapper;
import kr.co.kyobo.mapper.maximo.hst.RateHstMapper;

@Service
public class RateHstService {
	
	@Autowired
	public RateHstMapper rateHstMapper;
	
	public int findCount(Map<String, Object> params) {
		return rateHstMapper.findCount(params);
	}
	
	public List<Map<String, Object>> findAll(Map<String, Object> params) {
		return rateHstMapper.findAll(params);
	}
	
}
