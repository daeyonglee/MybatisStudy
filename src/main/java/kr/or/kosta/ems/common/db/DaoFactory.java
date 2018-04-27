package kr.or.kosta.ems.common.db;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;


/**
 * 서브팩토리클래스에서 다양한 기술을 사용하는 다양한 종류의 DAO 생성이 가능토록 추상팩토리클래스 정의 
 * Dao 생성 창구 단일화
 */
public abstract class DaoFactory {
	
	public abstract Object getDao(String daoFullName);
	public abstract Object getDao(Class daoType);
	
}
