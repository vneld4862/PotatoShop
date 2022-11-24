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
	public void updateReview(BoardVO boardVO) {
		sqlSession.update("boardMapper.updateReview", boardVO);
	}

	@Override
	public List<BoardVO> selectBoardList(String memberId) {
		return sqlSession.selectList("boardMapper.selectBoardList", memberId);
	}

	@Override
	public BoardVO selectBoardDetail(String itemCode) {
		return sqlSession.selectOne("boardMapper.selectBoardDetail", itemCode);
	}

	@Override
	public List<BoardVO> selectWrittenReviewList(String memberId) {
		return sqlSession.selectList("boardMapper.selectWrittenReviewList", memberId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteReview(String itemCode) {
		//그 상품의 리뷰에 달린 모든 댓글 삭제
		sqlSession.delete("boardMapper.deleteReviewReplyAll", itemCode);
		//상품 리뷰 삭제
		sqlSession.delete("boardMapper.deleteReview", itemCode);
	}

	@Override
	public void insertReviewReply(ReplyVO replyVO) {
		sqlSession.insert("boardMapper.insertReviewReply", replyVO);
	}

	@Override
	public List<ReplyVO> selectReviewReply(int boardNum) {
		return sqlSession.selectList("boardMapper.selectReviewReply", boardNum);
	}

	@Override
	public void deleteReply(int replyNum) {
		sqlSession.delete("boardMapper.deleteReviewReply", replyNum);
	}



	
}
