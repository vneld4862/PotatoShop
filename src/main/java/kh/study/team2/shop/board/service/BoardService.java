package kh.study.team2.shop.board.service;

import java.util.List;

import kh.study.team2.shop.board.vo.BoardVO;

public interface BoardService {
	//글 등록
	void insertBoard(BoardVO boardVO);	
	
	//글 목록 조회
	List<BoardVO> selectBoardList(BoardVO boardVO);

	
}
