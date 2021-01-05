package kr.co.kyobo.mapper.maximo.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CommonCodeMapper {
	int findCount(Map<String, Object> params);
	
	List<Map<String, Object>> findCodeList(Map<String, Object> params);
	
	List<Map<String, Object>> findList(Map<String, Object> params);
	
	List<Map<String, Object>> baseLineSeq(Map<String, Object> params);
	
	List<Map<String, Object>> rateSeq(Map<String, Object> params);
	
	List<Map<String, Object>> detailSeq(Map<String, Object> params);
	
	List<Map<String, Object>> contractAmountSeq(Map<String, Object> params);
	
	Map<String, Object> findCode(Map<String, Object> params);
	
	List<Map<String, Object>> findAll(Map<String, Object> params);
	
	Map<String, Object> findOne(Map<String, Object> params);
	
	int updateOrderNo(Map<String, Object> data);
}
