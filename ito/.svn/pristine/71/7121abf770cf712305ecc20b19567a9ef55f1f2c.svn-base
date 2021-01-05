package kr.co.kyobo.bill.resource.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kyobo.mapper.maximo.bill.ContractMapper;
import kr.co.kyobo.mapper.maximo.bill.ResourceMapper;

@Service
public class ResourceService {

	@Autowired
	public ResourceMapper resourceMapper;
	
	public int findCount(Map<String, Object> params) {
		return resourceMapper.findCount(params);
	}
	
	public List<Map<String, Object>> findAll(Map<String, Object> params) {
		return resourceMapper.findAll(params);
	}
	
	public Map<String, Object> findOne(Map<String, Object> params) {
		return resourceMapper.findOne(params);
	}
	
	public int insert(Map<String, Object> insertData) {
		return resourceMapper.insert(insertData);
	}
	
	public int update(Map<String, Object> insertData) {
		return resourceMapper.update(insertData);
	}
	
	public int updateOrderNo(Map<String, Object> insertData) {
		return resourceMapper.updateOrderNo(insertData);
	}
}
