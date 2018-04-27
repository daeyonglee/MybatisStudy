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

import kr.or.kosta.ems.user.domain.User;
import kr.or.kosta.ems.user.mapper.UserMapper;


/** UserServiceImpl 이라 가정 */
public class UserMapperTest {
	
	String resource = "mybatis-config.xml";
	Logger logger = Logger.getLogger(UserMapperTest.class);
	
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
	
	
//	@Test
	public void test1(){
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		
		for(int i=10; i<=110; i++) {
			mapper.create(new User("hyunsu"+i, "박현수"+i, "1234"+i, "hyunsu"+i+"@naver.com", "010-8482-9382", null, null));
			sqlSession.commit();
		}
		
	}
	
//	@Test
	public void test2(){
		UserMapper dao = sqlSession.getMapper(UserMapper.class);
		User user = dao.read("hyunsu");
		logger.info(user);
	}
	
//	@Test
	public void test3() {
		UserMapper dao = sqlSession.getMapper(UserMapper.class);
		dao.update(new User("hyunsu", "박현수1", "2345", "hyunsu@gmail.com", "010-0000-2222", null, null));
		sqlSession.commit();
	}
	
//	@Test
	public void test4() {
		UserMapper dao = sqlSession.getMapper(UserMapper.class);
		User user = dao.isMember("hyunsu", "2345");
		logger.info(user);
	}
	
//	@Test
	public void test5() {
		UserMapper dao = sqlSession.getMapper(UserMapper.class);
		List<User> list = dao.listAll();
		
		for (User user : list) {
			logger.info(user);
		}
	}
	
//	@Test
	public void test6() {
		UserMapper dao = sqlSession.getMapper(UserMapper.class);
		List<User> list = dao.listByPage(3);
		
		for (User user : list) {
			logger.info(user);
		}
	}
	
	@Test
	public void test7() {
		UserMapper dao  = sqlSession.getMapper(UserMapper.class);
		List<User> list = dao.listByParams(2, "name", "%박현수%", 10); 
		for (User user : list) {
			logger.info(user);
		}
	}

}







