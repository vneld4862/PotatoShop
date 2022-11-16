package kh.study.team2.shop.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import kh.study.team2.shop.item.vo.ImgVO;


public class UploadFileUtil {
	
	//파일이 첨부될 경로

	//예진 경로
//	private static final String UPLOAD_PATH = "D:\\workspaceSTS\\cloneSpringTest\\PotatoShop\\src\\main\\resources\\static\\images\\";
	//예진(노트북) 경로
//	private static final String UPLOAD_PATH = "C:\\dev\\workspaceSTS\\cloneSpringTest\\PotatoShop\\src\\main\\resources\\static\\images\\";
	//연지 경로
	private static final String UPLOAD_PATH = "D:\\workspaceSTS\\project\\PotatoShop\\src\\main\\resources\\static\\images\\";
	//명현 경로
//	private static final String UPLOAD_PATH = "C:\\workspace\\PotatoShop\\src\\main\\resources\\static\\images\\";
	//민하 경로
//	private static final String UPLOAD_PATH = "C:\\Users\\202-30\\git\\PotatoShop\\src\\main\\resources\\static\\images\\";
	
	
	public static ImgVO uploadFile(MultipartFile mainImg) {
		String fileName = null; 
		String originFileName = null;
		if(!mainImg.isEmpty()) {
			
			//첨부하려는 원본 파일명
			originFileName = mainImg.getOriginalFilename();
			
			String uuid = UUID.randomUUID().toString();
			//확장자 추출
			String extension = originFileName.substring(originFileName.lastIndexOf("."));
			fileName = uuid + extension; 
			originFileName.substring(originFileName.lastIndexOf("."));
			
			try {
				File file = new File(UPLOAD_PATH + fileName); 
				mainImg.transferTo(file);    
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}
		
		
		ImgVO imgVO = new ImgVO();
		imgVO.setAttachedName(fileName);
		imgVO.setOriginName(originFileName);
		imgVO.setIsMain("Y");  
		
		return imgVO; 
	}
	
	//다중 파일 첨부
	public static List<ImgVO> multiUploadFile(List<MultipartFile> subImgs) {
	
		List<ImgVO> list = new ArrayList<>();
		for(MultipartFile subImg : subImgs) {
			
		  ImgVO vo = uploadFile(subImg);
		  vo.setIsMain("N"); 
		  list.add(vo);
			
		}
		return list;
	}
		
	
}
