package kh.study.team2.shop.item.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.team2.shop.item.vo.ItemVO;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void insertItem(ItemVO itemVO) {
		sqlSession.insert("itemMapper.insertItem", itemVO);
		sqlSession.insert("itemMapper.insertImgs", itemVO);
	}

	@Override
	public String getNextItemCode() {
	 return	sqlSession.selectOne("itemMapper.getNextItemCode");
		
	}

	@Override
	public List<ItemVO> selectItemList(ItemVO itemVO) {
		return sqlSession.selectList("itemMapper.selectItemList",itemVO);
	}

	@Override
	public ItemVO selectItemDetail(String itemCode) {
		return sqlSession.selectOne("itemMapper.selectItemDetail", itemCode);
	}

	@Override
	public List<ItemVO> memberItemList(ItemVO itemVO) {
		return sqlSession.selectList("itemMapper.memberItemList", itemVO);
	}

	@Override
	public void updateItem(ItemVO itemVO) {
		
		/*
		 * if(itemVO.getImgList()) {
		 * 
		 * 
		 * }
		 */
		
		if(itemVO.getImgList().size() != 0) {
			sqlSession.insert("itemMapper.insertImgs", itemVO);//조건
		}
	    sqlSession.update("itemMapper.updateItem", itemVO);
	}

	@Override
	public void deleteItem(ItemVO itemVO) {
		sqlSession.delete("itemMapper.deleteItem", itemVO);
	}

	@Override
	public int selectItemCnt(ItemVO itemVO) 
	{
		return sqlSession.selectOne("itemMapper.selectItemCnt",itemVO);
	}

	@Override
	public List<ItemVO> searchItemName(ItemVO itemVO) {
		return sqlSession.selectList("itemMapper.searchItemName",itemVO);
	}
	@Override
	public List<ItemVO> searchCateName(ItemVO itemVO) {
		return sqlSession.selectList("itemMapper.searchCateName",itemVO);
	}

	@Override
	public int searchNameCnt(ItemVO itemVO) {
		return sqlSession.selectOne("itemMapper.searchNameCnt",itemVO);
	}
	@Override
	public int searchCateCnt(ItemVO itemVO) {
		return sqlSession.selectOne("itemMapper.searchCateCnt",itemVO);
	}
	@Override
	public void updateViewCnt(String itemCode) {
		sqlSession.update("itemMapper.updateViewCnt",itemCode);
	}

	@Override
	public List<ItemVO> bestFourSalersItem(List<String> memberList) {
		return sqlSession.selectList("itemMapper.bestSalersItem",memberList);
	}

	@Override
	public List<ItemVO> marketItemList(String itemCode) {
		return sqlSession.selectList("itemMapper.marketItemList", itemCode);
	}
}
