package com.liang.argorithm.aboutproject.cache.list;

import java.util.List;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

/**
 * @author liangbingtian
 * @date 2023/06/15 下午3:06
 */
public class TodoDemo {

  private final Jedis jedis = new Jedis("127.0.0.1");

  public void addTodoEvent(long userId, String todoEvent) {
    jedis.lpush("todo_event::"+userId, todoEvent);
  }

  public List<String> findTodoEventByPage(long userId, int pageNo, int pageSize) {
    int startIndex = (pageNo-1)*pageSize;
    int endIndex = pageNo*pageSize-1;
    return jedis.lrange("todo_event::"+userId, startIndex, endIndex);
  }

  /**
   * 插入到目标的哪个位置
   * @param userId id
   * @param position 前面还是后边
   * @param todoEvent 要插入的
   * @param targetTodoEvent 插入的目标
   */
  public void insertTodoEvent(long userId, ListPosition position, String todoEvent, String targetTodoEvent) {
    jedis.linsert("todo_event::"+userId, position, targetTodoEvent, todoEvent);
  }

  /**
   * count>0从表头开始向表尾搜索，count<0从表尾开始向表头搜索删除一个；count==0删除所有的数据
   * @param userId
   * @param todoEvent
   */
  public void finishTodoEvent(long userId, String todoEvent) {
    jedis.lrem("todo_event::"+userId, 0, todoEvent);
  }

  public void updateTodoEvent(long userId, int index, String updateTodoEvent) {
    jedis.lset("todo_event::"+userId, index, updateTodoEvent);
  }
}
