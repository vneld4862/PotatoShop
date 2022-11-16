package kh.study.team2.shop.board.service;

import java.util.List;

import kh.study.team2.shop.board.vo.BoardVO;
import kh.study.team2.shop.board.vo.ReviewImgVO;

public interface BoardService {
	//리뷰 등록
	void insertReview(BoardVO boardVO, ReviewImgVO uploadInfo);	
	
	//내 상점에 작성된 리뷰 조회(매개변수 수정 필요)
	List<BoardVO> selectBoardList(String memberId);

	//리뷰 상세 조회
	BoardVO selectBoardDetail(String itemCode);
	
	//내가 작성한 리뷰 조회
	List<BoardVO> selectWrittenReviewList(String memberId);
	
}
