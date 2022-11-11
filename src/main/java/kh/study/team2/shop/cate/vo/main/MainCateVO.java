package kh.study.team2.shop.cate.vo.main;

import java.util.List;

import groovy.transform.ToString;
import kh.study.team2.shop.cate.vo.sub.SubCateVO;
import lombok.Getter;
import lombok.Setter;

@ToString
@Getter
@Setter
public class MainCateVO {
	private String mainCateCode;
	private String mainCateName;
	private String cateStatus;
}
