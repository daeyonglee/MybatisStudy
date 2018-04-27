package kr.or.kosta.ems;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import kr.or.kosta.ems.common.db.DaoFactory;
import kr.or.kosta.ems.common.db.MyBatisDaoFactory;
import kr.or.kosta.ems.employee.dao.EmployeeDao;
import kr.or.kosta.ems.employee.dao.MybatisEmployeeDao;
import kr.or.kosta.ems.employee.domain.Employee;

/** EmployeeServiceImpl 이라 가정 */
public class MybatisEmployeeDaoTest {
	
	Logger logger = Logger.getLogger(MybatisEmployeeDaoTest.class);
	
	DaoFactory factory = new MyBatisDaoFactory();
	
//	@Test
	public void test1(){
		EmployeeDao dao = (EmployeeDao)factory.getDao(MybatisEmployeeDao.class);
		List<Employee> list = dao.listAll();
		for (Employee employee : list) {
			logger.info(employee);
		}
	}
	
	@Test
	public void test2(){
		EmployeeDao dao = (EmployeeDao)factory.getDao(MybatisEmployeeDao.class);
		Employee employee = dao.read(173);
		logger.info(employee);
	}
	
//	@Test
	public void test3() {
		EmployeeDao dao = (EmployeeDao)factory.getDao(MybatisEmployeeDao.class);
		dao.update(173, "Sunditata", "Kumarmar", 9000);
		Employee employee = dao.read(173);
		logger.info(employee);
	}
	
//	@Test
	public void test4() {
		EmployeeDao dao = (EmployeeDao) factory.getDao(MybatisEmployeeDao.class);
		List<Employee> list = dao.readByName("%Kumarmar%");
		for (Employee employee : list) {
			logger.info(employee);
		}
	}
	
//	@Test
	public void test5() {
		EmployeeDao dao = (EmployeeDao) factory.getDao(MybatisEmployeeDao.class);
		Employee emp = new Employee();
		emp.setId(2929);
		emp.setFirstName("lee");
		emp.setLastName("daeyong");
		emp.setEmail("dleo200@naver.com");
		emp.setPhoneNumber("01083768171");
		emp.setHireDate("2018-04-27");
		emp.setSalary(9000);
		emp.setJobId("IT_PROG");
		emp.setDepartmentId(10);
		emp.setManagerId(150);
		dao.create(emp);
	}
	
//	@Test
	public void test6() {
		EmployeeDao dao = (EmployeeDao) factory.getDao(MybatisEmployeeDao.class);
		dao.delete(173);
	}

}
