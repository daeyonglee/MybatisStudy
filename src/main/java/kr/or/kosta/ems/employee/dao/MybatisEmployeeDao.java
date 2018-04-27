package kr.or.kosta.ems.employee.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.kosta.ems.employee.domain.Employee;

public class MybatisEmployeeDao implements EmployeeDao {
	
	private static final String NAMESPACE = "kr.or.kosta.ems.employee";
	
	private SqlSession sqlSession;
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<Employee> listAll(){
		return sqlSession.selectList(NAMESPACE + ".listAll");
	}

	@Override
	public Employee read(int id) {
		return sqlSession.selectOne(NAMESPACE + ".read", id);
	}

	@Override
	public List<Employee> readByName(String lastName) {
		return sqlSession.selectList(NAMESPACE + ".readByName", lastName);
	}

	@Override
	public void create(Employee employee) {
		sqlSession.insert(NAMESPACE + ".create", employee);
		sqlSession.commit();
	}

	@Override
	public void update(int id, String firstName, String lastName, int salary) {
		Employee emp = new Employee();
		emp.setId(id);
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setSalary(salary);
		sqlSession.update((NAMESPACE) + ".update", emp);
		sqlSession.commit();
	}

	@Override
	public void delete(int id) {
		sqlSession.delete(NAMESPACE + ".delete", id);
		sqlSession.commit();
	}
	
}





