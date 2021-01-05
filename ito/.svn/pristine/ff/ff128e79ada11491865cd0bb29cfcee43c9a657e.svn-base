package kr.co.kyobo.tower.ignore.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kyobo.mapper.maximo.tower.IgnoreMapper;

@Service
public class IgnoreService {

	@Autowired
	public IgnoreMapper ignoreMapper;
	
	public int findCount(Map<String, Object> params) {
		return ignoreMapper.findCount(params);
	}
	
	public List<Map<String, Object>> findAll(Map<String, Object> params) {
		return ignoreMapper.findAll(params);
	}
	
	public Map<String, Object> findOne(Map<String, Object> params) {
		return ignoreMapper.findOne(params);
	}
	
	public int insert(Map<String, Object> insertData) {
		return ignoreMapper.insert(insertData);
	}
	
	public int update(Map<String, Object> insertData) {
		return ignoreMapper.update(insertData);
	}
	
	public int saveSlaGroupId(Map<String, Object> insertData) {
		return ignoreMapper.saveSlaGroupId(insertData);
	}
	
	public int saveIgnoreDate(Map<String, Object> insertData) {
		int resultCnt = ignoreMapper.delete(insertData);
		int dataListCnt = (int)insertData.get("dataListCnt");
		
		if(dataListCnt > 0) {
			resultCnt = ignoreMapper.insert(insertData);
		}
		
		return resultCnt;
	}
}
