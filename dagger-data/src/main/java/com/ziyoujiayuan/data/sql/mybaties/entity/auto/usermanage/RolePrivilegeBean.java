package com.ziyoujiayuan.data.sql.mybaties.entity.auto.usermanage;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class RolePrivilegeBean extends RolePrivilegeBeanKey  implements Serializable{
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role_privilege.creat_time
     *
     * @mbg.generated Fri Oct 20 11:49:38 CST 2017
     */
    private Date creatTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role_privilege.creator
     *
     * @mbg.generated Fri Oct 20 11:49:38 CST 2017
     */
    private String creator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role_privilege.oper_time
     *
     * @mbg.generated Fri Oct 20 11:49:38 CST 2017
     */
    private Date operTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role_privilege.opertor
     *
     * @mbg.generated Fri Oct 20 11:49:38 CST 2017
     */
    private String opertor;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role_privilege.creat_time
     *
     * @return the value of t_role_privilege.creat_time
     *
     * @mbg.generated Fri Oct 20 11:49:38 CST 2017
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role_privilege.creat_time
     *
     * @param creatTime the value for t_role_privilege.creat_time
     *
     * @mbg.generated Fri Oct 20 11:49:38 CST 2017
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role_privilege.creator
     *
     * @return the value of t_role_privilege.creator
     *
     * @mbg.generated Fri Oct 20 11:49:38 CST 2017
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role_privilege.creator
     *
     * @param creator the value for t_role_privilege.creator
     *
     * @mbg.generated Fri Oct 20 11:49:38 CST 2017
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role_privilege.oper_time
     *
     * @return the value of t_role_privilege.oper_time
     *
     * @mbg.generated Fri Oct 20 11:49:38 CST 2017
     */
    public Date getOperTime() {
        return operTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role_privilege.oper_time
     *
     * @param operTime the value for t_role_privilege.oper_time
     *
     * @mbg.generated Fri Oct 20 11:49:38 CST 2017
     */
    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role_privilege.opertor
     *
     * @return the value of t_role_privilege.opertor
     *
     * @mbg.generated Fri Oct 20 11:49:38 CST 2017
     */
    public String getOpertor() {
        return opertor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role_privilege.opertor
     *
     * @param opertor the value for t_role_privilege.opertor
     *
     * @mbg.generated Fri Oct 20 11:49:38 CST 2017
     */
    public void setOpertor(String opertor) {
        this.opertor = opertor == null ? null : opertor.trim();
    }
}