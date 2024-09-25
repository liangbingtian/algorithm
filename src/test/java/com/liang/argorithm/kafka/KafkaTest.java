package com.liang.argorithm.kafka;

import com.liang.argorithm.excel.handler.ExcelHandlerFacade;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 针对XLSX、XLSM格式，可正确处理。
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.liang.argorithm.ArgorithmApplication.class)
public class KafkaTest {


  @Test
  public void xlsxTest3() {
      StringBuilder str = new StringBuilder("insert into `jd_industry_compete_brand`(`username`, `sku_cid3`, `brand_id`, `brand_name`, `brand_no`, `ctr`, `cpm`, `cvs`, `cpc`, `roi`, `kc_dt`)\n values \n");
      ExcelHandlerFacade excelHandler = new ExcelHandlerFacade();
      excelHandler.addExcelProcessor(((sheetName, sheetIndex, rowIndex, recordMap) -> {
        System.out.printf("sheetName: %s, sheetIndex: %s, row: %d, record: %s%n", sheetName, sheetIndex, rowIndex, recordMap.toString());
        // 可以在此处将jsonObject数据使用转换工具，转换成任意目标格式（目标json或目标实体对象）
        if (rowIndex==0) {
          return;
        }
        String str1 = "('%s', '%s', '%s', '%s', '%s', '%s', '%s'),\n";
        String skuId = (String) recordMap.get("19");
        String skuIdStr = new BigDecimal(skuId).toPlainString();
        String cid1 = (String) recordMap.get("20");
        String cid3 = (String) recordMap.get("21");
        String spu = (String) recordMap.get("22");
        String spuStr = "#N/A".equals(spu)?"null":new BigDecimal(spu).toPlainString();
        String degree = (String) recordMap.get("23");
        String degreeStr = "#N/A".equals(degree)?"null":degree;
        String cuxiao = (String) recordMap.get("24");
        String cuxiaoStr = "#N/A".equals(cuxiao)? "否" : "是";
        String promo = (String) recordMap.get("25");
        String promoStr = "#N/A".equals(promo)?"null":promo;
        final String format = String.format(str1, skuIdStr, spuStr, degreeStr, cid1, cid3, cuxiaoStr, promoStr);
        str.append(format);
      }));
      excelHandler.processInputStream(
              KafkaTest.class.getClassLoader().getResourceAsStream("V表2.xlsx"));
    String filePath = "output.txt";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      writer.write(str.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
