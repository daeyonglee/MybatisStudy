package kr.or.kosta.ems.employee.mapper;

import java.util.List;

import kr.or.kosta.ems.employee.domain.Employee;

/**
 * 사원 관련 영속성 처리 표준 규약
 * @author 김기정
 *
 */
public interface EmployeeMapper {
	
	/** 사원 전체 목록 조회 */
	public  List<Employee> listAll();
	
	/** 사원번호로 사원 조회*/
	public Employee read(int id);
	
	/** 사원이름으로 사원 조회*/
	//public List<Employee> readByName(String lastName);
	
	/** 사원 등록 */
	//public void create(Employee employee);
	
	/** 사원 정보 수정 */
	//public void update(int id , String firstName , String lastName , int salary);
	
	/** 사원 정보 삭제 */
	//public void delete(int id);
	
}









