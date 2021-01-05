package kr.co.kyobo.bill.detail.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kyobo.mapper.maximo.bill.DetailMapper;

@Service
public class DetailService {

	@Autowired
	public DetailMapper detailMapper;
	
	public int findCount(Map<String, Object> params) {
		return detailMapper.findCount(params);
	}
	
	public List<Map<String, Object>> findAll(Map<String, Object> params) {
		return detailMapper.findAll(params);
	}
	
	public List<Map<String, Object>> findAllJson(Map<String, Object> params) {
		return detailMapper.findAllJson(params);
	}
	
	public Map<String, Object> findOne(Map<String, Object> params) {
		return detailMapper.findOne(params);
	}
	
	public int insert(Map<String, Object> insertData) {
		int resultCnt = 0;
		
		// 이력 저장
		resultCnt += detailMapper.insertHist(insertData);
		
		// 기존 데이터 삭제
		resultCnt += detailMapper.delete(insertData);
		
		// 데이터 저장
		resultCnt += detailMapper.insert(insertData);
		
		return resultCnt;
	}
}
