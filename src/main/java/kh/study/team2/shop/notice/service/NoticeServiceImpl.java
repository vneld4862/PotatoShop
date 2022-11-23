package kh.study.team2.shop.notice.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.team2.shop.notice.vo.NoticeVO;


@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	SqlSessionTemplate sqlSession;

	@Override
	public void insertNotice(NoticeVO noticeVO) {
		sqlSession.insert("noticeMapper.insertNotice", noticeVO);
	}

	@Override
	public List<NoticeVO> selectNoticeList() {
		return sqlSession.selectList("noticeMapper.selectNoticeList");
	}

	@Override
	public NoticeVO selectNoticeDetail(int noticeNum) {
		return sqlSession.selectOne("noticeMapper.selectNoticeDetail", noticeNum);
	}

	@Override
	public void correctNotice(NoticeVO noticeVO) {
		sqlSession.update("noticeMapper.correctNotice", noticeVO);
	}

	@Override
	public void deleteNotice(int noticeNum) {
		sqlSession.delete("noticeMapper.deleteNotice", noticeNum);
	}

	@Override
	public int selectBoardCnt() {
		return sqlSession.selectOne("noticeMapper.selectBoardCnt");
	}
	


	
	
}