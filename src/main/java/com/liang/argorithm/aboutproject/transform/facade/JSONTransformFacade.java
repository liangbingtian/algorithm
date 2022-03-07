package com.liang.argorithm.aboutproject.transform.facade;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.liang.argorithm.aboutproject.transform.iterator.JSONIterator;
import com.liang.argorithm.aboutproject.transform.node.CompressedNode;
import com.liang.argorithm.aboutproject.transform.node.PatternNode;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liangbingtian
 * @date 2022/03/03 上午10:49
 */
@Slf4j
public class JSONTransformFacade {

  /**
   * 为json所有数组中添加索引，将所有的json数组转化为json对象
   */
  public static void getJSONObjectWithIndex(JSONObject jsonObject) {
    for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
      if (entry.getValue() instanceof JSONObject) {
        getJSONObjectWithIndex((JSONObject) entry.getValue());
      }
      if (entry.getValue() instanceof JSONArray) {
        entry.setValue(getJSONObjectWithIndex((JSONArray) entry.getValue()));
      }
    }
  }

  /**
   * 将jsonArray添加索引，修改为jsonObject
   *
   * @param jsonArray
   * @return
   */
  public static JSONObject getJSONObjectWithIndex(JSONArray jsonArray) {
    JSONObject jsonObject = new JSONObject(true);
    for (int i = 0; i < jsonArray.size(); ++i) {
      String key = "_" + i;
      Object o = jsonArray.get(i);
      if (o instanceof JSONObject) {
        getJSONObjectWithIndex((JSONObject) o);
      }
      if (o instanceof JSONArray) {
        o = getJSONObjectWithIndex((JSONArray) o);
      }
      jsonObject.put(key, o);
    }
    return jsonObject;
  }

  /**
   * 将模式树的key值里的./替换成为原路径
   */
  public static void revisePatternJSONObject(JSONObject patternJSONObject, String parentSourcePath,
      String parentTargetPath, JSONObject revisedJSONObject) {
    if (patternJSONObject == null) {
      return;
    }
    for (Map.Entry<String, Object> entry : patternJSONObject.entrySet()) {
      String key = entry.getKey();
      Object value = entry.getValue();
      String revisedKey = key;
      int index = key.indexOf("->");
      String sourcePath = parentSourcePath;
      String targetPath = parentTargetPath;
      if (index == -1) {
        //目标节点直接映射
        if (key.startsWith("./")) {
          //如果以./开头，证明要把上面的替换掉
          targetPath = targetPath + key.substring(2);
        } else {
          targetPath = key;
        }
        revisedKey = targetPath;
      } else {
        //源节点映射到目标节点
        String key1 = key.substring(0, index);
        String key2 = key.substring(index + 2);
        //分别处理k1和k2
        if (key1.startsWith("./")) {
          sourcePath = sourcePath + key1.substring(2);
        } else {
          sourcePath = key1;
        }
        if (key2.startsWith("./")) {
          targetPath = targetPath + key2.substring(2);
        } else {
          targetPath = key2;
        }
        revisedKey = sourcePath + "->" + targetPath;
      }
      //如果value是jsonObject那么进行重构
      if (entry.getValue() instanceof JSONObject) {
        JSONObject subJSONObject = new JSONObject(true);
        revisedJSONObject.put(revisedKey, subJSONObject);
        revisePatternJSONObject((JSONObject) value, sourcePath, targetPath, subJSONObject);
      } else {
        revisedJSONObject.put(revisedKey, value);
      }
    }
  }


  public static JSONObject getJSONObjectFromSourceAndPattern(InputStream sourceInputStream,
      InputStream patternInputStream) {
    try {
      return getJSONObjectFromSourceAndPattern(JSONObject.parseObject(sourceInputStream,
          JSONObject.class, Feature.OrderedField),
          (JSONObject) JSONObject.parseObject(patternInputStream, JSONObject.class, Feature.OrderedField));
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }

  public static JSONObject getJSONObjectFromSourceAndPattern(JSONObject sourceJSONObject,
      JSONObject patternJSONObject) {
    JSONObject result = null;
    JSONObject revisedJSONObject = new JSONObject(true);
    revisePatternJSONObject(patternJSONObject, "", "", revisedJSONObject);
    PatternNode patternNode = PatternNode.getPatternNodeFromJSONObject(revisedJSONObject);
    JSONIterator jsonIterator = new JSONIterator();
    jsonIterator.setPatternNode(patternNode);
    jsonIterator.iterator(sourceJSONObject, "r");
    CompressedNode compressedNode = jsonIterator.getCompressedNode();
    Object object = compressedNode.getTargetJSONObject();
    String str = JSON.toJSONString(object);
    result = JSON.parseObject(str, Feature.OrderedField);
    return null;
  }

}
