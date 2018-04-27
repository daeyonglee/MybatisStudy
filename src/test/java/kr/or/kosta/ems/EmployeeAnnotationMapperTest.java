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
import kr.or.kosta.ems.employee.mapper.EmployeeAnnotationMapper;

/** EmployeeServiceImpl 이라 가정 */
public class EmployeeAnnotationMapperTest {
	
	String resource = "mybatis-config.xml";
	Logger logger = Logger.getLogger(EmployeeAnnotationMapperTest.class);
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
	public void testEmployeeList(){
		EmployeeAnnotationMapper mapper = sqlSession.getMapper(EmployeeAnnotationMapper.class);
		logger.info("--------------- 사원 전체 조회 ------------");
		List<Employee> list = mapper.listAll();
		for (Employee employee : list) {
			logger.info(employee);
		}
		
	}
	
	@Test
	public void testReadEmployee(){
		EmployeeAnnotationMapper mapper = sqlSession.getMapper(EmployeeAnnotationMapper.class);
		logger.info("--------------- 사원번호로 사원 조회 ------------");
		Employee employee = mapper.read(100);
		logger.info(employee);
		
	}

}
