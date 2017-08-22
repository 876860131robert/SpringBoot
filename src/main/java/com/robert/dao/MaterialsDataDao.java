package com.robert.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.robert.model.vo.MaterialsVO;

/**
 * 文件映射类
 * 
 * @author Administrator
 *
 */
@Mapper
public interface MaterialsDataDao {

	/**
	 * 查询出物料信息
	 * 
	 * @param materielsVO
	 * @return
	 */
	public List<MaterialsVO> findMaterielsDataPage(@Param("materielsVO") MaterialsVO materielsVO);

	/**
	 * 查询出物料的总数量
	 * 
	 * @param materielsVO
	 * @return
	 */
	public int findMaterielsDataTotal(@Param("materielsVO") MaterialsVO materielsVO);
	
	/**
	 * 根据id删除物料信息
	 * 
	 * @param id
	 */
	public void delMaterielsDataById(@Param("id") String id);

}
