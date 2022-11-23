package kh.study.team2.shop.cate.vo.detail;

import java.util.List;

import groovy.transform.ToString;
import kh.study.team2.shop.cate.vo.sub.SubCateVO;
import lombok.Getter;
import lombok.Setter;

@ToString
@Getter
@Setter
public class DetailCateVO {
	private String detailCateCode;
	private String detailCateName;
	private String subCateCode;
	private SubCateVO subCateVO;
	List<String> detailCateCodeList;
}
