package com.ziyoujiayuan.data.sql.mybaties.mapper.auto.usermanage;

import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.PrivilegeInfoBean;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage.PrivilegeInfoBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PrivilegeInfoBeanMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_privilege_info
     *
     * @mbg.generated Sun Dec 17 11:54:31 CST 2017
     */
    long countByExample(PrivilegeInfoBeanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_privilege_info
     *
     * @mbg.generated Sun Dec 17 11:54:31 CST 2017
     */
    int deleteByExample(PrivilegeInfoBeanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_privilege_info
     *
     * @mbg.generated Sun Dec 17 11:54:31 CST 2017
     */
    int deleteByPrimaryKey(Long privilegeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_privilege_info
     *
     * @mbg.generated Sun Dec 17 11:54:31 CST 2017
     */
    int insert(PrivilegeInfoBean record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_privilege_info
     *
     * @mbg.generated Sun Dec 17 11:54:31 CST 2017
     */
    int insertSelective(PrivilegeInfoBean record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_privilege_info
     *
     * @mbg.generated Sun Dec 17 11:54:31 CST 2017
     */
    List<PrivilegeInfoBean> selectByExample(PrivilegeInfoBeanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_privilege_info
     *
     * @mbg.generated Sun Dec 17 11:54:31 CST 2017
     */
    PrivilegeInfoBean selectByPrimaryKey(Long privilegeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_privilege_info
     *
     * @mbg.generated Sun Dec 17 11:54:31 CST 2017
     */
    int updateByExampleSelective(@Param("record") PrivilegeInfoBean record, @Param("example") PrivilegeInfoBeanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_privilege_info
     *
     * @mbg.generated Sun Dec 17 11:54:31 CST 2017
     */
    int updateByExample(@Param("record") PrivilegeInfoBean record, @Param("example") PrivilegeInfoBeanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_privilege_info
     *
     * @mbg.generated Sun Dec 17 11:54:31 CST 2017
     */
    int updateByPrimaryKeySelective(PrivilegeInfoBean record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_privilege_info
     *
     * @mbg.generated Sun Dec 17 11:54:31 CST 2017
     */
    int updateByPrimaryKey(PrivilegeInfoBean record);
}