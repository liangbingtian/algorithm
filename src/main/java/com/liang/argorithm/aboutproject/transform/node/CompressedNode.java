package com.liang.argorithm.aboutproject.transform.node;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * 压缩树的节点
 *
 * @author liangbingtian
 * @date 2022/03/05 上午10:38
 */
@Getter
@Setter
public class CompressedNode {

  private String sourcePath;

  private String targetPath;

  private Object value;

  private List<CompressedNode> subNodeList = new LinkedList<>();

  public Object getTargetJSONObject() {
    Object result = new LinkedHashMap<>();
    getTargetJSON("", result);
    return result;
  }


  private void getTargetJSON(String parentPath, Object parentNode) {
    String currPath = this.targetPath.substring(parentPath.length());
    if (".".equals(currPath)) {
      //如果是对象数组中的对象
      Map<String, Object> treeNode = new LinkedHashMap<>();
      for (CompressedNode subNode : this.getSubNodeList()) {
        subNode.getTargetJSON(this.getTargetPath(), treeNode);
      }
      ((List) parentNode).add(treeNode);
    } else if ("]".equals(currPath)) {
      //如果是数组中的值
      ((List) parentNode).add(this.getValue());
    } else {
      //按照.进行拆分
      String[] split = currPath.split("\\.");
      Object curr = null;
      Object preNode = parentNode;
      for (int i = 0; i < split.length; ++i) {
        String str = split[i];
        if (!"".equals(str) && !"[".equals(str)) {
          if (i != split.length - 1 && split[i + 1].equals("[")) {
            //如果是数组
            curr = new ArrayList<>();
            ((LinkedHashMap<String, Object>) preNode).put(str, curr);
          } else if (i == split.length - 1 && this.getSubNodeList().size() == 0) {
            //是值
            ((LinkedHashMap<String, Object>) preNode).put(str, this.getValue());
          } else {
            //是JSONObject
            curr = new LinkedHashMap<>();
            ((LinkedHashMap<String, Object>) preNode).put(str, curr);
            preNode = curr;
          }
        }
      }
      for (CompressedNode subNode : this.getSubNodeList()) {
        subNode.getTargetJSON(this.getTargetPath(), curr);
      }
    }
  }


}
