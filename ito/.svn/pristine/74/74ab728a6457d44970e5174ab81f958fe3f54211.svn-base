package kr.co.kyobo.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.kyobo.mapper.maximo.attach.AttachMapper;

@Service
public class AttachService {
	
	@Autowired
	public AttachMapper attachMapper;
	
	public int findCount(Map<String, Object> params) {
		return attachMapper.findCount(params);
	}
	
	public List<Map<String, Object>> findAll(Map<String, Object> params) {
		return attachMapper.findAll(params);
	}
	
	public Map<String, Object> findOne(Map<String, Object> params) {
		return attachMapper.findOne(params);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int insert(Map<String, Object> insertData) throws Exception {
		int resultCnt = 0;
		int totalResultCnt = 0;
		
		try {
			// 첨부파일 등록
			resultCnt = attachMapper.insert(insertData);
			
			if(resultCnt == 0) {
				throw new Exception("Exception for rollback");
			} else {
				totalResultCnt++;
			}
			
			// 첨부파일 상세 등록
			resultCnt = attachMapper.insertDetail(insertData);
			
			if(resultCnt == 0) {
				throw new Exception("Exception for rollback");
			} else {
				totalResultCnt++;
			}
		} catch(Exception e) {
			throw e;
		}
		
		return totalResultCnt;
	}
	
	public int delete(Map<String, Object> attachData) {
		int resultCnt = 0;
		
		resultCnt += attachMapper.delete(attachData);
		resultCnt += attachMapper.deleteDetail(attachData);
		
		return resultCnt;
	}
}
