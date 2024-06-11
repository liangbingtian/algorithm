package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 京东快车关键词分类，分词信息
 * @TableName jd_keyword_type
 */
@TableName(value ="jd_keyword_type")
@Data
public class JdKeywordType implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 关键词类别，1-品类词，2-品牌词，3-功效词，4-集团词，5-竞品词，6-礼物词，7-明星词，8-通用词，9-智能词
     */
    private Integer keywordType;

    /**
     * 该单词的切词词语
     */
    private String segmentWords;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 投放账号名称
     */
    private String username;

    /**
     * 是否私有，0-否，1-是
     */
    private Byte tfSelf;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
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
        JdKeywordType other = (JdKeywordType) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getKeyword() == null ? other.getKeyword() == null : this.getKeyword().equals(other.getKeyword()))
            && (this.getKeywordType() == null ? other.getKeywordType() == null : this.getKeywordType().equals(other.getKeywordType()))
            && (this.getSegmentWords() == null ? other.getSegmentWords() == null : this.getSegmentWords().equals(other.getSegmentWords()))
            && (this.getBrand() == null ? other.getBrand() == null : this.getBrand().equals(other.getBrand()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getTfSelf() == null ? other.getTfSelf() == null : this.getTfSelf().equals(other.getTfSelf()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getKeyword() == null) ? 0 : getKeyword().hashCode());
        result = prime * result + ((getKeywordType() == null) ? 0 : getKeywordType().hashCode());
        result = prime * result + ((getSegmentWords() == null) ? 0 : getSegmentWords().hashCode());
        result = prime * result + ((getBrand() == null) ? 0 : getBrand().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getTfSelf() == null) ? 0 : getTfSelf().hashCode());
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
        sb.append(", keyword=").append(keyword);
        sb.append(", keywordType=").append(keywordType);
        sb.append(", segmentWords=").append(segmentWords);
        sb.append(", brand=").append(brand);
        sb.append(", username=").append(username);
        sb.append(", tfSelf=").append(tfSelf);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}