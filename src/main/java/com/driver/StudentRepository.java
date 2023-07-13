package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String, Student> studentHashMap = new HashMap<>(); //Student Name -> Student
    HashMap<String, Teacher> teacherHashMap = new HashMap<>(); //Teacher Name -> Teacher
    HashMap<String, List<String>> teacherMap = new HashMap<>(); //Teacher Name -> List<Students>
    public Student addStudent(Student student) {
        String name = student.getName();
        studentHashMap.put(name, student);
        return student;
    }

    public Teacher addTeacher(Teacher teacher) {
        String name = teacher.getName();
        teacherHashMap.put(name, teacher);
        return teacher;
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if (teacherMap.containsKey(teacher)) {
            List<String> studentList = teacherMap.get(teacher);
            studentList.add(student);
            teacherMap.put(teacher, studentList);
        }else {
            List<String> studentList = new ArrayList<>();
            studentList.add(student);
            teacherMap.put(teacher, studentList);
        }
    }

    public Student getStudentByName(String name) {
        if (studentHashMap.containsKey(name)) {
            return studentHashMap.get(name);
        }
        return null;
    }

    public Teacher getTeacherByName(String name) {
        if (teacherHashMap.containsKey(name)) {
            return teacherHashMap.get(name);
        }
        return null;
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        List<String> studentList = new ArrayList<>();
        if (teacherMap.containsKey(teacher)) {
            studentList = teacherMap.get(teacher);
        }
        return studentList;
    }

    public List<String> getAllStudents() {
        List<String> studentList = new ArrayList<>();
        for (String student : studentHashMap.keySet()) {
            studentList.add(student);
        }
        return studentList;
    }

    public void deleteTeacherByName(String teacher) {
        teacherHashMap.remove(teacher);
        List<String> students = teacherMap.get(teacher);
        teacherMap.remove(teacher);
        for (String student : students) {
            studentHashMap.remove(student);
        }
    }

    public void deleteAllTeachers() {
        for (String teacher : teacherMap.keySet()) {
            List<String> students = teacherMap.get(teacher);

            teacherHashMap.remove(teacher);
            teacherMap.remove(teacher);
            for (String student : students) {
                studentHashMap.remove(student);
            }
        }
    }
}
