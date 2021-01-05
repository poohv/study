package kr.co.kyobo.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kyobo.mapper.maximo.common.CommonCodeMapper;

@Service
public class CommonCodeService {
	@Autowired
	private CommonCodeMapper commonCodeMapper;
	
	public List<Map<String, Object>> findCodeList(Map<String, Object> params) {
		return commonCodeMapper.findCodeList(params);
	}
	
	public List<Map<String, Object>> findList(Map<String, Object> params) {
		return commonCodeMapper.findList(params);
	}
	
	public List<Map<String, Object>> baseLineSeq(Map<String, Object> params) {
		return commonCodeMapper.baseLineSeq(params);
	}
	
	public List<Map<String, Object>> rateSeq(Map<String, Object> params) {
		return commonCodeMapper.rateSeq(params);
	}
	
	public List<Map<String, Object>> detailSeq(Map<String, Object> params) {
		return commonCodeMapper.detailSeq(params);
	}
	
	public List<Map<String, Object>> contractAmountSeq(Map<String, Object> params) {
		return commonCodeMapper.contractAmountSeq(params);
	}
	
	public Map<String, Object> findCode(Map<String, Object> params) {
		return commonCodeMapper.findCode(params);
	}
	
	public int findCount(Map<String, Object> params) {
		return commonCodeMapper.findCount(params);
	}
	
	public List<Map<String, Object>> findAll(Map<String, Object> params) {
		return commonCodeMapper.findAll(params);
	}
	
	public int updateOrderNo(Map<String, Object> insertData) {
		return commonCodeMapper.updateOrderNo(insertData);
	}
}
