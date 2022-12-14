package kh.study.team2.shop.manage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kh.study.team2.shop.board.service.BoardService;
import kh.study.team2.shop.board.vo.BoardVO;
import kh.study.team2.shop.buy.service.BuyService;
import kh.study.team2.shop.buy.vo.BuyVO;
import kh.study.team2.shop.cate.service.CateService;
import kh.study.team2.shop.cate.vo.detail.DetailCateVO;
import kh.study.team2.shop.cate.vo.main.MainCateVO;
import kh.study.team2.shop.cate.vo.sub.SubCateVO;
import kh.study.team2.shop.config.UploadFileUtil;
import kh.study.team2.shop.config.UploadFileUtil_profile;
import kh.study.team2.shop.item.service.ItemService;
import kh.study.team2.shop.item.vo.ImgVO;
import kh.study.team2.shop.item.vo.ItemVO;
import kh.study.team2.shop.manage.service.ManageService;
import kh.study.team2.shop.manage.vo.ProfileVO;
import kh.study.team2.shop.member.service.MemberService;
import kh.study.team2.shop.member.vo.MemberVO;
import kh.study.team2.shop.wish.service.WishService;
import kh.study.team2.shop.wish.vo.WishVO;


@Controller
@RequestMapping("/manage")
public class ManageController {
	@Resource(name="manageService")
	private ManageService manageService;
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	@Resource(name="itemService")
	private ItemService itemService;

	@Resource(name="cateService")
	private CateService cateService;
	
	@Resource(name="memberService")
	private MemberService memberService;
	
	@Resource(name="buyService")
	private BuyService buyService;
	
	@Resource(name="wishService")
	private WishService wishService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	//모든 메소드가 실행되기 전에 무조건 실행되는 메소드
	@ModelAttribute
	public void test(@RequestParam(defaultValue = "1") String menu, Model model) {
		model.addAttribute("menu", menu);
	}
	
	//내 상점 페이지 이동
	@GetMapping("/myMarket")
	public String myMarket(Model model
						  , Authentication authentication
						  , ItemVO itemVO
						  , BoardVO boardVO
						  , String wishChk) {
		User user = (User)authentication.getPrincipal();
		if(wishChk!=null)
		{
			model.addAttribute("wishChk",wishChk);
		}
		//회원 정보 조회
		model.addAttribute("memberInfo", memberService.selectMemberInfo(user.getUsername()));
				
		//상품목록 조회
		itemVO.setMemberId(user.getUsername());
		List<ItemVO> itemList = itemService.memberItemList(itemVO);
	    model.addAttribute("itemList", itemList);
		
		//내 상점 후기 목록 조회
		model.addAttribute("boardList", boardService.selectBoardList(user.getUsername()));
		
		//내가 다른 상점에 남긴 후기 목록 조회
		model.addAttribute("writtenReviewList", boardService.selectWrittenReviewList(user.getUsername()));
		
		//찜 목록 조회
		List<WishVO> wishList = wishService.selectWishList(user.getUsername());
		model.addAttribute("wishList", wishList);

		//프로필정보 조회
		model.addAttribute("profileInfo", memberService.profileInfo(user.getUsername()));
		model.addAttribute("viewCnt",manageService.selectShopViewCnt(user.getUsername()));
		//내가 쓴 리뷰 총 개수
		//int writtenReviewTotalCnt = manageService.selectWrittenReviewCnt(user.getUsername());
		//내 상점에 쓰여진 리뷰 총 개수
		//int myMarketReviewTotalCnt = manageService.selectMyMarketReviewCnt(user.getUsername());
		
		//페이지 정보 세팅
		//boardVO.setTotalDataCnt(writtenReviewTotalCnt);
		//boardVO.setPageInfo();

		return "content/manage/my_market"; 
	}	
	
	//내 정보
	@GetMapping("/myInfo")
	public String myInfo(Model model, MemberVO memberVO) {
		MemberVO memberInfo = memberService.selectMyInfo(memberVO);
		model.addAttribute("myInfo", memberInfo);
		return"content/manage/my_info";
	}
	
