package kr.co.kyobo.bill.contract.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kyobo.mapper.maximo.bill.ContractMapper;

@Service
public class ContractService {

	@Autowired
	public ContractMapper contractMapper;
	
	public int findCount(Map<String, Object> params) {
		return contractMapper.findCount(params);
	}
	
	public List<Map<String, Object>> findAll(Map<String, Object> params) {
		return contractMapper.findAll(params);
	}
	
	public Map<String, Object> findOne(Map<String, Object> params) {
		return contractMapper.findOne(params);
	}
	
	public int insert(Map<String, Object> insertData) {
		return contractMapper.insert(insertData);
	}
	
	public int update(Map<String, Object> insertData) {
		return contractMapper.update(insertData);
	}
}
