package kr.co.kyobo.tower.group.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kyobo.mapper.maximo.tower.GroupMapper;

@Service
public class GroupService {

	@Autowired
	public GroupMapper groupMapper;
	
	public int findCount(Map<String, Object> params) {
		return groupMapper.findCount(params);
	}
	
	public List<Map<String, Object>> findAll(Map<String, Object> params) {
		return groupMapper.findAll(params);
	}
	
	public Map<String, Object> findOne(Map<String, Object> params) {
		return groupMapper.findOne(params);
	}
	
	public int insert(Map<String, Object> insertData) {
		return groupMapper.insert(insertData);
	}
	
	public int update(Map<String, Object> insertData) {
		return groupMapper.update(insertData);
	}
	
	public int saveSlaGroupId(Map<String, Object> insertData) {
		return groupMapper.saveSlaGroupId(insertData);
	}
	
	public int updateOrderNo(Map<String, Object> insertData) {
		return groupMapper.updateOrderNo(insertData);
	}
}
