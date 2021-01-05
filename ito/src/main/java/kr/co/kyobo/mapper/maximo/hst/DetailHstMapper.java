package kr.co.kyobo.mapper.maximo.hst;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DetailHstMapper {
	int findCount(Map<String, Object> params);
	
	List<Map<String, Object>> findAll(Map<String, Object> params);
	
	List<Map<String, Object>> findAllJson(Map<String, Object> params);
	
	Map<String, Object> findOne(Map<String, Object> params);
}
