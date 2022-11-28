package kh.study.team2.shop.qna.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.study.team2.shop.qna.service.QnaService;


@Controller
@RequestMapping("/qna")
public class QnaController {
	
	@Resource(name = "qnaService")
	QnaService qnaService;
	
	@RequestMapping("/list")
	public String list() {
		
//		//전체 데이터 수
//		int totalCnt = boardService.selectBoardCnt();
//		
//		//페이지 정보 세팅
//		boardVO.setTotalDataCnt(totalCnt);
//		boardVO.setPageInfo();//커맨드 객체로 데이터 자동으로 넘어감
		
		return "/content/qna/qna_list";
	}
	
	
}
