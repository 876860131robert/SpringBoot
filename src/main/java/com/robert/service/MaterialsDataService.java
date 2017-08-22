package com.robert.service;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.robert.dao.MaterialsDataDao;
import com.robert.model.MaterialsModel;
import com.robert.model.vo.MaterialsVO;


@Service
@Transactional
public class MaterialsDataService {

	@Autowired
	private MaterialsDataDao materielsDataDao;

	public int findMaterielsDataTotal(MaterialsVO materielsVO) {

		return materielsDataDao.findMaterielsDataTotal(materielsVO);
	}
	
	
	public List<MaterialsVO> findMaterielsDataPage(MaterialsVO materielsVO){
		return materielsDataDao.findMaterielsDataPage(materielsVO);
		
	}


	public MaterialsModel findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean findMaterielsInfoByType(String materielstype) {
		// TODO Auto-generated method stub
		return false;
	}


	public void updateMateriels(MaterialsModel materielsModel) {
		// TODO Auto-generated method stub
		
	}


	public void addMaterielsData(MaterialsModel materielsModel) {
		// TODO Auto-generated method stub
		
	}


	public List<MaterialsModel> findMaterielsInfoByPCategory(String pcategory) {
		// TODO Auto-generated method stub
		return null;
	}


	public void delMaterielsDataById(String id) {
		materielsDataDao.delMaterielsDataById(id);
		
	}
	
	
	
	
	

}
