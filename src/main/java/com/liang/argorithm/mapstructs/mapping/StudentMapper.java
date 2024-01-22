package com.liang.argorithm.mapstructs.mapping;

import com.liang.argorithm.mapstructs.Course;
import com.liang.argorithm.mapstructs.GenderEnum;
import com.liang.argorithm.mapstructs.Student;
import com.liang.argorithm.mapstructs.StudentVO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author liangbingtian
 * @date 2023/11/02 上午9:38
 */
@Mapper
public interface StudentMapper {

  StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

  /**
   * student->studentVO
   *
   * @param student
   * @return
   */
  @Mapping(source = "gender", target = "gender")
  @Mapping(source = "birthday", target = "birthday", dateFormat = "yyyy-MM-dd HH:mm:ss")
  StudentVO studentToStudentVO(Student student);

  /**
   * students->studentVos
   * @param studentList
   * @return
   */
  List<StudentVO> studentsToStudentVOs(List<Student> studentList);

  /**
   * student, course -> studentVo
   * @param student
   * @param course
   * @return
   */
  @Mapping(source = "student.gender.name", target = "gender")
  @Mapping(source = "student.birthday", target = "birthday", dateFormat = "yyyy-MM-dd HH:mm:ss")
  @Mapping(source = "course.courseName", target = "course")
  @Mapping(source = "student.name", target = "name", defaultValue = "张三")
  StudentVO studentAndCourseToStudentVO(Student student, Course course);

  /**
   * 获取名称
   *
   * @param gender
   * @return
   */
  default String getGenderName(GenderEnum gender) {
    return gender.getName();
  }
}
