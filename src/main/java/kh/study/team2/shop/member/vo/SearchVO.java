package kh.study.team2.shop.member.vo;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@ToString
@Getter
@Setter
public class SearchVO {
	private String searchKeyword;
	private String searchValue;
}
