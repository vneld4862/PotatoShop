package kh.study.team2.shop.board.controller;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	
	//후기 작성
	@PostMapping("/regReview")
	public String regReview(BoardVO boardVO
							, @RequestParam(required = false) ReviewImgVO reviewImgVO
							, @RequestParam(required = false) MultipartFile reviewImg
							, Authentication authentication) {
		
		ReviewImgVO uploadInfo = UploadFileUtil2.uploadFile(reviewImg);
		
		uploadInfo.setItemCode("ITEM_002"); //임시
		
		boardVO.setItemCode("ITEM_002"); //임시

		User user = (User)authentication.getPrincipal();
		boardVO.setMemberId(user.getUsername());
		
		boardVO.setReviewImgVO(uploadInfo);		
		boardService.insertReview(boardVO, uploadInfo);
		
		return "redirect:/manage/myMarket";
	}
	
	//후기 상세 보기
	@ResponseBody
	@PostMapping("/reviewDetail")
	public BoardVO reviewDetail(Model model, String itemCode) {
		
		return boardService.selectBoardDetail(itemCode); //리턴 값을 ajax의 result로 전달
	}
	
	//후기 삭제
	@ResponseBody
	@PostMapping("/deleteReview")
	public void deleteReview(String itemCode) {
		
		boardService.deleteReview(itemCode);
	}
	
}
