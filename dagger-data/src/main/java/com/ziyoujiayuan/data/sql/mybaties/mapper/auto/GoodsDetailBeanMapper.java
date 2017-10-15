package com.ziyoujiayuan.data.sql.mybaties.mapper.auto;

import com.ziyoujiayuan.data.sql.mybaties.entity.auto.GoodsDetailBean;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.GoodsDetailBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsDetailBeanMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_detail
     *
     * @mbg.generated Mon Sep 25 16:21:09 CST 2017
     */
    long countByExample(GoodsDetailBeanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_detail
     *
     * @mbg.generated Mon Sep 25 16:21:09 CST 2017
     */
    int deleteByExample(GoodsDetailBeanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_detail
     *
     * @mbg.generated Mon Sep 25 16:21:09 CST 2017
     */
    int deleteByPrimaryKey(String goodsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_detail
     *
     * @mbg.generated Mon Sep 25 16:21:09 CST 2017
     */
    int insert(GoodsDetailBean record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_detail
     *
     * @mbg.generated Mon Sep 25 16:21:09 CST 2017
     */
    int insertSelective(GoodsDetailBean record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_detail
     *
     * @mbg.generated Mon Sep 25 16:21:09 CST 2017
     */
    List<GoodsDetailBean> selectByExample(GoodsDetailBeanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_detail
     *
     * @mbg.generated Mon Sep 25 16:21:09 CST 2017
     */
    GoodsDetailBean selectByPrimaryKey(String goodsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_detail
     *
     * @mbg.generated Mon Sep 25 16:21:09 CST 2017
     */
    int updateByExampleSelective(@Param("record") GoodsDetailBean record, @Param("example") GoodsDetailBeanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_detail
     *
     * @mbg.generated Mon Sep 25 16:21:09 CST 2017
     */
    int updateByExample(@Param("record") GoodsDetailBean record, @Param("example") GoodsDetailBeanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_detail
     *
     * @mbg.generated Mon Sep 25 16:21:09 CST 2017
     */
    int updateByPrimaryKeySelective(GoodsDetailBean record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_detail
     *
     * @mbg.generated Mon Sep 25 16:21:09 CST 2017
     */
    int updateByPrimaryKey(GoodsDetailBean record);
}