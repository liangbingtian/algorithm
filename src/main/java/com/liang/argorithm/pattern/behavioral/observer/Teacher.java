package com.liang.argorithm.pattern.behavioral.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author liangbingtian
 * teacher是真正的观察者
 * @date 2022/02/25 下午12:47
 */
public class Teacher implements Observer {
  private String teacherName;

  public Teacher(String teacherName) {
    this.teacherName = teacherName;
  }

  @Override
  public void update(Observable o, Object arg) {
    Course course = (Course) o;
    Question question = (Question) arg;
    System.out.println(teacherName+"老师的"+course.getCourseName()+"课程接收到一个"+question.getQuestionContent());
  }
}
