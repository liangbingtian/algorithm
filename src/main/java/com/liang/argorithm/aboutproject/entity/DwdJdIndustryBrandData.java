package com.liang.argorithm.aboutproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName dwd_jd_industry_brand_data
 */
@TableName(value ="dwd_jd_industry_brand_data")
@Data
public class DwdJdIndustryBrandData implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * yyyy-MM-dd当前时间
     */
    private String dateStr;

    /**
     * 品牌类型，竞争品牌或自身品牌
     */
    private String brandType;

    /**
     * 行业渠道
     */
    private String business;

    /**
     * 三级类目id
     */
    private Integer cid3;

    /**
     * 转换周期
     */
    private Integer transDays;

    /**
     * 口径，点击或下单
     */
    private Integer caliber;

    /**
     * 订单状态，0全部订单，1下单订单
     */
    private Integer orderStatus;

    /**
     * 
     */
    private Integer giftflag;

    /**
     * 品牌id
     */
    private Integer brandId;

    /**
     * 花费
     */
    private BigDecimal cost;

    /**
     * 展现
     */
    private Integer impression;

    /**
     * 点击
     */
    private Integer click;

    /**
     * 老客花费
     */
    private BigDecimal oldCustomerCost;

    /**
     * 新客花费
     */
    private BigDecimal newCustomerCost;

    /**
     * 老客数量
     */
    private Integer oldCustomerCnt;

    /**
     * 新客数量
     */
    private Integer newCustomerCnt;

    /**
     * 加购总数
     */
    private Integer totalCartCnt;

    /**
     * 订单总数
     */
    private Integer totalOrderCnt;

    /**
     * 成交订单总数
     */
    private Integer totalDealOrderCnt;

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
        DwdJdIndustryBrandData other = (DwdJdIndustryBrandData) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDateStr() == null ? other.getDateStr() == null : this.getDateStr().equals(other.getDateStr()))
            && (this.getBrandType() == null ? other.getBrandType() == null : this.getBrandType().equals(other.getBrandType()))
            && (this.getBusiness() == null ? other.getBusiness() == null : this.getBusiness().equals(other.getBusiness()))
            && (this.getCid3() == null ? other.getCid3() == null : this.getCid3().equals(other.getCid3()))
            && (this.getTransDays() == null ? other.getTransDays() == null : this.getTransDays().equals(other.getTransDays()))
            && (this.getCaliber() == null ? other.getCaliber() == null : this.getCaliber().equals(other.getCaliber()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getGiftflag() == null ? other.getGiftflag() == null : this.getGiftflag().equals(other.getGiftflag()))
            && (this.getBrandId() == null ? other.getBrandId() == null : this.getBrandId().equals(other.getBrandId()))
            && (this.getCost() == null ? other.getCost() == null : this.getCost().equals(other.getCost()))
            && (this.getImpression() == null ? other.getImpression() == null : this.getImpression().equals(other.getImpression()))
            && (this.getClick() == null ? other.getClick() == null : this.getClick().equals(other.getClick()))
            && (this.getOldCustomerCost() == null ? other.getOldCustomerCost() == null : this.getOldCustomerCost().equals(other.getOldCustomerCost()))
            && (this.getNewCustomerCost() == null ? other.getNewCustomerCost() == null : this.getNewCustomerCost().equals(other.getNewCustomerCost()))
            && (this.getOldCustomerCnt() == null ? other.getOldCustomerCnt() == null : this.getOldCustomerCnt().equals(other.getOldCustomerCnt()))
            && (this.getNewCustomerCnt() == null ? other.getNewCustomerCnt() == null : this.getNewCustomerCnt().equals(other.getNewCustomerCnt()))
            && (this.getTotalCartCnt() == null ? other.getTotalCartCnt() == null : this.getTotalCartCnt().equals(other.getTotalCartCnt()))
            && (this.getTotalOrderCnt() == null ? other.getTotalOrderCnt() == null : this.getTotalOrderCnt().equals(other.getTotalOrderCnt()))
            && (this.getTotalDealOrderCnt() == null ? other.getTotalDealOrderCnt() == null : this.getTotalDealOrderCnt().equals(other.getTotalDealOrderCnt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDateStr() == null) ? 0 : getDateStr().hashCode());
        result = prime * result + ((getBrandType() == null) ? 0 : getBrandType().hashCode());
        result = prime * result + ((getBusiness() == null) ? 0 : getBusiness().hashCode());
        result = prime * result + ((getCid3() == null) ? 0 : getCid3().hashCode());
        result = prime * result + ((getTransDays() == null) ? 0 : getTransDays().hashCode());
        result = prime * result + ((getCaliber() == null) ? 0 : getCaliber().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getGiftflag() == null) ? 0 : getGiftflag().hashCode());
        result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
        result = prime * result + ((getCost() == null) ? 0 : getCost().hashCode());
        result = prime * result + ((getImpression() == null) ? 0 : getImpression().hashCode());
        result = prime * result + ((getClick() == null) ? 0 : getClick().hashCode());
        result = prime * result + ((getOldCustomerCost() == null) ? 0 : getOldCustomerCost().hashCode());
        result = prime * result + ((getNewCustomerCost() == null) ? 0 : getNewCustomerCost().hashCode());
        result = prime * result + ((getOldCustomerCnt() == null) ? 0 : getOldCustomerCnt().hashCode());
        result = prime * result + ((getNewCustomerCnt() == null) ? 0 : getNewCustomerCnt().hashCode());
        result = prime * result + ((getTotalCartCnt() == null) ? 0 : getTotalCartCnt().hashCode());
        result = prime * result + ((getTotalOrderCnt() == null) ? 0 : getTotalOrderCnt().hashCode());
        result = prime * result + ((getTotalDealOrderCnt() == null) ? 0 : getTotalDealOrderCnt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dateStr=").append(dateStr);
        sb.append(", brandType=").append(brandType);
        sb.append(", business=").append(business);
        sb.append(", cid3=").append(cid3);
        sb.append(", transDays=").append(transDays);
        sb.append(", caliber=").append(caliber);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", giftflag=").append(giftflag);
        sb.append(", brandId=").append(brandId);
        sb.append(", cost=").append(cost);
        sb.append(", impression=").append(impression);
        sb.append(", click=").append(click);
        sb.append(", oldCustomerCost=").append(oldCustomerCost);
        sb.append(", newCustomerCost=").append(newCustomerCost);
        sb.append(", oldCustomerCnt=").append(oldCustomerCnt);
        sb.append(", newCustomerCnt=").append(newCustomerCnt);
        sb.append(", totalCartCnt=").append(totalCartCnt);
        sb.append(", totalOrderCnt=").append(totalOrderCnt);
        sb.append(", totalDealOrderCnt=").append(totalDealOrderCnt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}