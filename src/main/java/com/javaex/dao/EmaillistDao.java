package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.EmailVo;

@Repository
public class EmaillistDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insert(EmailVo vo) {
		
		int count=sqlSession.insert("emaillist.insert",vo);  //(Namespaces.id , vo)
		return count;
	
	}
	  //DAO가 처리안하고 get list() 메소드를 sqlSession한테 요청함
	//리턴타입을 Arraylist가 아니라 인터페이스타입 List로 써줘야함
	public List<EmailVo> getlist(){  //컨트롤러가 getlist메소드를 실행요청함
		return sqlSession.selectList("emaillist.list"); // 결과값이 하나면 one 결과값이 여러게이면 list로 써야함  
		 //new list  해줄 필요없고 알아서 처리해줌    //selectlist로 하면 list로 옴 
	}
	
	public List<EmailVo> getlist1(int no){
		no=3;
		return sqlSession.selectList("emaillist.list", no); 
		
	}
	
}
