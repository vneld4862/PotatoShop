package kh.study.team2.shop.board.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("boardService")
public class BoardServiceImpl implements BoardService{
	@Autowired
	SqlSessionTemplate sqlSession;


	
}
