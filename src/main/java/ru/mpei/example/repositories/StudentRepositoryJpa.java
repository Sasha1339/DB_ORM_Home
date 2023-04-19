package ru.mpei.example.repositories;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.mpei.example.models.Course;
import ru.mpei.example.models.Grade;
import ru.mpei.example.models.Group;
import ru.mpei.example.models.Student;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepositoryJpa implements StudentRepository {
    @PersistenceContext
    private final EntityManager em;

    public StudentRepositoryJpa(EntityManager em){
        this.em = em;
    }

    @Override
    public List<Student> getAll(){
        TypedQuery<Student> query = em.createQuery("select s from Student s", Student.class);
    return query.getResultList();
    }
    @Override
    public List<Group> getAllToGroup(){
        TypedQuery<Group> query = em.createQuery("select s from Group s", Group.class);
        return query.getResultList();
    }
    @Override
    public String shomMeGroupAndId(){
        List<Group> groups = getAllToGroup();
        String result = "";
        for(Group group: groups){
            result += "Группа номер "+group.getName()+" имеет ID - "+group.getId()+"\n";
        }
        return  result;
    }
    public List<Course> getAllCourse() {
        TypedQuery<Course> query = em.createQuery("select s from Course s", Course.class);
        return query.getResultList();
    }
    public List<Grade> getAllToGrade() {
        TypedQuery<Grade> query = em.createQuery("select s from Grade s", Grade.class);
        return query.getResultList();
    }
    public List<List> getListAverageGrade(int group_id){
        List<Student> students = getAll();
        List<Student> stugentAnyList = students;
        List<Course> courses = getAllCourse();
        List<List> result = new ArrayList<>();
        boolean flag ;
        boolean numberCourse = true;
        String nameCourse = "";
        int gradeSum = 0;
        int gradeCount = 0;
       for(Course course: courses){
           for(Student student: students){
               if (student.getGroup().getId() == group_id){
               for(Course c: student.getCourse()){
                   if (course.getName() == c.getName()){
                       gradeSum += student.getGrade().get(student.getCourse().indexOf(c)).getGrade();
                       gradeCount++;
                       }
                   }
               }

           }
           if (gradeCount > 0) {
               result.add(List.of(course.getName(), ((float)gradeSum)/((float) gradeCount)));
           }
           gradeSum = 0;
           gradeCount = 0;

       }

       return result;
    }
    @Override
    public String getAverageGrade(int group_id){
        List<List> info = getListAverageGrade(group_id);
        List<Group> group = getAllToGroup();
        String result = "";
        for(List i: info){
            result = result + "По курсу "+String.valueOf(i.get(0))+" в группе "+String.valueOf(group.get(group_id-1).getName())
                    +" средний бал равен - "+ String.valueOf(i.get(1))+"\n";
        }
        return result;
    }


}
