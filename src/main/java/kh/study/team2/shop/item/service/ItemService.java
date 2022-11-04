package kh.study.team2.shop.item.service;

import kh.study.team2.shop.item.vo.ItemVO;

public interface ItemService {
	void insertItem(ItemVO itemVO);
	String getNextItemCode();
}
