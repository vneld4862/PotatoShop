package kh.study.team2.shop.notice.service;

import java.util.List;

import kh.study.team2.shop.notice.vo.NoticeVO;

public interface NoticeService {

	void insertNotice(NoticeVO noticeVO);
	List<NoticeVO> selectNoticeList(NoticeVO noticeVO);
	NoticeVO selectNoticeDetail(int noticeNum);
	void correctNotice(NoticeVO noticeVO);
	void deleteNotice(int noticeNum);
	//게시글 총 개수 조회
	int selectNoticeCnt();
	int updateReadCnt(int noticeNum);
	
}
