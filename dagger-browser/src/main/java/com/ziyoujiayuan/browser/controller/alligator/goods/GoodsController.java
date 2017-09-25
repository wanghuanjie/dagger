package com.ziyoujiayuan.browser.controller.alligator.goods;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ziyoujiayuan.browser.remote.impl.alligator.goods.GoodsServiceImpl;

/**
 * 商品详情接口
 * @Author wanghjbuf
 * @Date 2017年9月22日
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	GoodsServiceImpl goodsServiceImpl;
	
	/**
	 * 获取商品详情
	 * @param goods_id
	 * @return
	 */
	@RequestMapping("/details")
	@ResponseBody
	public Map<String, Object> details(String goods_id) {
	    Map<String, Object> map = new HashMap<String, Object>();
	    try {
		    map.put("data", goodsServiceImpl.getGoodsDetail(goods_id));
		    map.put("msg", "操作成功");
		    map.put("success", true);
		} catch (Exception e) {
		    map.put("msg", e.getMessage());
		    map.put("success", false);
		    
		    e.printStackTrace();
		}
	    
	    return map;
	}
}
