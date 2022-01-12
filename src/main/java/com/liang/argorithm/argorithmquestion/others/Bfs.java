package com.liang.argorithm.argorithmquestion.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 广度优先相关算法
 *
 * @author liangbingtian
 * @date 2021/10/07 下午10:10
 */
public class Bfs {

  /**
   * 基因序列从A变化到B的最小变化数,而且每一步的变化都用基因库去限制
   *
   * @return
   */
  int minMutation(String start, String end, List<String> bank) {
    //1.用set去记录bank，如果其中某一个已经遍历完，就从bank中删除，避免重复遍历
    Set<String> bankSet = new HashSet<>(bank);
    //2. 如果end没在银行中，直接返回-1
    if (!bankSet.contains(end)) {
      return -1;
    }
    //3.因为要做双广度，所以记录头set和尾set
    Set<String> headSet = new HashSet<>(Collections.singletonList(start));
    Set<String> tailSet = new HashSet<>(Collections.singletonList(end));
    Set<String> tmp = new HashSet<>();
    List<Character> characters = Arrays.asList('A', 'C', 'G', 'T');
    //记录最小的层数
    int round = 0;
    while (!headSet.isEmpty() && !tailSet.isEmpty()) {
      round++;
      if (headSet.size() > tailSet.size()) {
        Set<String> node = headSet;
        headSet = tailSet;
        tailSet = node;
      }
      for (String str : headSet) {
        StringBuilder sb = new StringBuilder(str);
        int n = sb.length();
        for (int i = 0; i < n; ++i) {
          char old = sb.charAt(i);
          for (Character character : characters) {
            sb.setCharAt(i, character);
            if (tailSet.contains(sb.toString())) {
              return round;
            } else if (bankSet.contains(sb.toString())) {
              bankSet.remove(sb.toString());
              tmp.add(sb.toString());
            }
          }
          sb.setCharAt(i, old);
        }
      }
      Set<String> node = headSet;
      headSet = tmp;
      tmp = node;
      tmp.clear();
    }
    return -1;
  }


  /**
   * 单词接龙二，先构建最短序列的字典树，然后再进行深搜
   *
   * @param beginWord
   * @param endWord
   * @param wordList
   * @return
   */
  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    List<List<String>> result = new ArrayList<>();
    Set<String> wordSet = new HashSet<>(wordList);
    if (!wordSet.contains(endWord)) {
      return null;
    }
    Set<String> headSet = new HashSet<>(Collections.singletonList(beginWord));
    Set<String> tailSet = new HashSet<>(Collections.singletonList(endWord));
    Map<String, List<String>> map = new HashMap<>();
    boolean flag = true;
    boolean isFront = true;
    Set<String> nextLayer = new HashSet<>();
    while (!headSet.isEmpty() && !tailSet.isEmpty() && flag) {
      if (headSet.size() > tailSet.size()) {
        Set<String> tmp = headSet;
        headSet = tailSet;
        tailSet = tmp;
        isFront = !isFront;
      }
      wordSet.removeAll(headSet);
      for (String str : headSet) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); ++i) {
          char oldChar = sb.charAt(i);
          for (char ch = 'a'; ch <= 'z'; ++ch) {
            sb.setCharAt(i, ch);
            String curr = sb.toString();
            if (tailSet.contains(curr) || wordSet.contains(curr)) {
              String up = isFront ? str : curr;
              String down = isFront ? curr : str;
              map.putIfAbsent(up, new ArrayList<>());
              map.get(up).add(down);
              nextLayer.add(curr);
              if (tailSet.contains(curr)) {
                flag = false;
              }
            }
          }
          sb.setCharAt(i, oldChar);
        }
      }
      Set<String> tmp = headSet;
      headSet = nextLayer;
      nextLayer = tmp;
      nextLayer.clear();
    }
    if (flag) {
      return result;
    }
    dfsLeter(map, result, beginWord, endWord, new LinkedList<>());
    return result;
  }

  private boolean findLadders(Set<String> headSet, Set<String> tailSet, Set<String> wordSet
      , boolean isFront, Map<String, List<String>> map) {
    if (headSet.isEmpty()) {
      return false;
    }
    if (headSet.size() > tailSet.size()) {
      return findLadders(tailSet, headSet, wordSet, !isFront, map);
    }
    boolean isMeet = false;
    wordSet.removeAll(headSet);
    Set<String> nextLayer = new HashSet<>();
    for (String str : headSet) {
      StringBuilder sb = new StringBuilder(str);
      for (int i = 0; i < sb.length(); ++i) {
        char old = sb.charAt(i);
        for (char ch = 'a'; ch <= 'z'; ++ch) {
          sb.setCharAt(i, ch);
          String currStr = sb.toString();
          if (wordSet.contains(currStr)) {
            String up = isFront ? str : currStr;
            String down = isFront ? currStr : str;
            if (!map.containsKey(up)) {
              map.put(up, new ArrayList<>());
            }
            map.get(up).add(down);
            nextLayer.add(currStr);
            if (tailSet.contains(currStr)) {
              isMeet = true;
            }
          }
        }
        sb.setCharAt(i, old);
      }
    }
    if (isMeet) {
      return true;
    }
    return findLadders(nextLayer, tailSet, wordSet, isFront, map);
  }

  /**
   * 尝试使用非递归的方法
   */

  private void dfsLeter(Map<String, List<String>> oneAndItsSubs, List<List<String>> result,
      String beginWord, String endWord, LinkedList<String> list) {
    list.add(beginWord);
    if (beginWord.equals(endWord)) {
      result.add(new ArrayList<>(list));
      list.removeLast();
      return;
    }
    List<String> nextLayers = oneAndItsSubs.getOrDefault(beginWord, new ArrayList<>());
    for (String str : nextLayers) {
      dfsLeter(oneAndItsSubs, result, str, endWord, list);
    }
    list.removeLast();
  }


  public static void main(String[] args) {
    String begin = "hit";
    String end = "cog";
    List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
    new Bfs().findLadders(begin, end, wordList);
  }


}
