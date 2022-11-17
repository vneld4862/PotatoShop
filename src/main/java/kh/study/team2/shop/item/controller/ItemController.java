package kh.study.team2.shop.item.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kh.study.team2.shop.cate.service.CateService;
import kh.study.team2.shop.cate.vo.detail.DetailCateVO;
import kh.study.team2.shop.cate.vo.sub.SubCateVO;
import kh.study.team2.shop.config.UploadFileUtil;
import kh.study.team2.shop.item.service.ItemService;
import kh.study.team2.shop.item.vo.ImgVO;
import kh.study.team2.shop.item.vo.ItemVO;
import kh.study.team2.shop.manage.vo.ProfileVO;
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
					, SubCateVO subCateVO
					, DetailCateVO detailCateVO) {
		
		model.addAttribute("mainCateList",cateService.mainCateList());
		model.addAttribute("subCateList",cateService.subCateList(subCateVO));
		model.addAttribute("detailCateList",cateService.detailCateList(detailCateVO));
		model.addAttribute("itemList",itemService.selectItemList());
		
		
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
	public String memberItemList(Authentication authentication, Model model) {
		
		User user = (User)authentication.getPrincipal();
		String memberId = user.getUsername();
	    List<ItemVO> itemList = itemService.memberItemList(memberId);
		model.addAttribute("itemList", itemList);
		System.out.println(itemList);
		
		List<WishVO> wishList = wishService.selectWishList(memberId);
		System.out.println(wishList);
		model.addAttribute("wishList", wishList);
		
		ProfileVO profileInfo = memberService.profileInfo(memberId);
		model.addAttribute("profileInfo", profileInfo);
		
		return"content/item/item_list";
	}
	
	
	//상품상세보기 페이지로 이동
	@GetMapping("/itemDetail")
	public String itemDetail(String itemCode
							, Model model
							, Authentication authentication) {
		
		User user = (User)authentication.getPrincipal();
		String memberId = user.getUsername();
		System.out.println(itemCode);
		ItemVO itemInfo = itemService.selectItemDetail(itemCode);
		System.out.println(itemInfo);
		model.addAttribute("itemInfo", itemInfo);
		model.addAttribute("memberId", memberId);
		
		return"content/item/item_detail";
	}
	
	
	
	
	
	
	
}
