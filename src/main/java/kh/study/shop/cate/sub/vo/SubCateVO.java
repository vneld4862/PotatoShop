package kh.study.shop.cate.sub.vo;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@ToString
@Getter
@Setter
public class SubCateVO {
	private String subCateCode;
	private String subCateName;
	private String mainCateCode;
}
