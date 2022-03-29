package com.liang.argorithm.aboutproject.transform.rebuild;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 将xml构建出树结构
 *
 * @author liangbingtian
 * @date 2022/03/26 下午7:47
 */
public class XMLReBuilder extends DefaultHandler {

  private boolean rebuild = false;

  private XMLTreeNode root;

  private String value;

  private LinkedList<XMLTreeNode> treeNodes = new LinkedList<>();

  private List<AbstractRebuildStrategy> rebuildStrategyList = new ArrayList<>();

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes)
      throws SAXException {
    treeNodes.push(new XMLTreeNode(qName));
    super.startElement(uri, localName, qName, attributes);
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    super.endElement(uri, localName, qName);
    XMLTreeNode currNode = treeNodes.pop();
    if (treeNodes.size()==0) {
      root = currNode;
    }else {
      treeNodes.peek().addSonNode(currNode);
    }
    currNode.setValue(value);
    value = null;
  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    super.characters(ch, start, length);
    value = new String(ch, start, length).trim();
  }

  public void addAllRebuildStrategyList(List<AbstractRebuildStrategy> strategyList) {
    rebuildStrategyList.addAll(strategyList);
  }

  public void writeToOutputStream(OutputStream outputStream) {
    if (!rebuild) {
      for (AbstractRebuildStrategy rebuildStrategy: rebuildStrategyList) {
        rebuildStrategy.treeRebuild(root, "");
        rebuildStrategy.setRebuild(true);
      }
      rebuild = true;
    }
    XMLOutputUtils.writeXMLToOutputStream(root, outputStream);
  }
}
