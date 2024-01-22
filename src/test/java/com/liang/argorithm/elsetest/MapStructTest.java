package com.liang.argorithm.elsetest;

import com.liang.argorithm.mapstructs.Course;
import com.liang.argorithm.mapstructs.GenderEnum;
import com.liang.argorithm.mapstructs.Student;
import com.liang.argorithm.mapstructs.StudentVO;
import com.liang.argorithm.mapstructs.mapping.StudentMapper;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.Test;

/**
 * @author liangbingtian
 * @date 2023/11/02 上午9:46
 */
public class MapStructTest {

  @Test
  public void testTransform() {
    final Student build = Student.builder().age(6).birthday(new Date()).gender(GenderEnum.Female)
        .height(11d).name("123").build();
    final StudentVO studentVO = StudentMapper.INSTANCE.studentToStudentVO(build);
    final List<Student> students = Collections.singletonList(build);
    final List<StudentVO> studentVOS = StudentMapper.INSTANCE.studentsToStudentVOs(students);
    final Course course = Course.builder().courseName("语文").id(12L).sortNo(12).build();
    final StudentVO studentVO2 = StudentMapper.INSTANCE.studentAndCourseToStudentVO(build, course);
    System.out.println(studentVO2);
  }

}
