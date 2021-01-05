package kr.co.kyobo.sr.sr.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.kyobo.mapper.maximo.sr.SrMapper;

@Service
public class SrService {
	
	@Autowired
	public SrMapper srMapper;
	
	public int findCount(Map<String, Object> params) {
		return srMapper.findCount(params);
	}
	
	public List<Map<String, Object>> findAll(Map<String, Object> params) {
		return srMapper.findAll(params);
	}
	
	public List<Map<String, Object>> findDetailAll(Map<String, Object> params) {
		return srMapper.findDetailAll(params);
	}
	
	public List<Map<String, Object>> findReceiveAll(Map<String, Object> params) {
		return srMapper.findReceiveAll(params);
	}
	
	public Map<String, Object> findOne(Map<String, Object> params) {
		return srMapper.findOne(params);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int insert(Map<String, Object> insertData) throws Exception {
		int resultCnt = 0;
		int totalResultCnt = 0;
		
		try {
			// 요청정보 등록
			if("N".equals(insertData.get("SEND_TYPE"))) {
				// 신규 요청
				insertData.put("SR_SEQ", 0);
				resultCnt = srMapper.insert(insertData);
			} else {
				// 재요청
				resultCnt = srMapper.insertResend(insertData);
			}
			
			if(resultCnt == 0) {
				throw new Exception("Exception for rollback");
			} else {
				totalResultCnt++;
			}
			
			// 요청 상세 등록
			resultCnt = srMapper.insertDetail(insertData);
			
			if(resultCnt == 0) {
				throw new Exception("Exception for rollback");
			} else {
				totalResultCnt++;
			}
			
			// 수신자 등록
			// 수신자 목록
			List<Map<String, Object>> toList = (List<Map<String, Object>>)insertData.get("toList");
			if(toList.size() > 0) {
				resultCnt = srMapper.insertReceiveUser(insertData);
			}
			
		} catch(Exception e) {
			throw e;
		}
		
		return totalResultCnt;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int update(Map<String, Object> params) throws Exception {
		int resultCnt = 0;
		int totalResultCnt = 0;
		
		try {
			// 요청정보 수정
			resultCnt = srMapper.update(params);
			
			if(resultCnt == 0) {
				throw new Exception("Exception for rollback");
			} else {
				totalResultCnt++;
			}
			
			List<Map<String, Object>> detailList = (List<Map<String, Object>>)params.get("detailList");
			
			for(int i=0; i<detailList.size(); i++) {
				// 요청 상세 수정
				resultCnt = srMapper.updateDetail(detailList.get(i));
				
				if(resultCnt == 0) {
					throw new Exception("Exception for rollback");
				} else {
					totalResultCnt++;
				}
			}
		} catch(Exception e) {
			throw e;
		}
		
		return totalResultCnt;
	}
	
	public int insertDetail(Map<String, Object> insertData) {
		return srMapper.insertDetail(insertData);
	}
	
	public int updateDetail(Map<String, Object> insertData) {
		return srMapper.updateDetail(insertData);
	}
	
	public int insertReceiveUser(Map<String, Object> insertData) {
		return srMapper.insertReceiveUser(insertData);
	}
}
