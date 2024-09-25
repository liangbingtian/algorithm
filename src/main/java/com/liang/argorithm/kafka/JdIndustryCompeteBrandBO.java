package com.liang.argorithm.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * industryCompeteBrand的msg
 *
 * @author liangbingtian
 * @date 2024/08/21 12:04
 */
@Data
@ApiModel(value = "JdIndustryCompeteBrandBO")
public class JdIndustryCompeteBrandBO {

    private String username;

    @JsonProperty(value = "sku_cid3")
    private String skuCId3;

    @JsonProperty(value = "brand_id")
    private String brandId;

    @JsonProperty(value = "brand_name")
    private String brandName;

    @JsonProperty(value = "brand_no")
    private String brandNo;

    private String ctr;

    private String cpm;

    private String cvs;

    private String cpc;

    private String roi;

}
