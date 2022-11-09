package kh.study.team2.shop.board.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.team2.shop.board.vo.BoardVO;


@Service("boardService")
public class BoardServiceImpl implements BoardService{
	@Autowired
	SqlSessionTemplate sqlSession;

	@Override
	public void insertBoard(BoardVO boardVO) {
		sqlSession.insert("boardMapper.insertBoard", boardVO);
	}


	
}
