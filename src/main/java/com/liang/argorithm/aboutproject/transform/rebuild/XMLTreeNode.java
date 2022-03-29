package com.liang.argorithm.aboutproject.transform.rebuild;

import java.util.LinkedList;

/**
 * 用于表示xml树结构，用于xml行列转换
 * @author liuqiangm
 */
public class XMLTreeNode {

  String key;
  String value;
  XMLTreeNode parentNode;
  LinkedList<XMLTreeNode> sonNodeList = new LinkedList<>();

  public XMLTreeNode() {
  }

  public XMLTreeNode(String key) {
    this.key = key;
  }

  public void addSonNode(XMLTreeNode sunNode) {
    sunNode.parentNode = this;
    this.sonNodeList.add(sunNode);
  }

  public void addParentNode(XMLTreeNode parentNode) {
    parentNode.addSonNode(this);
  }

  public void addParentNodeAndSortFirst(XMLTreeNode parentNode) {
    this.parentNode = parentNode;
    parentNode.sonNodeList.addFirst(this);
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public XMLTreeNode getParentNode() {
    return parentNode;
  }

  public void setParentNode(XMLTreeNode parentNode) {
    this.parentNode = parentNode;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.format("\"%s\":\"%s\"", key, value);
  }

  public LinkedList<XMLTreeNode> getSonNodeList() {
    return sonNodeList;
  }
}
