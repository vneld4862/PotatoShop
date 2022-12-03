package kh.study.team2.shop.board.service;

import java.util.List;

import kh.study.team2.shop.board.vo.BoardVO;
import kh.study.team2.shop.board.vo.ReplyVO;
import kh.study.team2.shop.board.vo.ReviewImgVO;

public interface BoardService {
	//리뷰 등록
	void insertReview(BoardVO boardVO, ReviewImgVO uploadInfo);	
	
	//리뷰 작성 여부 업데이트(Y)
	void ifWittenReview(BoardVO boardVO);
	
	//리뷰 상세 조회
	BoardVO selectBoardDetail(String itemCode);
	
	//내 상점에 작성된 리뷰 조회
	List<BoardVO> selectBoardList(String seller);
	
	//내가 작성한 리뷰 조회
	List<BoardVO> selectWrittenReviewList(String buyer);
	
	//리뷰 삭제
	void deleteReview(String itemCode);
	
	//리뷰 작성 여부 업데이트(N)
	void updateReviewBefore(String itemCode);
	
	//해당 상점에 작성된 리뷰 목록 조회
	List<BoardVO> selectmarketReviewList(String seller);
	
///////////////////리뷰 수정///////////////////	

	//리뷰 이미지 여부 조회
	ReviewImgVO selectReviewImg(String itemCode);
	
	//리뷰 수정
	void updateReview(BoardVO boardVO);
	
	//이미지 있는 리뷰 수정 시 이미지 업데이트
	void updateReviewImg(ReviewImgVO reviewImgVO);
	
	//이미지 없는 리뷰 수정 시 이미지 등록
	void insertReviewImg(BoardVO boardVO);
	
	
///////////////////리뷰 댓글///////////////////		
	
	//리뷰 댓글 작성
	void insertReviewReply(ReplyVO replyVO);
	
	//리뷰 댓글 조회
	List<ReplyVO> selectReviewReply(int boardNum);
	
	//리뷰 댓글 삭제
	void deleteReply(int replyNum);
	
}
