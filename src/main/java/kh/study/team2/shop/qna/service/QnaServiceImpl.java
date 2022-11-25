package kh.study.team2.shop.qna.service;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("qnaService")
public class QnaServiceImpl implements QnaService{
	@Autowired
	SqlSessionTemplate sqlSession;


	
	
}