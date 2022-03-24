package com.liang.argorithm.aboutproject.transform.node;

import com.alibaba.fastjson.JSONObject;
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


  private static void filterPatternNode(PatternNode patternNode, Entry<String, Object> entry) {
    String key = entry.getKey();
    Object value = entry.getValue();
    String[] split = key.split("->");
    if (split.length == 2) {
      patternNode.setSourcePath(split[0]);
      patternNode.setTargetPath(split[1]);
      if (value instanceof JSONObject) {
        patternNode.getPatternNodeList().addAll(getPatternNodeFromJSONObject((JSONObject) value));
      }
    } else {
      patternNode.setTargetPath(key);
      if (value instanceof JSONObject) {
        patternNode.getPatternNodeList().addAll(getPatternNodeFromJSONObject((JSONObject) value));
      } else {
        patternNode.setValue(value);
      }
    }
  }

  public static List<PatternNode> getPatternNodeFromJSONObject(JSONObject jsonObject) {
    List<PatternNode> resultList = new LinkedList<>();
    for (Entry<String, Object> entry : jsonObject.entrySet()) {
      PatternNode patternNode = new PatternNode();
      filterPatternNode(patternNode, entry);
      resultList.add(patternNode);
    }
    return resultList;
  }
}
