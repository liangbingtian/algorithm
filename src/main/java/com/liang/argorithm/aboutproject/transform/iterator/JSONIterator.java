package com.liang.argorithm.aboutproject.transform.iterator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liang.argorithm.aboutproject.transform.node.CompressedNode;
import com.liang.argorithm.aboutproject.transform.node.PatternNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * json迭代器,用于在遍历json的时候构建compressNode
 *
 * @author liangbingtian
 * @date 2022/03/05 上午10:33
 */
@Getter
@Setter
@Slf4j
public class JSONIterator {

  private Map<String, Object> objectContainerMap = new HashMap<>();

  //模板根节点
  private PatternNode patternNode;

  //当前模板节点
  private PatternNode currPatternNode;

  //目标压缩树节点
  private CompressedNode compressedNode;

  //当前的压缩树节点
  private CompressedNode parentCompressedNode;

  private boolean reconstruct = false;

  private void addCompressedNodeToTree(CompressedNode cNode) {
    if (parentCompressedNode == null) {
      compressedNode = cNode;
    } else {
      parentCompressedNode.getSubNodeList().add(cNode);
    }
    parentCompressedNode = cNode;
  }

  public void iterator(Object object, String parentPath) {
    if (currPatternNode == null) {
      currPatternNode = patternNode;
    }
    PatternNode preCurrPatternNode = currPatternNode;
    List<Pair<String, Object>> allSubNodes = new ArrayList<>();
    if (object instanceof JSON) {
      fillAllSubNode((JSON) object, parentPath, allSubNodes);
      addObjectContainerToMap(allSubNodes);
    }
    CompressedNode preParentCompressedNode = parentCompressedNode;
    if (isPatternSourcePathEmpty(preCurrPatternNode)) {
      CompressedNode cNode = new CompressedNode();
      cNode.setTargetPath(currPatternNode.getTargetPath());
      cNode.setValue(currPatternNode.getValue());
      addCompressedNodeToTree(cNode);
      for (PatternNode node : currPatternNode.getPatternNodeList()) {
        currPatternNode = node;
        this.iterator(object, parentPath);
      }
    } else if (currPatternNode.getSourcePath().equals(parentPath)) {
      //声明压缩节点
      CompressedNode node = new CompressedNode();
      node.setSourcePath(currPatternNode.getSourcePath());
      node.setTargetPath(currPatternNode.getTargetPath());
      if (!(object instanceof JSON)) {
        node.setValue(object);
      }
      addCompressedNodeToTree(node);
      List<PatternNode> subPatternNodeList = currPatternNode.getPatternNodeList();
      Set<PatternNode> noMatchPatternNodeSet = new HashSet<>(subPatternNodeList);
      for (PatternNode subPatternNode : subPatternNodeList) {
        for (Pair<String, Object> subSourcePair : allSubNodes) {
          if (!isPatternSourcePathEmpty(subPatternNode) && subPatternNode.getSourcePath()
              .startsWith(subSourcePair.getKey())) {
            currPatternNode = subPatternNode;
            this.iterator(subSourcePair.getValue(), subSourcePair.getKey());
            noMatchPatternNodeSet.remove(subPatternNode);
          } else if (isPatternSourcePathEmpty(subPatternNode)){
            currPatternNode = subPatternNode;
            this.iterator(subSourcePair.getValue(), subSourcePair.getKey());
            noMatchPatternNodeSet.remove(subPatternNode);
            break;
          }
        }
      }
      //对于没有在当前层匹配到的，又可能就是直接映射
      for (PatternNode noMatch : noMatchPatternNodeSet) {
        noMatchConstructTree(noMatch);
      }
    } else if (currPatternNode.getSourcePath().startsWith(parentPath)) {
      log.info("匹配了部分前缀节点，则继续遍历子节点");
      for (Pair<String, Object> entry : allSubNodes) {
        if (currPatternNode.getSourcePath().startsWith(entry.getKey())) {
          this.iterator(entry.getValue(), entry.getKey());
        }
      }
    } else {
      //单层匹配不到
      noMatchConstructTree(patternNode);
    }
    //回溯之前全局变量复原
    parentCompressedNode = preParentCompressedNode;
    currPatternNode = preCurrPatternNode;
    removeObjectContainerMap(allSubNodes);
  }

  private void removeObjectContainerMap(List<Pair<String, Object>> allSubNodes) {
    for (Pair<String, Object> pair : allSubNodes) {
      objectContainerMap.remove(pair.getKey());
    }
  }

  public CompressedNode getCompressedNode() {
    if (!reconstruct && compressedNode != null) {
      reconstruct(compressedNode);
    }
    reconstruct = true;
    return compressedNode;
  }

  /**
   * 映射源JSON路径到map中
   *
   * @param json
   * @param parentPath
   * @param allSubNodes
   */
  private void fillAllSubNode(JSON json, String parentPath,
      List<Pair<String, Object>> allSubNodes) {
    if (json instanceof JSONObject) {
      for (Map.Entry<String, Object> entry : ((JSONObject) json).entrySet()) {
        Object value = entry.getValue();
        if (value instanceof JSONObject) {
          allSubNodes.add(
              new Pair<>(parentPath + (parentPath.endsWith(".") ? "" : ".") + entry.getKey(),
                  value));
        } else if (value instanceof JSONArray) {
          allSubNodes.add(
              new Pair<>(parentPath + (parentPath.endsWith(".") ? "" : ".") + entry.getKey() + ".[",
                  value));
        } else {
          allSubNodes.add(
              new Pair<>(parentPath + (parentPath.endsWith(".") ? "" : ".") + entry.getKey(),
                  value));
        }
      }
    } else if (json instanceof JSONArray) {
      for (Object object : (JSONArray) json) {
        if (object instanceof JSONObject) {
          allSubNodes.add(new Pair<>(parentPath + (parentPath.endsWith(".") ? "" : "."), object));
        } else if (object instanceof JSONArray) {
          allSubNodes
              .add(new Pair<>(parentPath + (parentPath.endsWith(".") ? "" : ".") + "[", object));
        } else {
          allSubNodes.add(new Pair<>(parentPath + "]", object));
        }
      }
    }
  }

  private void noMatchConstructTree(PatternNode patternNode) {
    CompressedNode node = new CompressedNode();
    node.setSourcePath(patternNode.getSourcePath());
    node.setTargetPath(patternNode.getTargetPath());
    node.setValue(objectContainerMap.get(patternNode.getSourcePath()));
    parentCompressedNode.getSubNodeList().add(node);
  }

  /**
   * 将直接的值抽出来
   *
   * @param subNodeList
   */
  private void addObjectContainerToMap(List<Pair<String, Object>> subNodeList) {
    for (Pair<String, Object> pair : subNodeList) {
      if (!(pair.getValue() instanceof JSON)) {
        objectContainerMap.put(pair.getKey(), pair.getValue());
      }
    }
  }

  /**
   * 检查模式节点的source是否为空或者是非r开头的
   */
  private boolean isPatternSourcePathEmpty(PatternNode patternNode) {
    return patternNode.getSourcePath() == null || !patternNode.getSourcePath().startsWith("r");
  }

  private void reconstruct(CompressedNode cNode) {
    for (CompressedNode subCompressNode : cNode.getSubNodeList()) {
      // 解决原为[]，目标变为[null]的问题
      if (subCompressNode.getTargetPath().endsWith(".[.")
          && subCompressNode.getSubNodeList().size() == 0) {
        cNode.getSubNodeList().clear();
        break;
      }
      reconstruct(subCompressNode);
    }
  }
}
