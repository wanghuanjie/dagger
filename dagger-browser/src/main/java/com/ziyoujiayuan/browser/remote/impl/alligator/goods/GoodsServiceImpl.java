package com.ziyoujiayuan.browser.remote.impl.alligator.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ziyoujiayuan.data.sql.mybaties.entity.auto.GoodsDetailBean;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.GoodsDetailBeanExample;
import com.ziyoujiayuan.data.sql.mybaties.mapper.auto.GoodsDetailBeanMapper;

/**
 * 商品详情实现
 * @Author wanghjbuf
 * @Date 2017年9月22日
 */
@Service
public class GoodsServiceImpl {

	@Autowired
	GoodsDetailBeanMapper goodsDetailBeanMapper;
	
	public GoodsDetailBean getGoodsDetail(String goods_id) {
		GoodsDetailBean resultBean;
		if ("".equals(goods_id) || null == goods_id) {
			GoodsDetailBeanExample goodsDetailBeanExample = new GoodsDetailBeanExample();
			goodsDetailBeanExample.createCriteria();
			List<GoodsDetailBean> list = goodsDetailBeanMapper.selectByExample(goodsDetailBeanExample);

			resultBean = list.size()>0?list.get(0):new GoodsDetailBean();
		} else {
			resultBean = goodsDetailBeanMapper.selectByPrimaryKey(goods_id);
		}
		return resultBean;
	}
}
