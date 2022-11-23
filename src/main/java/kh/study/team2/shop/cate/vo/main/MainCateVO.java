package kh.study.team2.shop.cate.vo.main;


import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MainCateVO {
	private String mainCateCode;
	private String mainCateName;
	private String cateStatus;
	private List<String> mainCateCodeList;
}
