package com.liang.argorithm.aboutproject.transform;

import com.liang.argorithm.aboutproject.consumer.save.KJKMSaveConsumer;
import com.liang.argorithm.aboutproject.repository.AccountingRepository;
import com.liang.argorithm.aboutproject.producer.AccountXmlProducer;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author liangbingtian
 * @date 2022/02/25 下午9:28
 */
@Component
public class TransformMethod {

  @Autowired
  private AccountingRepository repository;

  public void parseXmlBySAX(InputStream inputStream)
      throws ParserConfigurationException, SAXException, IOException {
    SAXParserFactory parserFactory = SAXParserFactory.newInstance();
    parserFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
    parserFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
    parserFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
    SAXParser saxParser = parserFactory.newSAXParser();
    InputSource inputSource = new InputSource(inputStream);
    inputSource.setEncoding("GB18030");
    AccountXmlProducer producer = new AccountXmlProducer();
    KJKMSaveConsumer kjkmSaveConsumer = new KJKMSaveConsumer();
    kjkmSaveConsumer.setRepository(repository);
    producer.addConsumer(kjkmSaveConsumer);
    saxParser.parse(inputSource, producer);
  }
}
