package kr.or.kosta.ems;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import kr.or.kosta.ems.employee.domain.Employee;
import kr.or.kosta.ems.employee.mapper.EmployeeMapper;


/** EmployeeServiceImpl 이라 가정 */
public class EmployeeMapperTest {
	
	String resource = "mybatis-config.xml";
	Logger logger = Logger.getLogger(EmployeeMapperTest.class);
	
	SqlSession sqlSession;

	@Before
	public void setUp() {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		sqlSession = sqlSessionFactory.openSession();
	}
	
	
	@Test
	public void employeeList(){
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		List<Employee> list = mapper.listAll();
		for (Employee employee : list) {
			logger.info(employee);
		}
		
	}
	
	@Test
	public void readEmployee(){
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		Employee employee = mapper.read(150);
		logger.info(employee);
	}

}







