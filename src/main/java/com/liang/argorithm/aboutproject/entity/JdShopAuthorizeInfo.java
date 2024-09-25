package com.liang.argorithm.aboutproject.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 精准通-账号授权信息
 * @TableName jd_shop_authorize_info
 */
@TableName(value ="jd_shop_authorize_info")
@Data
public class JdShopAuthorizeInfo implements Serializable {
    /**
     * 自增ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    @JSONField(name = "pin")
    private String username;

    /**
     * accessToken
     */
    @JSONField(name = "access_token")
    private String accessToken;

    /**
     * 进行刷新使用的token
     */
    @JSONField(name = "refresh_token")
    private String refreshToken;

    /**
     * appkey
     */
    private String appkey;

    /**
     * appSecret
     */
    private String appSecret;

    /**
     * 是否开启
     */
    private Byte tfOpen;

    /**
     * 平台 1-表示京东
     */
    private Integer platform;

    /**
     * 账号过期时间
     */
    private Date authorizeExpireTime;

    @TableField(exist = false)
    @JSONField(name = "time")
    private Long time;

    @TableField(exist = false)
    @JSONField(name = "expires_in")
    private Integer expiresIn;
    /**
     * 创建时间戳
     */
    private Date createDate;

    /**
     * 更新时间戳
     */
    private Date updateDate;

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
        JdShopAuthorizeInfo other = (JdShopAuthorizeInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getAccessToken() == null ? other.getAccessToken() == null : this.getAccessToken().equals(other.getAccessToken()))
            && (this.getRefreshToken() == null ? other.getRefreshToken() == null : this.getRefreshToken().equals(other.getRefreshToken()))
            && (this.getAppkey() == null ? other.getAppkey() == null : this.getAppkey().equals(other.getAppkey()))
            && (this.getAppSecret() == null ? other.getAppSecret() == null : this.getAppSecret().equals(other.getAppSecret()))
            && (this.getTfOpen() == null ? other.getTfOpen() == null : this.getTfOpen().equals(other.getTfOpen()))
            && (this.getPlatform() == null ? other.getPlatform() == null : this.getPlatform().equals(other.getPlatform()))
            && (this.getAuthorizeExpireTime() == null ? other.getAuthorizeExpireTime() == null : this.getAuthorizeExpireTime().equals(other.getAuthorizeExpireTime()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getAccessToken() == null) ? 0 : getAccessToken().hashCode());
        result = prime * result + ((getRefreshToken() == null) ? 0 : getRefreshToken().hashCode());
        result = prime * result + ((getAppkey() == null) ? 0 : getAppkey().hashCode());
        result = prime * result + ((getAppSecret() == null) ? 0 : getAppSecret().hashCode());
        result = prime * result + ((getTfOpen() == null) ? 0 : getTfOpen().hashCode());
        result = prime * result + ((getPlatform() == null) ? 0 : getPlatform().hashCode());
        result = prime * result + ((getAuthorizeExpireTime() == null) ? 0 : getAuthorizeExpireTime().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", accessToken=").append(accessToken);
        sb.append(", refreshToken=").append(refreshToken);
        sb.append(", appkey=").append(appkey);
        sb.append(", appSecret=").append(appSecret);
        sb.append(", tfOpen=").append(tfOpen);
        sb.append(", platform=").append(platform);
        sb.append(", authorizeExpireTime=").append(authorizeExpireTime);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}