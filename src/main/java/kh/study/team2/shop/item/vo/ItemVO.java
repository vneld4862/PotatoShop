package kh.study.team2.shop.item.vo;


import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ItemVO extends PageVO{
	private String itemCode;
	@NotBlank(message = "상품명은 필수입력입니다")
	private String itemName;
	@Positive
	private int itemPrice;
	private String regDate;
	private String itemComment;
	@NotBlank(message = "상품상태를 선택해주세요")
	private String itemStatus;
	@NotBlank(message = "거래방법을 선택해주세요")
	private String tradeType;
	@NotBlank(message = "주소창은 필수입력입니다")
	private String tradeAddr;
	private int viewCnt;
	@NotBlank(message = "최소 한개 이상의 카테고리를 등록해주십시오")
	private String mainCateCode;
	private String subCateCode;
	private String detailCateCode;
	private String memberId;
	private String isWritten;
	private String salesStatus;
	private String revisionDate;
	private int totalSales;
	private int SALES_CNT;
	private List<ImgVO> imgList;
	private int cntWishList;
	private List<String> itemCodeList;
   
	private String searchKeyword;
}
