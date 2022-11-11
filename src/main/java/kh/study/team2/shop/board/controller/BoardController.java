package kh.study.team2.shop.board.controller;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kh.study.team2.shop.board.service.BoardService;
import kh.study.team2.shop.board.vo.BoardVO;
import kh.study.team2.shop.board.vo.ReviewImgVO;
import kh.study.team2.shop.config.UploadFileUtil2;
import kh.study.team2.shop.item.service.ItemService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Resource(name="boardService")
	private BoardService boardService;
	@Resource(name="itemService")
	private ItemService itemService;
	
	//후기 작성 페이지 이동
	@GetMapping("/regReview")
	public String regBoard() {
		return "content/board/reg_market_board";
	}
	
	//후기 작성
	@PostMapping("/regReview")
	public String regReview(BoardVO boardVO
							, ReviewImgVO reviewImgVO
							, MultipartFile reviewImg) {
		
		ReviewImgVO uploadInfo = UploadFileUtil2.uploadFile(reviewImg);
		uploadInfo.setItemCode("ITEM_001"); //임시
		
		boardVO.setItemCode("ITEM_001"); //임시

		//uploadInfo.setItemCode(boardVO.getItemCode());
		boardVO.setMemberId("test"); //임시
		
		boardVO.setReviewImgVO(uploadInfo);		
		boardService.insertReview(boardVO);
		
		return "redirect:/manage/myMarket";
	}
	
	//후기 상세 보기
	@GetMapping("/reviewDetail")
	public String reviewDetail(Model model, String itemCode) {
		model.addAttribute("board", boardService.selectBoardDetail(itemCode));
		
		return "/content/board/market_board_detail";
	}
	
}
