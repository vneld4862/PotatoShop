package kh.study.team2.shop.config;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import kh.study.team2.shop.board.vo.ReviewImgVO;

public class UploadFileUtil2 {
	
	//파일이 첨부될 경로

	//예진 경로
//	private static final String UPLOAD_PATH = "D:\\workspaceSTS\\cloneSpringTest\\PotatoShop\\src\\main\\resources\\static\\images\\";
	//연지 경로
	private static final String UPLOAD_PATH = "D:\\workspaceSTS\\project\\PotatoShop\\src\\main\\resources\\static\\images\\";
//	private static final String UPLOAD_PATH = "D:\\dev\\workspaceSTS\\project\\PotatoShop\\src\\main\\resources\\static\\images\\";
	//명현 경로
//	private static final String UPLOAD_PATH = "C:\\workspace\\PotatoShop\\src\\main\\resources\\static\\images\\";
	//민하 경로
//	private static final String UPLOAD_PATH = "C:\\Users\\202-30\\git\\PotatoShop\\src\\main\\resources\\static\\images\\";
	
	
	//파일 첨부
	public static ReviewImgVO uploadFile(MultipartFile reviewImg) {
		String originFileName = null;
		String fileName = null;
		
		//실제 첨부 파일이 있을 때만 첨부 기능 실행
		if(!reviewImg.isEmpty()) { //파일명이 비어 있지 않다면
			//첨부하려는 원본 파일명
			originFileName = reviewImg.getOriginalFilename();

			//파일명 중복을 막기 위해 랜덤한 파일명을 문자열로 생성
			String uuid = UUID.randomUUID().toString();
			
			//확장자 추출
			String extension = originFileName.substring(originFileName.lastIndexOf("."));
			
			//첨부될 파일명 생성
			fileName = uuid + extension;
			
			try {
				//파일 객체 생성  File file = new File("파일경로 + 파일명");
				File file = new File(UPLOAD_PATH + fileName);

				reviewImg.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//리턴해야 하는 데이터를 저장하기 위한 객체
		ReviewImgVO reviewImgVO = new ReviewImgVO();
		reviewImgVO.setSavedName(fileName);
		reviewImgVO.setOriginName(originFileName);
		
		return reviewImgVO;
		
	}

		
	
}
