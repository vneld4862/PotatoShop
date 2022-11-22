package kh.study.team2.shop.item.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import kh.study.team2.shop.cate.service.CateService;
import kh.study.team2.shop.cate.vo.detail.DetailCateVO;
import kh.study.team2.shop.cate.vo.main.MainCateVO;
import kh.study.team2.shop.cate.vo.sub.SubCateVO;
import kh.study.team2.shop.config.UploadFileUtil;
import kh.study.team2.shop.item.service.ItemService;
import kh.study.team2.shop.item.vo.ImgVO;
import kh.study.team2.shop.item.vo.ItemVO;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import kh.study.team2.shop.member.service.MemberService;
import kh.study.team2.shop.sell.service.SellService;
import kh.study.team2.shop.wish.service.WishService;
import kh.study.team2.shop.wish.vo.WishVO;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Resource(name="itemService")
	private ItemService itemService;
	
	@Resource(name = "cateService")
	private CateService cateService;
	
	@Resource(name = "sellService")
	private SellService sellService;
	
	@Resource(name = "wishService")
	private WishService wishService;
	
	@Resource(name="memberService")
	private MemberService memberService;
	
	
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
					, Authentication authentication) {
		
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
			
			model.addAttribute("cookie_imgName",cookieList);
		}
		if (authentication!=null) {
			User user=(User)authentication.getPrincipal();
			System.out.println("@@@@@@@@@@@@@@@@@@@@"+user.getUsername());
		}
//		model.addAttribute("wishAmount",wishService.wishAmount(user.getUsername()));
		return "content/shop_main";
	}
	
	
	
	
	//상품등록 버튼 클릭 -> 메인화면으로 이동(상품관리페이지 이동으로 변경예정)
	@PostMapping("/regItem")
	public String regItem(ItemVO itemVO
						  , MultipartFile mainImg
						  , List<MultipartFile> subImgs
						  , Authentication authentication) {
		
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
		
		//insert쿼리 실행
		System.out.println(itemVO);
		User user = (User)authentication.getPrincipal();
		String memberId = user.getUsername();
		itemVO.setMemberId(memberId);
		itemService.insertItem(itemVO);
		
		return "redirect:/manage/itemManage";
	}
	
	
	//상품리스트 테스트
	@GetMapping("/memberItemList")
	public String memberItemList(Authentication authentication
								, Model model) {
		
		User user = (User)authentication.getPrincipal();
		String memberId = user.getUsername();
	    List<ItemVO> itemList = itemService.memberItemList(memberId);
		model.addAttribute("itemList", itemList);
		System.out.println(itemList);
		
		List<WishVO> wishList = wishService.selectWishList(memberId);
		System.out.println(wishList);
		model.addAttribute("wishList", wishList);
		model.addAttribute("profileInfo", memberService.profileInfo(memberId));
		
		return"content/item/item_list";
	}
	
	
	//상품상세보기 페이지로 이동
	@GetMapping("/itemDetail")
	public String itemDetail(String itemCode
							, ItemVO itemVO
							, String cookieChk
							, Model model
							, Authentication authentication
							, HttpServletResponse response
							, @CookieValue(required = false,name = "imgName")String cookieImgName) {
		
		User user = (User)authentication.getPrincipal();
		String memberId = user.getUsername();
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
		
		//인코딩 java.net			"+" -> "\\+"
		//인코딩만 진행하면 공백이 + 문자로 바뀜
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
		if(cookieChk !=null)
		{
			String[] chkArr=cookieChk.split(",");
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
		
		String wishCode =  wishService.selectWishCode(itemVO);
		model.addAttribute("wishCode", wishCode);
		
		return"content/item/item_detail";
	}
	
	
	public String getEncodeStr(String str)
	{
		String result="";
		try {
			result=URLEncoder.encode(str,"UTF-8").replaceAll("\\+","%20"); //컴퓨터가 생각하는 빈공백=%20
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	
}
