package kr.co.kyobo.hst.contractHst.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kyobo.mapper.maximo.bill.ContractAmountMapper;
import kr.co.kyobo.mapper.maximo.hst.ContractAmountHstMapper;

@Service
public class ContractAmountHstService {

	@Autowired
	public ContractAmountHstMapper contractAmountHstMapper;
	
	public int findCount(Map<String, Object> params) {
		return contractAmountHstMapper.findCount(params);
	}
	public List<Map<String, Object>> findAll(Map<String, Object> params) {
		return contractAmountHstMapper.findAll(params);
	}
	
}
