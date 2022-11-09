package kh.study.team2.shop.item.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kh.study.team2.shop.cate.service.CateService;
import kh.study.team2.shop.config.UploadFileUtil;
import kh.study.team2.shop.item.service.ItemService;
import kh.study.team2.shop.item.vo.ImgVO;
import kh.study.team2.shop.item.vo.ItemVO;
import kh.study.team2.shop.sell.service.SellService;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Resource(name="itemService")
	private ItemService itemService;
	@Resource(name = "cateService")
	private CateService cateService;
	@Resource(name = "sellService")
	private SellService sellService;
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("mainCateList",cateService.mainCateList());
		model.addAttribute("itemList",itemService.selectItemList());
		return "content/shop_main";
		
	}
	
	//판매하기 버튼 클릭 -> 상품등록 페이지로 이동
	@GetMapping("/regItemForm")
	public String regItemForm() {
		
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
		return "content/item/itemList";
	}
	
}
