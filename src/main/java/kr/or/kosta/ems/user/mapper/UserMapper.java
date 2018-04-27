package kr.or.kosta.ems.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.kosta.ems.user.domain.User;

/**
 * 회원관련 Dao 주요 스펙
 * 
 * @author 김기정
 */
public interface UserMapper {
	
	/** 신규 사용자 등록 */
	public void create(User user);
	
	/** 사용자아이디를 이용한 사용자 상세 정보 조회 */
	public User read(String id);
	
	/** 회원정보 수정 */
	public void update(User user);
	
	/** 회원 여부 반환 */
	public User isMember(@Param("id") String id, @Param("passwd") String passwd);
	
	/** 전체 회원목록 반환 */
	public List<User> listAll();
	
	/** 선택 페이지에 대한 회원목록 반환 */
	public List<User> listByPage(int page);
	
	/** {선택페이지, 검색유형, 검색값, 한페이지당 출력 행수}에 대한 회원목록 반환 */
	public List<User> listByParams(@Param("page") int page, @Param("searchType")String searchType, @Param("searchValue")String searchValue, @Param("pageSize")int pageSize);
	
}







