package kh.study.team2.shop.board.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.study.team2.shop.board.vo.BoardVO;
import kh.study.team2.shop.board.vo.ReplyVO;
import kh.study.team2.shop.board.vo.ReviewImgVO;


@Service("boardService")
public class BoardServiceImpl implements BoardService{
	@Autowired
	SqlSessionTemplate sqlSession;

	//리뷰 작성
	@Override
	public void insertReview(BoardVO boardVO, ReviewImgVO uploadInfo) {
		sqlSession.insert("boardMapper.insertReview", boardVO);
		
		if(uploadInfo.getSavedName() != null) {
			sqlSession.insert("boardMapper.insertReviewImg", boardVO);
		}
	}

	//리뷰 작성 후 작성 여부 Y로 변경
	@Override
	public void ifWittenReview(BoardVO boardVO) {
		sqlSession.update("boardMapper.ifWittenReview", boardVO);
	}
	
	//리뷰 상세 조회
	@Override
	public BoardVO selectBoardDetail(String itemCode) {
		return sqlSession.selectOne("boardMapper.selectBoardDetail", itemCode);
	}
	
	
	
	//리뷰 수정 전 기존 리뷰에 이미지가 첨부돼 있는지 확인
	@Override
	public ReviewImgVO selectReviewImg(String itemCode) {
		return sqlSession.selectOne("boardMapper.reviewImgChk", itemCode);
	}
	
	//리뷰 수정
	@Override
	public void updateReview(BoardVO boardVO) {
		sqlSession.update("boardMapper.updateReview", boardVO);
	}
	
	//리뷰 이미지 수정
	@Override
	public void updateReviewImg(ReviewImgVO reviewImgVO) {
		sqlSession.update("boardMapper.updateReviewImg", reviewImgVO);
	}
	
	//리뷰 이미지 추가
	@Override
	public void insertReviewImg(BoardVO boardVO) {
		sqlSession.insert("boardMapper.insertReviewImg", boardVO);
	}
	
	
	
	//내 상점에 작성된 리뷰 조회
	@Override
	public List<BoardVO> selectBoardList(String seller) {
		return sqlSession.selectList("boardMapper.selectBoardList", seller);
	}
	
	//내가 작성한 리뷰 조회
	@Override
	public List<BoardVO> selectWrittenReviewList(String buyer) {
		return sqlSession.selectList("boardMapper.selectWrittenReviewList", buyer);
	}
	
	//리뷰 삭제
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteReview(String itemCode) {
		//그 상품의 리뷰에 달린 모든 댓글 삭제
		sqlSession.delete("boardMapper.deleteReviewReplyAll", itemCode);
		//상품 리뷰 삭제
		sqlSession.delete("boardMapper.deleteReview", itemCode);
		sqlSession.delete("boardMapper.deleteReviewImg", itemCode);
	}

	//리뷰 작성 후 작성 여부 Y로 변경
	@Override
	public void updateReviewBefore(String itemCode) {
		sqlSession.update("boardMapper.updateReviewBefore", itemCode);
	}
	
	//리뷰 댓글 작성
	@Override
	public void insertReviewReply(ReplyVO replyVO) {
		sqlSession.insert("boardMapper.insertReviewReply", replyVO);
	}
	
	//리뷰 댓글 조회
	@Override
	public List<ReplyVO> selectReviewReply(int boardNum) {
		return sqlSession.selectList("boardMapper.selectReviewReply", boardNum);
	}
	
	//리뷰 댓글 삭제
	@Override
	public void deleteReply(int replyNum) {
		sqlSession.delete("boardMapper.deleteReviewReply", replyNum);
	}

	//해당 상점에 작성된 리뷰 목록 조회
	@Override
	public List<BoardVO> selectmarketReviewList(String seller) {
		return sqlSession.selectList("boardMapper.selectmarketReviewList", seller);
	}
	









	
}
