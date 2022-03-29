package com.liang.argorithm.aboutproject.transform.rebuild.rowtocol;

import com.liang.argorithm.aboutproject.transform.rebuild.AbstractRebuildStrategy;
import com.liang.argorithm.aboutproject.transform.rebuild.XMLTreeNode;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 用于实现部分xml标签的行列转换
 *
 * @author liangbingtian
 * @date 2022/03/26 下午7:30
 */
public class XMLRowToColRebuildStrategy extends AbstractRebuildStrategy {

  private Set<String> discardNodeStrSet = new TreeSet<>();

  private Map<String, Map<String, String>> tagSubNodeMap = new TreeMap<>();

  private List<XMLTreeNode> treeNodes = new LinkedList<>();

  private boolean addIndex = true;

  public XMLRowToColRebuildStrategy(String rebuildPath, boolean addIndex) {
    super(rebuildPath);
    this.addIndex = addIndex;
  }

  public XMLRowToColRebuildStrategy(String rebuildPath) {
    super(rebuildPath);
  }

  @Override
  protected void treeRebuild(XMLTreeNode treeNode, String path) {
    if (path==null||path.equals("")) {
      path = treeNode.getKey();
    }else {
      path+="."+treeNode.getKey();
    }
    if (this.getRebuildPath().startsWith(path)&&!this.getRebuildPath().equals(path)) {
      for (XMLTreeNode node : treeNode.getSonNodeList()) {
        treeRebuild(node, path);
      }
    }else if (this.getRebuildPath().equals(path)){
      reviseXmlTreeNode(treeNode);
    }

  }

  public void reviseXmlTreeNode(XMLTreeNode treeNode) {
    LinkedList<XMLTreeNode> treeNodeLayer = new LinkedList<>(treeNode.getSonNodeList());
    treeNode.getSonNodeList().clear();
    while (true) {
      if (treeNodeLayer.size()!=0&&treeNodeLayer.peekFirst().getSonNodeList().size()!=0) {
        XMLTreeNode subNode = new XMLTreeNode();
        boolean flag = false;
        int subIndex = 1;
        for (XMLTreeNode xmlTreeNode : treeNodeLayer) {
          XMLTreeNode tmpSubNode = xmlTreeNode.getSonNodeList().pollFirst();
          subNode.setKey(tmpSubNode.getKey());
          String key = xmlTreeNode.getKey()+"_"+subIndex;
          if (!addIndex) {
            key = xmlTreeNode.getKey();
          }
          tmpSubNode.setKey(key);
          subIndex++;
          if (discardNodeStrSet.contains(tmpSubNode.getKey())) {
            flag = true;
          }
          subNode.addSonNode(tmpSubNode);
        }
        if (!flag) {
          Map<String, String> subTagNodeMap = tagSubNodeMap.getOrDefault(subNode.getKey(),
              Collections.emptyMap());
          for (Map.Entry<String, String> entry : subTagNodeMap.entrySet()) {
            XMLTreeNode xmlTreeNode = new XMLTreeNode(entry.getKey());
            xmlTreeNode.setValue(entry.getValue());
            subNode.addSonNode(xmlTreeNode);
          }
          treeNode.addSonNode(subNode);
        }
      }else{
        break;
      }
    }

  }
}
