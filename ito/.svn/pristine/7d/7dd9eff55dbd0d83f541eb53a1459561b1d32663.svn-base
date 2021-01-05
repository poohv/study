package kr.co.kyobo.mapper.maximo.bill;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ContractAmountMapper {
	List<Map<String, Object>> findAll(Map<String, Object> params);
	
	int insert(Map<String, Object> data);
	
	int delete(Map<String, Object> data);
	
	int insertHist(Map<String, Object> data);
}
