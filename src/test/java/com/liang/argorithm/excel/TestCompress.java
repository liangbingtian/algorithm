package com.liang.argorithm.excel;

import com.alibaba.fastjson.JSON;
import com.liang.argorithm.utils.GzipUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author liangbingtian
 * @date 2023/10/30 下午5:41
 */
@Slf4j
public class TestCompress {

  @Test
  public void compress() {
    String text = "{\"averageHistoryRankExpand\":\"-\",\"businessId\":\"1898519\",\"campaignId\":477235818,\"campaignName\":\"10028119363005-米果类柿种原味65g*5\",\"clicks\":0,\"cost\":0.0000,\"cpc\":0.0000,\"cpm\":0.0000,\"ctr\":0.0000,\"dateStr\":\"2023-05-09\",\"dateTime\":1683628500000,\"directCartCnt\":0,\"directOrderCnt\":0,\"directOrderSum\":0.0000,\"groupId\":477236511,\"groupName\":\"行业词\",\"hour\":18,\"id\":1,\"impressions\":0,\"indirectCartCnt\":0,\"indirectOrderCnt\":0,\"indirectOrderSum\":0.0000,\"kw\":\"柿子种\",\"kwFlag\":\"0\",\"kwId\":1898519,\"kwType\":1,\"minute\":35,\"newCurrentPcShowq\":8.0000,\"newCurrentWlShowq\":3.0000,\"newPcRank\":0,\"newWlRank\":0,\"pcPrice\":2.5000,\"searchPromoteRankCoef\":10,\"searchPromoteRankEnable\":0,\"searchPromoteRankSuccessRate\":0.0000,\"totalCartCnt\":0,\"totalOrderCnt\":0,\"totalOrderCvs\":0.0000,\"totalOrderRoi\":0.0000,\"totalOrderSum\":0.0000,\"type\":8,\"username\":\"龟田制果KAMEDA\",\"wlPrice\":2.5000}";
    final byte[] bytes = GzipUtils
        .getGzipCompressBytes(text.getBytes());
    final String s = Base64.encodeBase64String(bytes);
    log.info("before:{},after:{}", text.length(), s.length());
    final byte[] bytes1 = GzipUtils.getGzipUnCompressBytes(Base64.decodeBase64(s));
    String text1 = new String(bytes1);
    Assert.assertEquals(text, text1);
  }

}
