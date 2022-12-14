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
	
	
	//?????? ???????????? ???????????? ?????? ????????? ???????????? ?????????
	@ModelAttribute
	public void test(@RequestParam(defaultValue = "1") String menu, Model model) {
		model.addAttribute("menu", menu);
	}
	
	//??? ?????? ????????? ??????
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
		//?????? ?????? ??????
		model.addAttribute("memberInfo", memberService.selectMemberInfo(user.getUsername()));
				
		//???????????? ??????
		itemVO.setMemberId(user.getUsername());
		List<ItemVO> itemList = itemService.memberItemList(itemVO);
	    model.addAttribute("itemList", itemList);
		
		//??? ?????? ?????? ?????? ??????
		model.addAttribute("boardList", boardService.selectBoardList(user.getUsername()));
		
		//?????? ?????? ????????? ?????? ?????? ?????? ??????
		model.addAttribute("writtenReviewList", boardService.selectWrittenReviewList(user.getUsername()));
		
		//??? ?????? ??????
		List<WishVO> wishList = wishService.selectWishList(user.getUsername());
		model.addAttribute("wishList", wishList);

		//??????????????? ??????
		model.addAttribute("profileInfo", memberService.profileInfo(user.getUsername()));
		model.addAttribute("viewCnt",manageService.selectShopViewCnt(user.getUsername()));
		//?????? ??? ?????? ??? ??????
		//int writtenReviewTotalCnt = manageService.selectWrittenReviewCnt(user.getUsername());
		//??? ????????? ????????? ?????? ??? ??????
		//int myMarketReviewTotalCnt = manageService.selectMyMarketReviewCnt(user.getUsername());
		
		//????????? ?????? ??????
		//boardVO.setTotalDataCnt(writtenReviewTotalCnt);
		//boardVO.setPageInfo();

		return "content/manage/my_market"; 
	}	
	
	//??? ??????
	@GetMapping("/myInfo")
	public String myInfo(Model model, MemberVO memberVO) {
		MemberVO memberInfo = memberService.selectMyInfo(memberVO);
		model.addAttribute("myInfo", memberInfo);
		return"content/manage/my_info";
	}
	
	//??? ?????? ??????
	@PostMapping("/updateMyInfo")
	public String updateMyInfo(MemberVO memberVO) {
		//???????????? ?????????
//		String pw = encoder.encode(memberVO.getMemberPw()); //memberVO ????????? input?????? ???????????? ??????????????? ????????? ?????? ????????? ??? ????????? pw??? ??????
//		memberVO.setMemberPw(pw); //???????????? ??? pw??? memberVO??? ??????????????? ??????
		
		memberService.updateMyInfo(memberVO);
		return "redirect:/manage/myMarket";
	}
	
	//????????? ?????? ??????
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
	
	//??? ????????????
	@PostMapping("/deleteWish")
	public String deleteWish(String wishCodes, WishVO wishVO) {
		
		String[] wishCodeArr = wishCodes.split(","); 
		List<String> wishCodeList = java.util.Arrays.asList(wishCodeArr);
		wishVO.setWishCodeList(wishCodeList);
		wishService.deleteWish(wishVO);
		return"redirect:/manage/myMarket";
	}

	
	//???????????? ???????????? ??????
	@GetMapping("/regItemForm")
	public String regItemForm(ItemVO itemVO,Model model) {
		List<MainCateVO> mainCateList = cateService.mainCateList();
		System.out.println(mainCateList);
		model.addAttribute("mainCateList", mainCateList);
		return "content/manage/reg_item";
	}
	
	
	//???????????? ????????? ??????
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
	
	//?????? ?????? ???????????? ??????
	@GetMapping("/updateForm")
	public String updateForm(String itemCode
							 , SubCateVO subCateVO
							 , DetailCateVO detailCateVO
							 , Model model
							 , @RequestParam(defaultValue = "2") String menu) {
		ItemVO itemInfo = itemService.selectItemDetail(itemCode);
		
		model.addAttribute("itemInfo", itemInfo);
		//???????????? ????????????
		model.addAttribute("mainCateList",cateService.mainCateList());
//		subCateVO.setSubCateCode(itemInfo.getSubCateCode());
//		model.addAttribute("mainCateList",cateService.subCateList(subCateVO));
//		detailCateVO.setDetailCateCode(itemInfo.getDetailCateCode());
//		model.addAttribute("mainCateList",cateService.detailCateList(detailCateVO));
		
		//??????????????? ??????
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
	
	//???????????? ????????? - ????????? ??????
		@ResponseBody
		@PostMapping("/deleteImg")
		public ItemVO deleteImg(String imgCode, String itemCode) {
			manageService.deleteImg(imgCode);
		    ItemVO itemInfo = itemService.selectItemDetail(itemCode);
		    
		    return itemInfo;
		}
		
	
	
	//?????? ??????
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
	
	//?????? (??????)??????
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
	
	//???????????? ??????
	@ResponseBody
	@PostMapping("/salesStatus")
	public void salesStatus(ItemVO itemVO) {
		System.out.println(itemVO);
		System.out.println(itemVO.getItemCode());
		manageService.salesStatus(itemVO);
	}
	
	
	//?????? ?????? ????????? ??????
	@GetMapping("/buyList")
	public String buyList(Model model, Authentication authentication, BuyVO buyVO) {
		
		User user = (User)authentication.getPrincipal();
		
		
		String buyer = user.getUsername();
		
		//?????? ????????? ???
		int totalCnt = manageService.selectBuyCnt(buyer);
		
		//????????? ?????? ??????
		buyVO.setTotalDataCnt(totalCnt);
		buyVO.setPageInfo();
		
		buyVO.setBuyer(user.getUsername());
		
		//?????? ?????? ??????
		model.addAttribute("buyList", manageService.selectBuyList(buyVO));

		return "content/manage/buy_list";
	}
	
	//?????? ?????? ????????? ??????
	@GetMapping("/salesList")
	public String sellList(Model model, Authentication authentication, BuyVO buyVO) {
		
		User user = (User)authentication.getPrincipal();
		

		String seller = user.getUsername();
		
		//?????? ????????? ???
		int totalCnt = manageService.selectSalesCnt(seller);
		
		//????????? ?????? ??????
		buyVO.setTotalDataCnt(totalCnt);
		buyVO.setPageInfo();
		
		buyVO.setSeller(user.getUsername());
		
		//?????? ?????? ??????
		model.addAttribute("salesList", manageService.selectSalesList(buyVO));
		
		return "content/manage/sales_list";
	}
}