	//내 정보 수정
	@PostMapping("/updateMyInfo")
	public String updateMyInfo(MemberVO memberVO) {
		//비밀번호 암호화
//		String pw = encoder.encode(memberVO.getMemberPw()); //memberVO 안에서 input으로 입력받은 비밀번호를 가지고 와서 암호화 후 이름을 pw로 지정
//		memberVO.setMemberPw(pw); //암호화한 값 pw를 memberVO의 비밀번호로 세팅
		
		memberService.updateMyInfo(memberVO);
		return "redirect:/manage/myMarket";
	}
	
	//프로필 정보 수정
	@PostMapping("/updateProfile")
	public String updateProfile(MemberVO memberVO
								, @RequestParam(required = false)MultipartFile profileImg) {
		System.out.println(profileImg);
		if(!profileImg.getOriginalFilename().equals("")) {
			String memberId = memberVO.getMemberId();
			ProfileVO uploadInfo = UploadFileUtil_profile.uploadFile(profileImg);
			uploadInfo.setMemberId(memberId);
			
			manageService.updateProfileImg(uploadInfo);
			
		}
		manageService.updateNickName(memberVO);
		
		return "redirect:/manage/myMarket";
	}
	
	//찜 선택삭제
	@PostMapping("/deleteWish")
	public String deleteWish(String wishCodes, WishVO wishVO) {
		
		String[] wishCodeArr = wishCodes.split(","); 
		List<String> wishCodeList = java.util.Arrays.asList(wishCodeArr);
		wishVO.setWishCodeList(wishCodeList);
		wishService.deleteWish(wishVO);
		return"redirect:/manage/myMarket";
	}

	
	//상품등록 페이지로 이동
	@GetMapping("/regItemForm")
	public String regItemForm(ItemVO itemVO,Model model) {
		List<MainCateVO> mainCateList = cateService.mainCateList();
		System.out.println(mainCateList);
		model.addAttribute("mainCateList", mainCateList);
		return "content/manage/reg_item";
	}
	
	
	//상품관리 페이지 이동
	@RequestMapping("/itemManage")
	public String itemManage(Authentication authentication
							, Model model
							, ItemVO itemVO
							, @RequestParam(defaultValue = "2") String menu) {
		model.addAttribute("menu", menu);
		
		User user = (User)authentication.getPrincipal();
		String memberId = user.getUsername();
		
		itemVO.setMemberId(memberId);
		int totalCnt = manageService.selectManageItemCnt(itemVO);
		itemVO.setDisplayCnt(5);
		itemVO.setTotalDataCnt(totalCnt);
		itemVO.setPageInfo();
		
		List<ItemVO> itemList = manageService.selectManageitemList(itemVO);
		model.addAttribute("itemList", itemList);
		
		
		
		
		return "content/manage/item_manage";
	}
	
	//상품 수정 화면으로 이동
	@GetMapping("/updateForm")
	public String updateForm(String itemCode
							 , SubCateVO subCateVO
							 , DetailCateVO detailCateVO
							 , Model model
							 , @RequestParam(defaultValue = "2") String menu) {
		ItemVO itemInfo = itemService.selectItemDetail(itemCode);
		
		model.addAttribute("itemInfo", itemInfo);
		//카테고리 목록조회
		model.addAttribute("mainCateList",cateService.mainCateList());
//		subCateVO.setSubCateCode(itemInfo.getSubCateCode());
//		model.addAttribute("mainCateList",cateService.subCateList(subCateVO));
//		detailCateVO.setDetailCateCode(itemInfo.getDetailCateCode());
//		model.addAttribute("mainCateList",cateService.detailCateList(detailCateVO));
		
		//서브이미지 개수
//		int subImgCnt = 0;
//		for(ImgVO e : itemInfo.getImgList()) {
//			if(e.getIsMain().equals("N")) {
//				subImgCnt++;
//			}
//		}
//		System.out.println("@@@@@@@@" + subImgCnt);
//		model.addAttribute("subImgCnt", subImgCnt);
		
		model.addAttribute("menu", menu);
		return "content/manage/update_item";
	}
	
	//상품수정 페이지 - 이미지 삭제
		@ResponseBody
		@PostMapping("/deleteImg")
		public ItemVO deleteImg(String imgCode, String itemCode) {
			manageService.deleteImg(imgCode);
		    ItemVO itemInfo = itemService.selectItemDetail(itemCode);
		    
		    return itemInfo;
		}
		
	
	
