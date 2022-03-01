package com.liang.argorithm.aboutproject.producer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class AccountXmlProducer extends SaxAccountXmlProducer {

  private boolean first = true;

  private static final String LEDGER = "总账";

  private boolean exist = false;

  @Override
  public void startElement(String uri, String localName, String qName,
      Attributes attributes) throws SAXException {
    if (first && LEDGER.equals(qName)) {
      exist = true;
    }
    first = false;
    /**
     * 判断是否是总账xml
     * 如果是总账xml，则继续后续的遍历
     */
    if (exist) {
      super.startElement(uri, localName, qName, attributes);
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    if (exist) {
      super.endElement(uri, localName, qName);
    }
  }

  @Override
  public void endDocument() throws SAXException {
    // 不做任何处理。
  }

  /**
   * SAX方式判断总账标签是否存在
   */
  public boolean isExist() {
    return exist;
  }

  public boolean isFirst() {
    return first;
  }
}
