package com.liang.argorithm.aboutproject.transform.json;


import com.liang.argorithm.aboutproject.transform.TransformMethod;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xml.sax.SAXException;

/**
 * @author liangbingtian
 * @date 2022/02/25 下午9:24
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.liang.argorithm.ArgorithmApplication.class)
public class ParseTest {

  @Autowired
  private TransformMethod transformMethod;

  @Test
  public void testParseMethod() throws IOException, SAXException, ParserConfigurationException {
//    transformMethod.parseXmlBySAX(ParseTest.class.getClassLoader().getResourceAsStream(
//        "pattern/xmls/总账类2.xml"));
    StringBuilder sb = new StringBuilder();
    StringBuilder append = sb.append("a").append("|").append("b");
    System.out.println(append);
  }
}