package com.liang.argorithm.excel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.liang.argorithm.aboutproject.entity.JdShopAuthorizeInfo;
import com.liang.argorithm.aboutproject.service.JdShopAuthorizeInfoService;
import com.liang.argorithm.excel.dto.ExcelRowItem;
import com.liang.argorithm.excel.enums.ExcelTypeEnum;
import com.liang.argorithm.excel.handler.ExcelHandlerFacade;
import com.liang.argorithm.excel.handler.ExcelIterator;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 针对XLSX、XLSM格式，可正确处理。
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.liang.argorithm.ArgorithmApplication.class)
public class XLSXTest {

  @Autowired
  private JdShopAuthorizeInfoService jdShopAuthorizeInfoService;

  @Test
  public void xlsxTest() {
    ExcelHandlerFacade excelHandler = new ExcelHandlerFacade();
    excelHandler.addExcelProcessor(((sheetName, sheetIndex, rowIndex, recordMap) -> {
      System.out.println(String
          .format("sheetName: %s, sheetIndex: %s, row: %d, record: %s", sheetName, sheetIndex, rowIndex, recordMap.toString()));
      JSONObject jsonObject = new JSONObject(recordMap);
      // 可以在此处将jsonObject数据使用转换工具，转换成任意目标格式（目标json或目标实体对象）
    }));
    excelHandler.processInputStream(
        XLSXTest.class.getClassLoader().getResourceAsStream("t0.xlsx"));
  }

  @Test
  public void xlsxIteratorTest() {
    ExcelIterator iterator = new ExcelIterator(
        XLSXTest.class.getClassLoader().getResourceAsStream("t0.xlsx"));
    while (iterator.hasNext()) {
      ExcelRowItem rowItem = iterator.next();
      System.out.println(String
          .format("sheetName: %s, sheetIndex: %s, row: %d, record: %s", rowItem.getSheetName(), rowItem.getSheetIndex(), rowItem.getRowIndex(), rowItem.getDataMap().toString()));
    }
    iterator.close();
  }

  @Test
  public void xlsxTest1() {
    ExcelHandlerFacade excelHandler = new ExcelHandlerFacade();
    excelHandler.addExcelProcessor(((sheetName, sheetIndex, rowIndex, recordMap) -> {
      System.out.println(String
          .format("sheetName: %s, sheetIndex: %s, row: %d, record: %s", sheetName, sheetIndex, rowIndex, recordMap.toString()));
      JSONObject jsonObject = new JSONObject(recordMap);
      // 可以在此处将jsonObject数据使用转换工具，转换成任意目标格式（目标json或目标实体对象）
    }));
    excelHandler.processInputStream(
        XLSXTest.class.getClassLoader().getResourceAsStream("发票单导入模板.xls"));
  }

  @Test
  public void test2() {
    System.out.println("This is a single quote: '''");
  }


  @Test
  public void xlsxTest2() {
    JsonFactory factory = new JsonFactory();
    try(FileOutputStream outputStream = new FileOutputStream("output.json");
            JsonGenerator generator = factory.createGenerator(outputStream);) {
      ExcelHandlerFacade excelHandler = new ExcelHandlerFacade();
      generator.writeStartArray();
      excelHandler.addExcelProcessor(((sheetName, sheetIndex, rowIndex, recordMap) -> {
        System.out.printf("sheetName: %s, sheetIndex: %s, row: %d, record: %s%n", sheetName, sheetIndex, rowIndex, recordMap.toString());
        final String status = (String) recordMap.get("4");
        final String jsonStr = (String) recordMap.get("7");
        final String username = (String) recordMap.get("2");
        if ("已更新".equals(status)) {
          generator.writeStartObject();
          Map<String, String> map = JSON.parseObject(jsonStr, Map.class);
          for (Map.Entry<String, String> entry : map.entrySet()) {
            generator.writeObjectField(entry.getKey(), entry.getValue());
          }
          generator.writeStringField("pin", username);
          generator.writeEndObject();
        }
        // 可以在此处将jsonObject数据使用转换工具，转换成任意目标格式（目标json或目标实体对象）
      }));
      excelHandler.processInputStream(
              XLSXTest.class.getClassLoader().getResourceAsStream("授权信息.xlsx"));
      generator.writeEndArray();
      generator.flush();

    }catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Test
  public void xlsxTest3() {
      StringBuilder str = new StringBuilder("insert into `basic_jd_udf_adserving_md_sku_set`(`sku_id`, `spu_id`, `level`, `cid2_name`, `cid3_name`, `tf_Promo`, `promo`)\n values \n");
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
              XLSXTest.class.getClassLoader().getResourceAsStream("V表2.xlsx"));
    String filePath = "output.txt";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      writer.write(str.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void xlsmTest() {
    ExcelHandlerFacade excelHandler = new ExcelHandlerFacade();
    excelHandler.addExcelProcessor(((sheetName, sheetIndex, rowIndex, recordMap) -> {
      System.out.println(String
          .format("sheetName: %s, sheetIndex: %s, row: %d, record: %s", sheetName, sheetIndex, rowIndex, recordMap.toString()));
      JSONObject jsonObject = new JSONObject(recordMap);
      // 可以在此处将jsonObject数据使用转换工具，转换成任意目标格式（目标json或目标实体对象）
    }));
    excelHandler.processInputStream(
        XLSXTest.class.getClassLoader().getResourceAsStream("t0.xlsm"),
        ExcelTypeEnum.XLSX);
  }

}
