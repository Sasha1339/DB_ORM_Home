package ru.mpei.example.repositories;

import ru.mpei.example.models.Group;
import ru.mpei.example.models.Student;

import java.util.List;

public interface StudentRepository {
    public List<Student> getAll();
    public String shomMeGroupAndId();

    public List<Group> getAllToGroup();


    public String getAverageGrade(int group_id);
}
