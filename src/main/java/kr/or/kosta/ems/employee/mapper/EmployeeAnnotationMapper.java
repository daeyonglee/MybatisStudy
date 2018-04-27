package kr.or.kosta.ems.employee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import kr.or.kosta.ems.employee.domain.Employee;

/**
 * 사원 관련 영속성 처리 표준 규약
 * MyBatis Annotation 활용
 * @author 김기정
 *
 */
public interface EmployeeAnnotationMapper {
	
	@Results({
		@Result(property="id", column="employee_id"),
		@Result(property="firstName", column="first_name"),
		@Result(property="lastName", column="last_name"),
		@Result(property="email", column="email"),
		@Result(property="phoneNumber", column="phone_number"),
		@Result(property="hireDate", column="hireDate"),
		@Result(property="salary", column="salary")
	})
	
	@Select("SELECT employee_id," +
	        "       first_name," +
			"       last_name," +
	        "       email," +
			"       phone_number," +
	        "       TO_CHAR(hire_date, 'YYYY-MM-DD') hireDate,"+ 
			"       salary" +
	        "		FROM   employees" +
			"		ORDER BY hire_date ASC")
	public  List<Employee> listAll();
	
	
	@Results({
		@Result(property="id", column="employee_id"),
		@Result(property="firstName", column="first_name"),
		@Result(property="lastName", column="last_name"),
		@Result(property="email", column="email"),
		@Result(property="phoneNumber", column="phone_number"),
		@Result(property="hireDate", column="hireDate"),
		@Result(property="salary", column="salary")
	})
	@Select("SELECT employee_id," +
	        "       first_name," +
			"       last_name," +
	        "       email," +
			"       phone_number," +
	        "       TO_CHAR(hire_date, 'YYYY-MM-DD') hireDate,"+ 
			"       salary" +
	        "		FROM   employees" +
	        "       WHERE  employee_id = #{id}" +
			"		ORDER BY hire_date ASC")
	public Employee read(int id) throws RuntimeException;
}









