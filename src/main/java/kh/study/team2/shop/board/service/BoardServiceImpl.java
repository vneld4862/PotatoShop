package kh.study.team2.shop.board.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.team2.shop.board.vo.BoardVO;
import kh.study.team2.shop.board.vo.ReviewImgVO;


@Service("boardService")
public class BoardServiceImpl implements BoardService{
	@Autowired
	SqlSessionTemplate sqlSession;

	@Override
	public void insertReview(BoardVO boardVO, ReviewImgVO uploadInfo) {
		sqlSession.insert("boardMapper.insertReview", boardVO);
		
		if(uploadInfo.getAttachedName() != null) {
			sqlSession.insert("boardMapper.insertReviewImg", boardVO);
		}
		System.out.println(boardVO.getItemCode());
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


	
}
