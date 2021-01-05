package kr.co.kyobo.sr.srGroup.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.kyobo.mapper.maximo.sr.SrGroupMapper;

@Service
public class SrGroupService {

	@Autowired
	public SrGroupMapper srGroupMapper;
	
	public int findCount(Map<String, Object> params) {
		return srGroupMapper.findCount(params);
	}
	
	public List<Map<String, Object>> findAll(Map<String, Object> params) {
		return srGroupMapper.findAll(params);
	}
	
	public Map<String, Object> findOne(Map<String, Object> params) {
		return srGroupMapper.findOne(params);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int insert(Map<String, Object> insertData) throws Exception {
		int resultCnt = 1;
		
		try {
			// 삭제
			resultCnt += srGroupMapper.delete(insertData);
			
			// 등록
			List<Map<String, Object>> userList = (List<Map<String, Object>>)insertData.get("userList");
			
			if(userList.size() > 0) {
				resultCnt += srGroupMapper.insert(insertData);
				
				if(resultCnt == 0) {
					throw new Exception("Exception for rollback");
				}
			}
		} catch(Exception e) {
			throw e;
		}
		
		return resultCnt;
	}
	
	public List<Map<String, Object>> findTowerGroupList(Map<String, Object> params) {
		return srGroupMapper.findTowerGroupList(params);
	}
	
	public List<Map<String, Object>> findTowerGroupNmList(Map<String, Object> params) {
		return srGroupMapper.findTowerGroupNmList(params);
	}
}
