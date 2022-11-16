package kh.study.team2.shop.cate.vo;

import groovy.transform.ToString;
import kh.study.team2.shop.cate.vo.detail.DetailCateVO;
import kh.study.team2.shop.cate.vo.main.MainCateVO;
import kh.study.team2.shop.cate.vo.sub.SubCateVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class AllofCate {
	private MainCateVO mainCateVO;
	private SubCateVO subCateVO;
	private DetailCateVO detailCateVO;
}
