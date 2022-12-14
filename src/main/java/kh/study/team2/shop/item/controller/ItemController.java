package kh.study.team2.shop.item.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kh.study.team2.shop.board.service.BoardService;
import kh.study.team2.shop.board.vo.BoardVO;
import kh.study.team2.shop.cate.service.CateService;
import kh.study.team2.shop.cate.vo.detail.DetailCateVO;
import kh.study.team2.shop.cate.vo.main.MainCateVO;
import kh.study.team2.shop.cate.vo.sub.SubCateVO;
import kh.study.team2.shop.config.UploadFileUtil;
import kh.study.team2.shop.item.service.ItemService;
import kh.study.team2.shop.item.vo.ImgVO;
import kh.study.team2.shop.item.vo.ItemVO;
import kh.study.team2.shop.manage.service.ManageService;
import kh.study.team2.shop.manage.vo.ProfileVO;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import kh.study.team2.shop.member.service.MemberService;
import kh.study.team2.shop.member.vo.MemberVO;
import kh.study.team2.shop.wish.service.WishService;
import kh.study.team2.shop.wish.vo.WishVO;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Resource(name="itemService")
	private ItemService itemService;
	
	@Resource(name = "cateService")
	private CateService cateService;
	
	@Resource(name = "wishService")
	private WishService wishService;
	
	@Resource(name="memberService")
	private MemberService memberService;

	@Resource(name="boardService")
	private BoardService boardService;
	
	@Resource(name="manageService")
	private ManageService manageService;
	
	
	@GetMapping("/list")
	public String list(Model model
					, MainCateVO mainCateVO
					, SubCateVO subCateVO
					, DetailCateVO detailCateVO
					, @CookieValue(required = false)String imgName
					, ItemVO itemVO
					, @RequestParam(name = "mainCode",required = false) String mainCode
					, @RequestParam(name = "subCode",required = false) String subCode
					, @RequestParam(name = "detailCode",required = false) String detailCode
					, Authentication authentication
					, HttpSession session) {
		
		
		if(mainCode != null)
		{
			itemVO.setMainCateCode(mainCode);
		}
		else if (subCode !=null) {
			itemVO.setSubCateCode(subCode);
		}
		else if (detailCode !=null) {
			itemVO.setDetailCateCode(detailCode);
		}
		int totalCnt=itemService.selectItemCnt(itemVO);
		itemVO.setDisplayCnt(20);
		itemVO.setTotalDataCnt(totalCnt);
		itemVO.setPageInfo();
		model.addAttribute("mainCateList",cateService.mainCateList());
		model.addAttribute("subCateList",cateService.subCateList(subCateVO));
		model.addAttribute("detailCateList",cateService.detailCateList(detailCateVO));
		
		model.addAttribute("itemList",itemService.selectItemList(itemVO));
		if(imgName !=null)
		{
			model.addAttribute("cookiechk",imgName);
			String[] cookieArr=imgName.split(",");
			List<String> cookieList=Arrays.asList(cookieArr);
			List<ImgVO> cookieImg=itemService.cookieItemList(cookieList);
			model.addAttribute("cookie_imgName",cookieImg); 
			
		}
		if (authentication!=null) {
			User user=(User)authentication.getPrincipal();
			model.addAttribute("wishAmount",wishService.wishAmount(user.getUsername()));
			
			//???????????? ??? top??? ????????? ????????? ??????
			MemberVO memberVO = memberService.selectMemberInfo(user.getUsername());
			session.setAttribute("nick", memberVO.getMemberNickName());
			
		}
		List<String> rankerList=memberService.memberRank();
		model.addAttribute("bestSalerItems",itemService.bestFourSalersItem(rankerList));
		return "content/shop_main";
	}
	
	
	
	
	//???????????? ?????? ??????
	@PostMapping("/regItem")
	public String regItem(ItemVO itemVO
							, BindingResult bindingResult
							, Model model
							, MultipartFile mainImg
							, @RequestParam(required = false)List<MultipartFile> subImgs
							, Authentication authentication) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("mainCateList",cateService.mainCateList());
			if(itemVO.getMainCateCode()!=null)
			{
				model.addAttribute("mainChk",itemVO.getMainCateCode());
			}
			model.addAttribute("typeChk",itemVO.getTradeType());
			model.addAttribute("statusChk",itemVO.getItemStatus());
			return "content/manage/reg_item";
		}
		String nextItemCode = itemService.getNextItemCode();
		
		ImgVO uploadInfo = UploadFileUtil.uploadFile(mainImg);
		uploadInfo.setItemCode(nextItemCode);
		List<ImgVO> uploadList = UploadFileUtil.multiUploadFile(subImgs);
		for(ImgVO img : uploadList) {
			img.setItemCode(nextItemCode);
		}
		
		uploadList.add(uploadInfo);
		itemVO.setImgList(uploadList);
		itemVO.setItemCode(nextItemCode);
		
		//insert?????? ??????
		System.out.println(itemVO);
		User user = (User)authentication.getPrincipal();
		String memberId = user.getUsername();
		itemVO.setMemberId(memberId);
		itemService.insertItem(itemVO);
		
		return "redirect:/item/checkRegItem";
	}
	
	//???????????? ??????(alert)??? ?????? ????????? ??????
	@GetMapping("/checkRegItem")
	public String checkRegItem() {
		
		return "content/item/check_reg_item";
	}
	
	//??????????????? ?????????
	@GetMapping("/memberItemList")
	public String memberItemList(Authentication authentication
								, ItemVO itemVO
								, Model model) {
		
		User user = (User)authentication.getPrincipal();
		String memberId = user.getUsername();
		itemVO.setMemberId(memberId);
	    List<ItemVO> itemList = itemService.memberItemList(itemVO);
		model.addAttribute("itemList", itemList);
		System.out.println(itemList);
		
		List<WishVO> wishList = wishService.selectWishList(memberId);
		System.out.println(wishList + "@@@@@@@@@@@@");
		model.addAttribute("wishList", wishList);
		model.addAttribute("profileInfo", memberService.profileInfo(memberId));
		
		return"content/item/item_list";
	}
	
	
	//?????????????????? ???????????? ??????
	@GetMapping("/itemDetail")
	public String itemDetail(String itemCode
							, ItemVO itemVO
							, Model model
							, Authentication authentication
							, HttpServletResponse response
							, @CookieValue(required = false,name = "imgName")String cookieImgName
							, @CookieValue(required = false,name = "cookieUser")String cookieUser) {
		
		System.out.println("****@@@@@@"+ itemVO);
		User user = (User)authentication.getPrincipal();
		String memberId = user.getUsername();
		System.out.println("****@@@@@@"+ memberId);
		System.out.println(itemCode);
		ItemVO itemInfo = itemService.selectItemDetail(itemCode);
		System.out.println(itemInfo);
		
		String imgName="";
		for(ImgVO img:itemInfo.getImgList())
		{
			if(img.getIsMain().equals("Y"))
			{
				 imgName=img.getAttachedName();
			}
		}
		
		//????????? java.net			"+" -> "\\+"
		//???????????? ???????????? ????????? + ????????? ??????
		String encodeImgName=null;
		
		if(cookieImgName != null)
		{
			encodeImgName=getEncodeStr(cookieImgName+","+imgName);
		}
		else
		{
			encodeImgName=getEncodeStr(imgName);
		}
		Cookie cookie_imgName=new Cookie("imgName",encodeImgName);
		boolean check=true;
		
		if(cookieImgName !=null)
		{
			String[] chkArr=cookieImgName.split(",");
			for(String chk:chkArr)
			{
				if(chk.equals(imgName))
				{
					check=false;
				}
			}
		}
		if(check)
		{
			response.addCookie(cookie_imgName);
		}
		model.addAttribute("itemInfo", itemInfo);
		model.addAttribute("memberId", memberId);
		model.addAttribute("wishAmount",wishService.itemDetailWish(itemCode));
		itemVO.setMemberId(memberId);
		itemVO.setItemCode(itemCode);
		
		itemService.updateViewCnt(itemCode);
				
		String wishCode =  wishService.selectWishCode(itemVO);
		model.addAttribute("wishCode", wishCode);
		
		ProfileVO profileInfo = memberService.detailProfile(itemCode);
		model.addAttribute("profileInfo", profileInfo);
		//?????? ????????? ???????????? ??????
		model.addAttribute("itemList", itemService.marketItemList(itemCode));
		//?????? ????????? ????????? ????????????
		List<BoardVO> reviewList = boardService.selectmarketReviewList(itemCode);
		System.out.println(reviewList);
		model.addAttribute("reviewList", reviewList);
		//????????? memberId
		String marketMemId = profileInfo.getMemberId();
		
		model.addAttribute("reviewCnt", boardService.selectReviewCnt(marketMemId));
		return"content/item/item_detail";
	}
	
	
	
	@GetMapping("/searchResult")
	public String searchResult(Model model,ItemVO itemVO)
	{
		itemVO.setSearchKeyword(itemVO.getSearchKeyword().replaceAll("\\s", "%"));
		itemVO.setDisplayCnt(10);
		int nameCnt=itemService.searchNameCnt(itemVO);
		int cateCnt=itemService.searchCateCnt(itemVO);
		if(nameCnt>cateCnt)
		{
			itemVO.setTotalDataCnt(nameCnt);
		}
		else
		{
			itemVO.setTotalDataCnt(cateCnt);
		}
		itemVO.setPageInfo();
		List<ItemVO> itemNameList=itemService.searchItemName(itemVO);
		List<ItemVO> itemCateList=itemService.searchCateName(itemVO);
		
		model.addAttribute("searchKeyword",itemVO.getSearchKeyword());
		model.addAttribute("searchItemList",itemNameList);
		model.addAttribute("searchCateList",itemCateList);
		return "content/item/search_result.html";
	}
	
	
	public String getEncodeStr(String str)
	{
		String result="";
		try {
			result=URLEncoder.encode(str,"UTF-8").replaceAll("\\+","%20"); //???????????? ???????????? ?????????=%20
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//????????? ?????? ????????? ??????
	@GetMapping("/sellerMarket")
	public String sellerMarket(Model model
								, Authentication authentication
								, ItemVO itemVO
								, String memberId
								, String wishChk
								, String reviewChk) {
		System.out.println(reviewChk);
		model.addAttribute("reviewChk", reviewChk);
		
		if(wishChk!=null)
		{
			model.addAttribute("wishChk",wishChk);
		}

		//?????? ?????? ??????
		model.addAttribute("memberInfo", memberService.selectMemberInfo(memberId));
		
		//?????? ?????? ??????
		itemVO.setMemberId(memberId);
		List<ItemVO> itemList = itemService.memberItemList(itemVO);
		
	    model.addAttribute("itemList", itemList);
	    
		//?????? ?????? ?????? ??????
		model.addAttribute("boardList", boardService.selectBoardList(memberId));
		
		User user = (User)authentication.getPrincipal();
		
		//?????? ?????? ????????? ?????? ?????? ?????? ??????
		model.addAttribute("writtenReviewList", boardService.selectWrittenReviewList(user.getUsername()));
		
		//??? ?????? ??????
		List<WishVO> wishList = wishService.selectWishList(memberId);
		model.addAttribute("wishList", wishList);

		//????????? ?????? ??????
		model.addAttribute("profileInfo", memberService.profileInfo(memberId));
		model.addAttribute("viewCnt", manageService.selectShopViewCnt(memberId));

		return "content/item/seller_market";
	}
	
	
	
	
}
