package kr.co.kyobo.mapper.maximo.sr;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SrMapper {
	int findCount(Map<String, Object> params);
	
	List<Map<String, Object>> findAll(Map<String, Object> params);
	
	Map<String, Object> findOne(Map<String, Object> params);
	
	List<Map<String, Object>> findDetailAll(Map<String, Object> params);
	
	List<Map<String, Object>> findReceiveAll(Map<String, Object> params);
	
	int insert(Map<String, Object> data);
	
	int insertResend(Map<String, Object> data);
	
	int update(Map<String, Object> data);
	
	int insertDetail(Map<String, Object> data);
	
	int updateDetail(Map<String, Object> data);
	
	int insertReceiveUser(Map<String, Object> data);
	
}
