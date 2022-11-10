package kh.study.team2.shop.board.controller;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.study.team2.shop.board.service.BoardService;
import kh.study.team2.shop.board.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Resource(name="boardService")
	private BoardService boardService;
	
	//글 작성 페이지 이동
	@GetMapping("/regMarketBoard")
	public String regBoard() {
		return "content/board/reg_market_board";
	}
	
	//글 작성
	@PostMapping("/regMarketBoard")
	public String regMarketBoard(BoardVO boardVO) {
		boardVO.setMemberId("test"); //임시
		boardVO.setItemCode("ITEM_001"); //임시
		boardService.insertBoard(boardVO);
		return "redirect:/admin/myMarket";
		//return "content/member/my_market";
	}
	
	//
}
