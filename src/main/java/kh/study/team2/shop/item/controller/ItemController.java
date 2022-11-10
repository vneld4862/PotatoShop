package kh.study.team2.shop.item.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import groovyjarjarantlr4.v4.parse.GrammarTreeVisitor.mode_return;
import kh.study.team2.shop.cate.service.CateService;
import kh.study.team2.shop.cate.vo.main.MainCateVO;
import kh.study.team2.shop.config.UploadFileUtil;
import kh.study.team2.shop.item.service.ItemService;
import kh.study.team2.shop.item.vo.ImgVO;
import kh.study.team2.shop.item.vo.ItemVO;
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
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("mainCateList",cateService.mainCateList());
		model.addAttribute("subCateList",cateService.subCateList());
		model.addAttribute("detailCateList",cateService.detailCateList());
		model.addAttribute("itemList",itemService.selectItemList());
		return "content/shop_main";
		
	}
	
	//판매하기 버튼 클릭 -> 상품등록 페이지로 이동
	@GetMapping("/regItemForm")
	public String regItemForm(Model model) {
		List<MainCateVO> mainCateList = cateService.mainCateList();
		System.out.println(mainCateList);
		model.addAttribute("mainCateList", mainCateList);
		return "content/item/regItem";
	}
	
	
	//상품등록 버튼 클릭 -> 메인화면으로 이동(상품관리페이지 이동으로 변경예정)
	@PostMapping("/regItem")
	public String regItem(ItemVO itemVO
						  , MultipartFile mainImg
						  , List<MultipartFile> subImgs) {
		
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
		itemService.insertItem(itemVO);
		return "redirect:/item/list";
	}
	
	
	//상품리스트 테스트
	@GetMapping("/memberItemList")
	public String memberItemList(Model model) {
	    List<ItemVO> itemList = itemService.selectItemList();
		model.addAttribute("itemList", itemList);
		System.out.println(itemList);
		return"content/item/itemList";
	}
	
	
	//상품상세보기 페이지로 이동
	@GetMapping("/itemDetail")
	public String itemDetail(String itemCode, Model model) {
		System.out.println(itemCode);
		ItemVO itemInfo = itemService.selectItemDetail(itemCode);
		System.out.println(itemInfo);
		model.addAttribute("itemInfo", itemInfo);
		return"content/item/item_detail";
	}
	
	//찜버튼 클릭 시 실행 
	@ResponseBody
	@PostMapping("/insertWish")
	public void insertWish(WishVO wishVO) {
		System.out.println("찜을 해보자^^");
		wishVO.setMemberId("test1"); //임시로 추가. 추후에 시큐리티에서 가져올 예정
		
		
		
		wishService.insertWish(wishVO);
	}
	
	
	
	
	
	
}
