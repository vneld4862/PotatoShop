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
	
	//리뷰 수정
	@Override
	public void updateReview(BoardVO boardVO) {
		sqlSession.update("boardMapper.updateReview", boardVO);
		
		//리뷰 수정 시 사진을 새로 업로드했을 경우
			//리뷰 등록 시 사진을 업로드했을 경우
			//1. 기존 사진 Delete
			//2. 새로 업로드한 사진 insert
			
		//리뷰 등록 시 사진을 업로드하지 않았을 경우
			//1. 새로 업로드한 사진 insert
		
//		if(uploadInfo.getSavedName() != null) {
//			sqlSession.insert("boardMapper.insertReviewImg", boardVO);
//		}
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
	
	




	
}
