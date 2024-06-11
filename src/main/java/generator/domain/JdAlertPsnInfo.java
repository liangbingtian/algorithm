package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName jd_alert_psn_info
 */
@TableName(value ="jd_alert_psn_info")
@Data
public class JdAlertPsnInfo implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 公司编码
     */
    private String company;

    /**
     * 组织名称
     */
    private String organizationName;

    /**
     * 人员名称
     */
    private String psnName;

    /**
     * 统一认证账号
     */
    private String account;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 职位名称
     */
    private String jobName;

    /**
     * 所属部门编码
     */
    private String deptCode;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 所属事业部编码
     */
    private String businessUnitCode;

    /**
     * 所属事业部名称
     */
    private String businessUnitName;

    /**
     * 微信id
     */
    private String weChart;

    /**
     * 邮箱信息
     */
    private String email;

    /**
     * 启用状态:1删除2启用3禁用
     */
    private String enablestate;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateBy;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        JdAlertPsnInfo other = (JdAlertPsnInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompany() == null ? other.getCompany() == null : this.getCompany().equals(other.getCompany()))
            && (this.getOrganizationName() == null ? other.getOrganizationName() == null : this.getOrganizationName().equals(other.getOrganizationName()))
            && (this.getPsnName() == null ? other.getPsnName() == null : this.getPsnName().equals(other.getPsnName()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getPostName() == null ? other.getPostName() == null : this.getPostName().equals(other.getPostName()))
            && (this.getJobName() == null ? other.getJobName() == null : this.getJobName().equals(other.getJobName()))
            && (this.getDeptCode() == null ? other.getDeptCode() == null : this.getDeptCode().equals(other.getDeptCode()))
            && (this.getDeptName() == null ? other.getDeptName() == null : this.getDeptName().equals(other.getDeptName()))
            && (this.getBusinessUnitCode() == null ? other.getBusinessUnitCode() == null : this.getBusinessUnitCode().equals(other.getBusinessUnitCode()))
            && (this.getBusinessUnitName() == null ? other.getBusinessUnitName() == null : this.getBusinessUnitName().equals(other.getBusinessUnitName()))
            && (this.getWeChart() == null ? other.getWeChart() == null : this.getWeChart().equals(other.getWeChart()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getEnablestate() == null ? other.getEnablestate() == null : this.getEnablestate().equals(other.getEnablestate()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompany() == null) ? 0 : getCompany().hashCode());
        result = prime * result + ((getOrganizationName() == null) ? 0 : getOrganizationName().hashCode());
        result = prime * result + ((getPsnName() == null) ? 0 : getPsnName().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getPostName() == null) ? 0 : getPostName().hashCode());
        result = prime * result + ((getJobName() == null) ? 0 : getJobName().hashCode());
        result = prime * result + ((getDeptCode() == null) ? 0 : getDeptCode().hashCode());
        result = prime * result + ((getDeptName() == null) ? 0 : getDeptName().hashCode());
        result = prime * result + ((getBusinessUnitCode() == null) ? 0 : getBusinessUnitCode().hashCode());
        result = prime * result + ((getBusinessUnitName() == null) ? 0 : getBusinessUnitName().hashCode());
        result = prime * result + ((getWeChart() == null) ? 0 : getWeChart().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getEnablestate() == null) ? 0 : getEnablestate().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", company=").append(company);
        sb.append(", organizationName=").append(organizationName);
        sb.append(", psnName=").append(psnName);
        sb.append(", account=").append(account);
        sb.append(", postName=").append(postName);
        sb.append(", jobName=").append(jobName);
        sb.append(", deptCode=").append(deptCode);
        sb.append(", deptName=").append(deptName);
        sb.append(", businessUnitCode=").append(businessUnitCode);
        sb.append(", businessUnitName=").append(businessUnitName);
        sb.append(", weChart=").append(weChart);
        sb.append(", email=").append(email);
        sb.append(", enablestate=").append(enablestate);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}