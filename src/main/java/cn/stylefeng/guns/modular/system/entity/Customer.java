package cn.stylefeng.guns.modular.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 通知表
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-07
 */
@TableName("t_customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "CUSTOMER_ID", type = IdType.ID_WORKER)
    private Long customerId;
    /**
     * 公司名称
     */
    @TableField("NAME")
    private String name;
    /**
     * 联系人
     */
    @TableField("CONTACT")
    private String contact;
    /**
     * 联系方式
     */
    @TableField("CONTACT_TEL")
    private String contactTel;
    /**
     * 联系方式
     */
    @TableField("CONTACT_TEL2")
    private String contactTel2;
    /**
     * 联系方式
     */
    @TableField("CONTACT_TEL3")
    private String contactTel3;
    /**
     * 客户基本情况
     */
    @TableField("CONTENT")
    private String content;

    /**
     * 跟进状态(字典) 电话一试、二次沟通、约出见面
     */
    @TableField("STATUS")
    private String status;

    //办理的项目49种
    /**
     * 跟进情况
     */
    @TableField("FOLLOW_CONTENT")
    private String followContent;
    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 创建人
     */
    @TableField(value = "CREATE_USER", fill = FieldFill.INSERT)
    private Long createUser;
    /**
     * 修改时间
     */
    @TableField(value = "UPDATE_TIME", fill = FieldFill.UPDATE)
    private Date updateTime;
    /**
     * 修改人
     */
    @TableField(value = "UPDATE_USER", fill = FieldFill.UPDATE)
    private Long updateUser;

    @TableField(value = "VALID", fill = FieldFill.INSERT)
    private Integer valid;


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getContactTel2() {
        return contactTel2;
    }

    public void setContactTel2(String contactTel2) {
        this.contactTel2 = contactTel2;
    }

    public String getContactTel3() {
        return contactTel3;
    }

    public void setContactTel3(String contactTel3) {
        this.contactTel3 = contactTel3;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFollowContent() {
        return followContent;
    }

    public void setFollowContent(String followContent) {
        this.followContent = followContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", contactTel='" + contactTel + '\'' +
                ", contactTel2='" + contactTel2 + '\'' +
                ", contactTel3='" + contactTel3 + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", followContent='" + followContent + '\'' +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                ", updateTime=" + updateTime +
                ", updateUser=" + updateUser +
                ", valid=" + valid +
                '}';
    }
}
