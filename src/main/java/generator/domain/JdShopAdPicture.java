package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 图片复制相关的实体
 * @TableName jd_shop_ad_picture
 */
@TableName(value ="jd_shop_ad_picture")
@Data
public class JdShopAdPicture implements Serializable {
    /**
     * id主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 图片名称
     */
    private String pictureName;

    /**
     * 创意名称
     */
    private String adName;

    /**
     * 图片url
     */
    private String imageUrl;

    /**
     * sku的id
     */
    private Long skuId;

    /**
     * 标题
     */
    private String title;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新时间
     */
    private Date updateDate;

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
        JdShopAdPicture other = (JdShopAdPicture) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPictureName() == null ? other.getPictureName() == null : this.getPictureName().equals(other.getPictureName()))
            && (this.getAdName() == null ? other.getAdName() == null : this.getAdName().equals(other.getAdName()))
            && (this.getImageUrl() == null ? other.getImageUrl() == null : this.getImageUrl().equals(other.getImageUrl()))
            && (this.getSkuId() == null ? other.getSkuId() == null : this.getSkuId().equals(other.getSkuId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPictureName() == null) ? 0 : getPictureName().hashCode());
        result = prime * result + ((getAdName() == null) ? 0 : getAdName().hashCode());
        result = prime * result + ((getImageUrl() == null) ? 0 : getImageUrl().hashCode());
        result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
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
        sb.append(", pictureName=").append(pictureName);
        sb.append(", adName=").append(adName);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", skuId=").append(skuId);
        sb.append(", title=").append(title);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", createDate=").append(createDate);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}