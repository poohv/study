package kr.co.kyobo.bill.contract.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kyobo.mapper.maximo.bill.ContractAmountMapper;

@Service
public class ContractAmountService {

	@Autowired
	public ContractAmountMapper contractAmountMapper;
	
	public List<Map<String, Object>> findAll(Map<String, Object> params) {
		return contractAmountMapper.findAll(params);
	}
	
	public int insert(Map<String, Object> insertData) {
		int resultCnt = 0;
		
		// 이력 저장
		resultCnt += contractAmountMapper.insertHist(insertData);
		
		// 기존 데이터 삭제
		resultCnt += contractAmountMapper.delete(insertData);
		
		// 데이터 저장
		resultCnt += contractAmountMapper.insert(insertData);
		
		return resultCnt;
	}
}
