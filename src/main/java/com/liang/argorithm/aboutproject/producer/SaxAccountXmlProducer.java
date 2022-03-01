package com.liang.argorithm.aboutproject.producer;

import com.alibaba.fastjson.JSONObject;
import com.liang.argorithm.aboutproject.consumer.IAccountXmlConsumer;
import com.liang.argorithm.aboutproject.transform.json.Sax2JSONWithoutAttrHandler;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX的会计科目信息提取producer
 */
public class SaxAccountXmlProducer extends DefaultHandler {

  Sax2JSONWithoutAttrHandler jsonWithoutAttrHandler = new Sax2JSONWithoutAttrHandler();
  private final StringBuilder stringBuilder = new StringBuilder();

  private Map<String, IAccountXmlConsumer> xmlConsumerMap = new HashMap<>();
  private Set<String> xmlConsumerFirstIteratorSet = new HashSet<>();

  private String lastConsumer = null;

  boolean flag = false;

  // 遍历树时的模式，用于模式匹配
  private LinkedList<String> pathList = new LinkedList<>();

  /**
   * 生命周期管理。开始消费
   * @throws SAXException
   */
  @Override
  public void startDocument() throws SAXException {
    super.startDocument();
  }

  /**
   * 生命周期管理。结束消费
   * @throws SAXException
   */
  @Override
  public void endDocument() throws SAXException {
    if (lastConsumer != null) {
      // 上一轮标签解析结束
      xmlConsumerMap.get(lastConsumer).end();
      lastConsumer = null;
    }
    super.endDocument();
  }

  /**
   * 解析xml元素
   */
  @Override
  public void startElement(String uri, String localName, String qName,
      Attributes attributes) throws SAXException {
    pathList.addLast(qName);
    String pathStr = this.getPathStr();
    boolean pathContains = xmlConsumerMap.containsKey(pathStr);
    if (pathContains) {
      // 记录pathStr标签是否开始第一次遍历。
      if (!xmlConsumerFirstIteratorSet.contains(pathStr)) {
        xmlConsumerMap.get(pathStr).start();
        xmlConsumerFirstIteratorSet.add(pathStr);
        flag = true;
      }
      if (lastConsumer == null) {
        lastConsumer = pathStr;
      } else if (!lastConsumer.equals(pathStr)) {
        xmlConsumerMap.get(lastConsumer).end();
        lastConsumer = pathStr;
      }
    }
    if (flag) {
      jsonWithoutAttrHandler.startElement(uri, localName, qName, attributes);
    }
    super.startElement(uri, localName, qName, attributes);
  }

  @Override
  public void endElement(String uri, String localName, String qName)
      throws SAXException {
    if(flag) {
      jsonWithoutAttrHandler.endElement(uri, localName, qName);
      super.endElement(uri, localName, qName);
    }
    String pathStr = getPathStr();
    if(xmlConsumerMap.containsKey(pathStr)) {
      flag = false;
      JSONObject root = jsonWithoutAttrHandler.getRoot();
      root = root.getJSONObject(pathList.peekLast());
      xmlConsumerMap.get(pathStr).consume(root);
      jsonWithoutAttrHandler.setRoot(null);
    } else if (!flag && lastConsumer != null) {
      // 上一轮标签解析结束
      xmlConsumerMap.get(lastConsumer).end();
      lastConsumer = null;
    }
    pathList.removeLast();
    super.endElement(uri, localName, qName);
  }

  @Override
  public void characters(char[] ch, int start, int length)
      throws SAXException {
    if (flag) {
      jsonWithoutAttrHandler.characters(ch, start, length);
    }
  }

  private String getPathStr() {
    // 复用stringBuilder，减少gc开销
    stringBuilder.setLength(0);
    if (pathList.size() == 0) {
      return "";
    }
    Iterator<String> iterator = pathList.iterator();
    stringBuilder.append(iterator.next());
    while (iterator.hasNext()) {
      stringBuilder.append(".").append(iterator.next());
    }
    return stringBuilder.toString();
  }

  public void addConsumer(IAccountXmlConsumer xmlConsumer) {
    xmlConsumerMap.put(xmlConsumer.getXmlPath(), xmlConsumer);
  }

}
