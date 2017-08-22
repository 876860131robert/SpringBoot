package com.robert.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.robert.common.Constants;
import com.robert.model.vo.MaterialsVO;
import com.robert.service.MaterialsDataService;

/**
 * 物料控制层
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping(value = "/materielsData")
public class MaterialsDataController {
	/**
	 * 日志
	 */
	private static final Logger              logger    = Logger.getLogger(MaterialsDataController.class);
	
@Autowired
private MaterialsDataService materielsDataService;

		/**
		 * 
		 * @param request
		 * @param response
		 */
		@RequestMapping("/list_page")
		public void findMaterielsDataPage(HttpServletRequest request, HttpServletResponse response) {
			response.setHeader("Content-type", "text/html;charset=UTF-8");
//			String page = request.getParameter("page");
//			String pcategory = Base64Util.decode(request.getParameter("pcategory"));
			String page="1";
			String pcategory = "rer";
			String brand = "re";
			String type = "re";
//			try {
//				if (!"".equals(Base64Util.decode(request.getParameter("brand")))
//						&& Base64Util.decode(request.getParameter("brand")) != null) {
//					brand = java.net.URLDecoder.decode(Base64Util.decode(request.getParameter("brand")), "utf-8");
//				}
//				if (!"".equals(Base64Util.decode(request.getParameter("materielstype")))
//						&& Base64Util.decode(request.getParameter("materielstype")) != null) {
//					type = java.net.URLDecoder.decode(Base64Util.decode(request.getParameter("materielstype")), "utf-8");
//				}
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
			int pageSize = 13;
			MaterialsVO materielsVO = new MaterialsVO();
			materielsVO.setBrand(brand);
			materielsVO.setMaterielstype(type);
			materielsVO.setPcategory(pcategory);
			int count = materielsDataService.findMaterielsDataTotal(materielsVO);
			int pageTimes;
			if (count % pageSize == 0) {
				pageTimes = count / pageSize;
			} else {
				pageTimes = count / pageSize + 1;
			}
			int startRow = (Integer.parseInt(page) - 1) * pageSize;
			materielsVO.setStartrow(startRow);
			materielsVO.setPagesize(pageSize);
			List<MaterialsVO> list = materielsDataService.findMaterielsDataPage(materielsVO);
			MaterialsVO materialsVO=new MaterialsVO();
//			for(int i=0;i<list.size();i++){
//				list.get(materialsVO.getBrand());
//			}
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("total", count);
			map.put("rows", list);
			map.put("pageTimes", pageTimes); // ��ҳ��
			map.put("countNum", count);// �鵽�����û���
			map.put("currentPage", Integer.parseInt(page));
				try {
					response.getWriter().write(JSON.toJSONString(map));
					logger.info("业务处理成功，向前台返回值!");
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		/**
		 * 根据id删除物料信息
		 * 
		 * @param idBase
		 * @param request
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/delMaterielsDataById/{idBase}")
		public String delMaterielsDataById(@PathVariable("idBase") String idBase, HttpServletRequest request)
				throws Exception {
//			String ids=Base64Util.decode(java.net.URLDecoder.decode(idBase,"UTF-8"));//�ȶԲ�������URLDecoderȻ�����Base64����
			String [] array= idBase.split(",");
			String pcategory = "GE";
			Map<String, String> map = new HashMap<String, String>();
			try {
				for (int i = 0; i < array.length; i++) {
					materielsDataService.delMaterielsDataById(array[i]);
				}
				map.put("msg", "删除成功！");
				map.put("result", Constants.success);
				map.put("pcategory", pcategory);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("msg", "删除失败！");
				map.put("result", Constants.fail);
			}

			String mapJson = JSON.toJSONString(map);
			return mapJson;
		}
		@RequestMapping("/login")
       public String login(HttpServletRequest request, HttpServletResponse response){
			String userName=request.getParameter("userName");
			String passWord=request.getParameter("passWord");
			Map<String, String> map = new HashMap<String, String>();
			if(userName=="123" && passWord=="123"){
				map.put("msg", "登录成功！");
				map.put("result", Constants.success);
			}else{
				map.put("msg", "删除失败！");
				map.put("result", Constants.fail);
			}
			String mapJson = JSON.toJSONString(map);
			return mapJson;
	
     } 

}
