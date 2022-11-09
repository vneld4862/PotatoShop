package kh.study.team2.shop.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import kh.study.team2.shop.item.vo.ImgVO;



public class UploadFileUtil {
	
	//파일이 첨부될 경로
//	private static final String UPLOAD_PATH = "D:\\\\workspaceSTS\\\\Shop1011\\\\src\\\\main\\\\resources\\\\static\\\\images\\\\";
	private static final String UPLOAD_PATH = "D:\\workspaceSTS\\cloneSpringTest\\PotatoShop\\src\\main\\resources\\static\\images\\";
	  //변수에 final이 붙어있으면 값을 변경할 수 없다... 다시 물어보자. 파일변경할수 없도록 상수로 만들었다
	  //상수로 만들면 모두 대문자로 만든다. 
	
	
	//파일 첨부   메소드명앞에 static을 붙이면 클래스.하면 메소드에 바로 접근할 수 있다.
	public static ImgVO uploadFile(MultipartFile mainImg) {
		String fileName = null; //초기화 왜 해줘야한다구...?
		String originFileName = null;
        //실제 첨부파일이 있을 때만 첨부기능 실행 (앞에서 첨부파일이 넘어와야 첨부기능을 하는 코드 실행하도록)
		if(!mainImg.isEmpty()) {
			
			//첨부하려는 원본 파일명
			originFileName = mainImg.getOriginalFilename();
			
			
			
			//파일명 중복을 막기 위해 랜덤한 파일명을 문자열로 생성
			String uuid = UUID.randomUUID().toString(); //랜덤한 문자생성
			//확장자 추출
			String extension = originFileName.substring(originFileName.lastIndexOf("."));
			//String extension = originFileName.substring(3);   //"apple.jpg"    .jpg 이런 확장자만 가져오겠다.
			
			//첨부될 파일명 생성 (중복되지 않는 걸로)
			fileName = uuid + extension; //"adfsdsf" + ".jpg" 이런 형태로 중복되지 않도록 랜덤한 파일명생성.
			 //String fileName = uuid + extension; //위에서 선언해줬으니 중복선언할수 없잖아. 
			 
			
			//originFileName.substring(originFileName.indexOf(".")); // .을 기준으로 문자를 잘라준다.
			originFileName.substring(originFileName.lastIndexOf(".")); //가장 마지막에 있는 매개변수를 알려준다. ex)abc.efg.jpg이런 문제를 해결하기 위해서
			
			
			try {
				//파일 객체 생성
				//File file = new File("파일경로 + 파일명"); 
				File file = new File(UPLOAD_PATH + fileName); 
				mainImg.transferTo(file);    //이미지업로드는 무조건 예외처리하라고 한다. 예외처리해준다.
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}
		
		
		//리턴해야하는 데이터를 저장하기 위한 객체
		ImgVO imgVO = new ImgVO();
		imgVO.setAttachedName(fileName);
		imgVO.setOriginName(originFileName);
		imgVO.setIsMain("Y");  //여러 데이터를 리턴시켜서 넘겨야 하니 객체에 담아서 넘겨준다.
		
		return imgVO; //리턴하는 것과 메소드 자료형을 리턴타입 맞춰주기
	}
	
	//다중 파일 첨부
	public static List<ImgVO> multiUploadFile(List<MultipartFile> subImgs) {
	
		List<ImgVO> list = new ArrayList<>();
		//첨부된 파일의 개수만큼 첨부를 시작
		for(MultipartFile subImg : subImgs) {
			
		  ImgVO vo = uploadFile(subImg);
		  vo.setIsMain("N"); //서브파일은 메인여부가 N으로 세팅된 후 데이터를 리턴시켜야하니
							   //'N'으로 먼저 세팅하고 데이터를 LIST에 담아준다.
		  list.add(vo);
			
			
			
		     //uploadFile(subImg); //위의 메소드 과정을 거쳐 만들어진 첨부파일정보를 리턴받았잖아.
			//imgVO를 리턴받은 것. 첨부될 파일명, 원본파일명, 메인여부 정보를 가진.
			//모든 정보를 가질 LIST를 만들어서 넣어주고 리턴시켜주면 된다.
			//list.add(uploadFile(subImg));
			
			
		}
		
		return list;
			
	}
		
	
}
