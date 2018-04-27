package kr.or.kosta.ems;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import kr.or.kosta.ems.employee.domain.Employee;

public class MybatisTest {

	String resource = "mybatis-config.xml";
	Logger logger = Logger.getLogger(MybatisTest.class);
	
	SqlSession sqlSession;

	private static final String namespace = "kr.or.kosta.ems.employee";

	@Before
	public void setUp() {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		sqlSession = sqlSessionFactory.openSession(true); //auto commint   openSession(true);
//		sqlSession = sqlSessionFactory.openSession(false);
		logger.debug("sqlSession 생성 완료..");
	}

//	@Test
	public void test1() {
		logger.debug("==================== 전체사원 조회 ========================");
		List<Employee> employeeList = sqlSession.selectList("kr.or.kosta.ems.employee.selectAll");
		for (Employee employee : employeeList) {
			logger.debug(employee);
		}
	}

//  @Test
	public void test2() {
		logger.debug("==================== 사원번호로 사원조회 ========================");
		int id = 200;
		Employee employee = sqlSession.selectOne(namespace + ".selectEmployeeById", id);
		logger.debug(employee);
	}

//	@Test
	public void test3() {
		logger.debug("==================== 사원번호로 급여조회 ========================");
		int id = 200;
		int salary = sqlSession.selectOne(namespace + ".selectSalaryById", id);
		logger.debug("받는 급여 : " + salary);
	}

	@Test
	public void test4() {
		logger.debug("==================== 급여범위로 사원검색 ========================");
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("min", 10000);
		params.put("max", 100000);
		List<Employee> employeeList = sqlSession.selectList(namespace + ".selectEmployeesBySalary", params);
		for (Employee emp : employeeList) {
			logger.debug(emp);
		}
	}

//	@Test
	public void test5() {
		logger.debug("==================== 성으로 사원검색 ========================");
		String searchName = "e";
		searchName = "%" + searchName.toUpperCase() + "%"; // E(e)가 포함된 모든 성
		List<Employee> employeeList = sqlSession.selectList(namespace + ".selectEmployeesByLastName", searchName);
		for (Employee emp : employeeList) {
			logger.debug(emp);
		}
	}

//	@Test
	public void test6() {
		logger.debug("==================== 부서명 포함 전체사원 조회(조인) ========================");
		List<Map<String, Object>> list = sqlSession.selectList(namespace + ".selectEmployeesWithDepartment");
		for (Map<String, Object> row : list) {
			// System.out.println(row);
			// 숫자형은 BigDecimal로 받음
			BigDecimal empId = (BigDecimal) row.get("id");
			String firstName = (String) row.get("firstName");
			String lastName = (String) row.get("lastName");
			String departmentName = (String) row.get("departmentName");
			logger.debug(empId + "\t" + firstName + "\t" + lastName + "\t" + departmentName);
		}
	}

//	@Test
	public void test7() {
		logger.debug("==================== ResultMap을 이용한 전체사원 조회 ========================");
		List<Employee> employeeList = sqlSession.selectList(namespace + ".selectAll2");
		for (Employee e : employeeList) {
			logger.debug(e);
		}
	}

//	@Test
	public void test8() {
		Employee emp = new Employee();
		emp.setFirstName("KiJung");
		emp.setLastName("Kim");
		emp.setEmail("kimkijung@gmail.com");
		emp.setPhoneNumber("010.9179.87087");
		emp.setHireDate("2016-11-02");
		emp.setJobId("IT_PROG");
		emp.setSalary(50000);
		emp.setManagerId(150);
		emp.setDepartmentId(60);

		sqlSession.insert(namespace + ".insertEmployee", emp);
		//sqlSession.commit();
		// sqlSession.rollback();
		logger.debug("신규사원 등록 완료");
	}

//	@Test
	public void test9() {
		Employee emp = new Employee();
		emp.setId(200);
		emp.setFirstName("볶이");
		emp.setLastName("떡");
		emp.setSalary(70000);
		sqlSession.update(namespace + ".updateEmployee", emp);
		sqlSession.commit();
		logger.debug("사원정보 수정 완료");
	}

//	@Test
	public void test10() {
		logger.debug("==================== 검색타입별 사원 검색(동적SQL 활용) ========================");
		Map<String, Object> searchParams = new HashMap<String, Object>();
//		searchParams.put("searchType", "id");
//		searchParams.put("searchValue", 150);

		searchParams.put("searchType", "name");
		searchParams.put("searchValue", "E%");

		List<Employee> employeeList = sqlSession.selectList(namespace + ".dynamicSQL", searchParams);
		for (Employee e : employeeList) {
			logger.debug(e);
		}
	}

}
