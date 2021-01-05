package kr.co.kyobo.mapper.maximo.tower;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IgnoreMapper {
	int findCount(Map<String, Object> params);
	
	List<Map<String, Object>> findAll(Map<String, Object> params);
	
	Map<String, Object> findOne(Map<String, Object> params);
	
	int insert(Map<String, Object> data);
	
	int update(Map<String, Object> data);
	
	int delete(Map<String, Object> data);
	
	int saveIgnoreDate(Map<String, Object> data);
	
	int saveSlaGroupId(Map<String, Object> data);
}
