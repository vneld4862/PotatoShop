package kh.study.team2.shop.board.service;

import java.util.List;

import kh.study.team2.shop.board.vo.BoardVO;
import kh.study.team2.shop.board.vo.ReviewImgVO;

public interface BoardService {
	//리뷰 등록
	void insertReview(BoardVO boardVO, ReviewImgVO uploadInfo);	
	
	//리뷰 목록 조회
	List<BoardVO> selectBoardList(String memberId);

	//리뷰 상세 조회
	BoardVO selectBoardDetail(String itemCode);
}
