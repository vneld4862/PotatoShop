package kh.study.team2.shop.item.service;

import java.util.List;

import kh.study.team2.shop.item.vo.ImgVO;
import kh.study.team2.shop.item.vo.ItemVO;

public interface ItemService {
	void insertItem(ItemVO itemVO);
	String getNextItemCode();
	List<ItemVO> selectItemList(ItemVO itemVO);
	//상점정보 - 상품목록 조회
    List<ItemVO> marketItemList(String itemCode);
	ItemVO selectItemDetail(String itemCode);
	List<ItemVO> memberItemList(ItemVO itemVO);
	void updateItem(ItemVO itemVO);
	void deleteItem(ItemVO itemVO);
//	void deleteItem(String itemCode);
	int selectItemCnt(ItemVO itemVO);
	List<ItemVO> searchItemName(ItemVO itemVO);
	List<ItemVO> searchCateName(ItemVO itemVO);
	int searchNameCnt(ItemVO itemVO);
	int searchCateCnt(ItemVO itemVO);
	void updateViewCnt(String itemCode);
	List<ItemVO> bestFourSalersItem(List<String> memberList);
	List<ImgVO> cookieItemList(List<String> attachedNameList);
}
