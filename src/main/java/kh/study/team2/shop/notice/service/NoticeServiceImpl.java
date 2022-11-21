package kh.study.team2.shop.notice.service;

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
	


	
	
}