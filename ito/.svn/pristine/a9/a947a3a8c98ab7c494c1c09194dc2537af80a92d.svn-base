package kr.co.kyobo.mapper.maximo.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
	int findCount(Map<String, Object> params);
	
	List<Map<String, Object>> findAll(Map<String, Object> params);
	
	Map<String, Object> findOne(Map<String, Object> params);
	
	List<Map<String, Object>> findUserRoleList(Map<String, Object> params);
	
	List<Map<String, Object>> findUserListByRole(Map<String, Object> params);
	
	int insertUserRole(Map<String, Object> data);
	
	int deleteUserRole(Map<String, Object> data);
	
	Map<String, Object> findLoginUser(Map<String, Object> params);
	
	List<Map<String, Object>> findLoginUserRoleList(Map<String, Object> params);
}
