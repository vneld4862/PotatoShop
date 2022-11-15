package kh.study.team2.shop.item.service;

import java.util.List;

import kh.study.team2.shop.item.vo.ItemVO;

public interface ItemService {
	void insertItem(ItemVO itemVO);
	String getNextItemCode();
	List<ItemVO> selectItemList();
	ItemVO selectItemDetail(String itemCode);
	List<ItemVO> memberItemList(String memberId);
//	ItemVO updateItem(ItemVO itemVO);
}
