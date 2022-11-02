package com.liang.argorithm.excel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liang.argorithm.aboutproject.entity.MonitorWordDict;
import com.liang.argorithm.aboutproject.mapper.MonitorWordDictMapper;
import com.liang.argorithm.excel.handler.ExcelHandlerFacade;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 导入测试
 *
 * @author liangbingtian
 * @date 2022/11/02 下午3:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.liang.argorithm.ArgorithmApplication.class)
public class ExcelImportTest2 {

  private static String commonFirst = null;

  @Autowired
  private MonitorWordDictMapper dictMapper;

  @Test
  public void test() {
    List<MonitorWordDict> monitorWordDicts = new ArrayList<>();
    ExcelHandlerFacade excelHandler = new ExcelHandlerFacade();
    excelHandler.addExcelProcessor(((sheetName, sheetIndex, rowIndex, recordMap) -> {
      if (rowIndex==0) {
        return;
      }
      String first = (String) recordMap.get("0");
      if (StringUtils.isNotEmpty(first)) {
        commonFirst = first;
      }
      MonitorWordDict dict = new MonitorWordDict();
      dict.setFirst(commonFirst);
      dict.setSecond((String) recordMap.get("1"));
      dict.setThird((String) recordMap.get("2"));
      monitorWordDicts.add(dict);
      System.out.println(String
          .format("sheetName: %s, sheetIndex: %s, row: %d, record: %s", sheetName, sheetIndex, rowIndex,
              JSON.toJSONString(dict)));
      dictMapper.insert(dict);
    }));
    excelHandler.processInputStream(
        XLSXTest.class.getClassLoader().getResourceAsStream("新词库_事件词_v3.1.xlsx"));
    System.out.println("插入完成");
  }

}
