package kh.study.team2.shop.cate.vo.sub;


import java.util.List;

import groovy.transform.ToString;
import kh.study.team2.shop.cate.vo.main.MainCateVO;
import lombok.Getter;
import lombok.Setter;

@ToString
@Getter
@Setter
public class SubCateVO {
	private String subCateCode;
	private String subCateName;
	private String mainCateCode;
	private MainCateVO mainCateVO;
	List<String> subCateCodeList;
}
