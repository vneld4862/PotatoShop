package kh.study.team2.shop.notice.service;

import java.util.List;

import kh.study.team2.shop.notice.vo.NoticeVO;

public interface NoticeService {

	void insertNotice(NoticeVO noticeVO);
	List<NoticeVO> selectNoticeList();
	NoticeVO selectNoticeDetail(NoticeVO noticeVO);
	void correctNotice(NoticeVO noticeVO);
	
}
