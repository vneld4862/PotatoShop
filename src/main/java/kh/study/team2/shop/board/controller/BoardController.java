package kh.study.team2.shop.board.controller;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kh.study.team2.shop.board.service.BoardService;
import kh.study.team2.shop.board.vo.BoardVO;
import kh.study.team2.shop.board.vo.ReplyVO;
import kh.study.team2.shop.board.vo.ReviewImgVO;
import kh.study.team2.shop.config.UploadFileUtil_review_img;
import kh.study.team2.shop.item.service.ItemService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Resource(name="boardService")
	private BoardService boardService;
	@Resource(name="itemService")
	private ItemService itemService;
	
	
	//리뷰 작성
	@PostMapping("/regReview")
	public String regReview(BoardVO boardVO
							, @RequestParam(required = false) ReviewImgVO reviewImgVO
							, @RequestParam(required = false) MultipartFile reviewImg
							, Authentication authentication
							, String seller) {
		
		ReviewImgVO uploadInfo = UploadFileUtil_review_img.uploadFile(reviewImg);
		
		uploadInfo.setItemCode(boardVO.getItemCode());
		
		boardVO.setItemCode(boardVO.getItemCode());
		
		User user = (User)authentication.getPrincipal();
		boardVO.setBuyer(user.getUsername());
		boardVO.setSeller(seller);
		
		boardVO.setReviewImgVO(uploadInfo);		
		boardService.insertReview(boardVO, uploadInfo);
		boardService.ifWittenReview(boardVO);
		
		return "content/board/reg_review_result";
	}
		
	//리뷰 상세 보기
	@ResponseBody
	@RequestMapping("/reviewDetail")
	public BoardVO reviewDetail(String itemCode) {
		
		return boardService.selectBoardDetail(itemCode); //리턴 값을 ajax의 result로 전달
	}
	
	//리뷰 삭제
	@ResponseBody
	@PostMapping("/deleteReview")
	public void deleteReview(String itemCode) {
		boardService.deleteReview(itemCode);
		boardService.updateReviewBefore(itemCode);
	}
	

	//리뷰 수정
	@PostMapping("/updateReview")
	public String updateReview(BoardVO boardVO
								, @RequestParam(required = false) MultipartFile reviewImg) {
		
		//select 쿼리 먼저 실행
		ReviewImgVO review = boardService.selectReviewImg(boardVO.getItemCode());
		
		//리뷰에 첨부된 사진이 없었다면
		if(review == null) {
			//수정한 리뷰에 첨부 파일이 있다면
			if(!reviewImg.getOriginalFilename().equals("")) {
				String itemCode = boardVO.getItemCode();
				ReviewImgVO uploadInfo = UploadFileUtil_review_img.uploadFile(reviewImg);
				uploadInfo.setItemCode(itemCode);
				
				boardVO.setReviewImgVO(uploadInfo);
				boardService.insertReviewImg(boardVO);
			}
		}
		
		//리뷰에 첨부된 사진이 있었다면
		else {
			//수정한 리뷰에 첨부 파일이 있다면
			if(!reviewImg.getOriginalFilename().equals("")) {
				String itemCode = boardVO.getItemCode();
				ReviewImgVO uploadInfo = UploadFileUtil_review_img.uploadFile(reviewImg);
				uploadInfo.setItemCode(itemCode);
				
				boardService.updateReviewImg(uploadInfo);
			}
		}
		
		boardService.updateReview(boardVO);
		
		return "content/board/update_review_result";
	}
	
	
	//리뷰 댓글 작성
	@ResponseBody
	@PostMapping("/regReplyAjax")
	public ReplyVO regReplyAjax(ReplyVO replyVO, Authentication authentication) {
		
		User user = (User)authentication.getPrincipal();
		replyVO.setMemberId(user.getUsername());
		
 		int replyNum = boardService.getNextReplyNum();
		replyVO.setReplyNum(replyNum);

		boardService.insertReviewReply(replyVO);

		ReplyVO resultData = new ReplyVO();
		resultData.setMemberId(user.getUsername());
		resultData.setReplyContent(replyVO.getReplyContent());
		resultData.setReplyRegDate(getNowDateTime());
		resultData.setReplyNum(replyNum);
		return resultData;
	}
	
	//리뷰 댓글 조회
	@ResponseBody
	@PostMapping("/selectReviewReply")
	public List<ReplyVO> selectReviewReply(int boardNum){
		
		return boardService.selectReviewReply(boardNum);
	}
	
	//리뷰 댓글 삭제
	@ResponseBody
	@PostMapping("/deleteReply")
	public void deleteReply(int replyNum) {
		
		boardService.deleteReply(replyNum);
	}	
	
	
	
	
	
	
	
	
	
	
	

	
	//현재 날짜 및 시간 데이터 리턴
	public String getNowDateTime() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		
		String monthStr = "" + month;
		//1~9월이면 앞에 '0'을 붙여줌
		if(month / 10 == 0) {
			monthStr = "0" + month;
		}
		
		int date = cal.get(Calendar.DATE);
		
		String dateStr = "" + date;
		//1~9일이면 앞에 '0'을 붙여줌
		if(date / 10 == 0) {
			dateStr = "0" + date;
		}
		
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		String hourStr = "" + hour;
		//1~9일이면 앞에 '0'을 붙여줌
		if(hour / 10 == 0) {
			hourStr = "0" + hour;
		}
		
		int minute = cal.get(Calendar.MINUTE);
		String minuteStr = "" + minute;
		//1~9일이면 앞에 '0'을 붙여줌
		if(minute / 10 == 0) {
			minuteStr = "0" + minute;
		}
		
		return year + "-" + monthStr + "-" + dateStr + " " + hourStr + ":" + minuteStr;
	}

	

}
