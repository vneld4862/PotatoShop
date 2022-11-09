package kh.study.team2.shop.board.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.study.team2.shop.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Resource(name="boardService")
	private BoardService boardService;

	@RequestMapping("/regMarketBoard")
	public String regMarketBoard() {
		
		return "content/board/reg_market_board";
	}
}
