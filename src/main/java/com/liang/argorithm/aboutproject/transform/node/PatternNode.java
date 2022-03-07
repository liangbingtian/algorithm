package com.liang.argorithm.aboutproject.transform.node;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Getter;
import lombok.Setter;

/**
 * 模式树的节点
 *
 * @author liangbingtian
 * @date 2022/03/04 下午6:21
 */
@Setter
@Getter
public class PatternNode {

  private String sourcePath;

  private String targetPath;

  private Object value;

  private List<PatternNode> patternNodeList = new LinkedList<>();

  /**
   * 第一层的入口
   *
   * @param jsonObject
   * @return
   */
  public static PatternNode getPatternNodeFromJSONObject(Map<String, Object> jsonObject) {
    PatternNode patternNode = new PatternNode();
    if (jsonObject.size() == 1) {
      for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
        filterPatternNode(patternNode, entry);
      }
    } else if (jsonObject.size() != 0) {
      throw new RuntimeException("这个json有点问题");
    }
    return patternNode;
  }

  private static void filterPatternNode(PatternNode patternNode, Entry<String, Object> entry) {
    String key = entry.getKey();
    Object value = entry.getValue();
    String[] split = key.split("->");
    if (split.length == 2) {
      patternNode.setSourcePath(split[0]);
      patternNode.setTargetPath(split[1]);
      if (value instanceof Map) {
        if (((Map) value).size() == 1) {
          patternNode.getPatternNodeList().add(getPatternNodeFromJSONObject((Map) value));
        } else if (((Map) value).size() != 0) {
          patternNode.getPatternNodeList().addAll(getPatternNodeFromJSONObjectList((Map) value));
        }
      }
    } else {
      patternNode.setTargetPath(key);
      if (value instanceof Map) {
        patternNode.getPatternNodeList().addAll(getPatternNodeFromJSONObjectList((Map) value));
      } else {
        patternNode.setValue(value);
      }
    }
  }

  private static List<PatternNode> getPatternNodeFromJSONObjectList(
      Map<String, Object> jsonObject) {
    List<PatternNode> resultList = new LinkedList<>();
    for (Entry<String, Object> entry : jsonObject.entrySet()) {
      PatternNode patternNode = new PatternNode();
      filterPatternNode(patternNode, entry);
      resultList.add(patternNode);
    }
    return resultList;
  }
}
