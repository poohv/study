package kr.co.kyobo.hst.detailHst.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kyobo.mapper.maximo.bill.DetailMapper;
import kr.co.kyobo.mapper.maximo.hst.DetailHstMapper;

@Service
public class DetailHstService {
	
	@Autowired
	public DetailHstMapper detailHstMapper;
	
	public int findCount(Map<String, Object> params) {
		return detailHstMapper.findCount(params);
	}
	
	public List<Map<String, Object>> findAll(Map<String, Object> params) {
		return detailHstMapper.findAll(params);
	}
	
	public List<Map<String, Object>> findAllJson(Map<String, Object> params) {
		return detailHstMapper.findAllJson(params);
	}
	
	public Map<String, Object> findOne(Map<String, Object> params) {
		return detailHstMapper.findOne(params);
	}
	
}