	//상품 수정
	@PostMapping("/updateItem")
	public String updateItem(ItemVO itemVO
							, BindingResult bindingResult
							, Model model
							, @RequestParam(required = false)MultipartFile mainImg
							, @RequestParam(required = false)List<MultipartFile> subImgs
							, String imgCode) {
		System.out.println(itemVO);
		System.out.println(imgCode);
		System.out.println("@@@@@@@"+subImgs.get(0));
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("mainCateList",cateService.mainCateList());
			if(itemVO.getMainCateCode()!=null)
			{
				model.addAttribute("mainChk",itemVO.getMainCateCode());
			}
			model.addAttribute("typeChk",itemVO.getTradeType());
			model.addAttribute("statusChk",itemVO.getItemStatus());
			return "content/manage/update_item";
		}
		
		
		
		List<ImgVO> uploadList2 = new ArrayList<>();
		
		if(subImgs != null) {
			List<ImgVO> uploadList = UploadFileUtil.multiUploadFile(subImgs);
			for(ImgVO img : uploadList) {
				img.setItemCode(itemVO.getItemCode());
			}
			uploadList2.addAll(uploadList);
		}
		
		if(!mainImg.getOriginalFilename().equals("")) {
			manageService.deleteImg(imgCode);
			ImgVO uploadInfo = UploadFileUtil.uploadFile(mainImg);
			uploadInfo.setItemCode(itemVO.getItemCode());
			uploadList2.add(uploadInfo);
		}
		
		
		itemVO.setImgList(uploadList2);
		itemVO.setItemCode(itemVO.getItemCode());
		System.out.println(itemVO);
		
		itemService.updateItem(itemVO);
		
		return"redirect:/manage/itemManage";
	}
	
	//상품 (선택)삭제
	@RequestMapping("/deleteItem")
	public String deleteItem(String itemCodes, ItemVO itemVO) {
		
		if(itemCodes != null) {
			String[] itemCodeArr = itemCodes.split(","); 
			List<String> itemCodeList = java.util.Arrays.asList(itemCodeArr);
			itemVO.setItemCodeList(itemCodeList);
		}
		
		else {
			String itemCode = itemVO.getItemCode();
			List<String> itemCodeList = new ArrayList<>();
			itemCodeList.add(itemCode);
			itemVO.setItemCodeList(itemCodeList);
		}
		itemService.deleteItem(itemVO);
		return"redirect:/manage/itemManage";
	}
	
	//판매상태 변경
	@ResponseBody
	@PostMapping("/salesStatus")
	public void salesStatus(ItemVO itemVO) {
		System.out.println(itemVO);
		System.out.println(itemVO.getItemCode());
		manageService.salesStatus(itemVO);
	}
	
	
	//구매 내역 페이지 이동
	@GetMapping("/buyList")
	public String buyList(Model model, Authentication authentication, BuyVO buyVO) {
		
		User user = (User)authentication.getPrincipal();
		
		
		String buyer = user.getUsername();
		
		//전체 데이터 수
		int totalCnt = manageService.selectBuyCnt(buyer);
		
		//페이지 정보 세팅
		buyVO.setTotalDataCnt(totalCnt);
		buyVO.setPageInfo();
		
		buyVO.setBuyer(user.getUsername());
		
		//구매 내역 조회
		model.addAttribute("buyList", manageService.selectBuyList(buyVO));

		return "content/manage/buy_list";
	}
	
	//판매 내역 페이지 이동
	@GetMapping("/salesList")
	public String sellList(Model model, Authentication authentication, BuyVO buyVO) {
		
		User user = (User)authentication.getPrincipal();
		

		String seller = user.getUsername();
		
		//전체 데이터 수
		int totalCnt = manageService.selectSalesCnt(seller);
		
		//페이지 정보 세팅
		buyVO.setTotalDataCnt(totalCnt);
		buyVO.setPageInfo();
		
		buyVO.setSeller(user.getUsername());
		
		//판매 내역 조회
		model.addAttribute("salesList", manageService.selectSalesList(buyVO));
		
		return "content/manage/sales_list";
	}
}
