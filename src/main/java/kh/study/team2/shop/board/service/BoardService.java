package kh.study.team2.shop.board.service;

import java.util.List;

import kh.study.team2.shop.board.vo.BoardVO;

public interface BoardService {
	//글 등록
	void insertReview(BoardVO boardVO);	
	
	//글 목록 조회
	List<BoardVO> selectBoardList(String memberId);

	//글 상세 조회
	BoardVO selectBoardDetail(String itemCode);
}
