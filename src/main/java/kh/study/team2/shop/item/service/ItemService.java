package kh.study.team2.shop.item.service;

import java.util.List;

import kh.study.team2.shop.item.vo.ItemVO;

public interface ItemService {
	void insertItem(ItemVO itemVO);
	String getNextItemCode();
	List<ItemVO> selectItemList(ItemVO itemVO);
	ItemVO selectItemDetail(String itemCode);
	List<ItemVO> memberItemList(String memberId);
	void updateItem(ItemVO itemVO);
	void deleteItem(ItemVO itemVO);
//	void deleteItem(String itemCode);
	int selectItemCnt(ItemVO itemVO);
	List<ItemVO> searchItemName(ItemVO itemVO);
	List<ItemVO> searchCateName(ItemVO itemVO);
	int searchNameCnt(ItemVO itemVO);
	int searchCateCnt(ItemVO itemVO);
}
