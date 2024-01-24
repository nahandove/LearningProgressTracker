package tracker;

import tracker.course.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private final int id;
    private final String name;
    private final String email;
    private List<Course> courses;

    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        courses = List.of(new JavaCourse(0, 0), new DSACourse(0, 0),
                new DatabasesCourse(0, 0), new SpringCourse(0, 0));
    }

    public Course getCourseByName(String name) {
        if ("Java".equals(name)) {
            return courses.get(0);
        }
        if ("DSA".equals(name)) {
            return courses.get(1);
        }
        if ("Databases".equals(name)) {
            return courses.get(2);
        }
        if ("Spring".equals(name)) {
            return courses.get(3);
        }
        throw new IllegalArgumentException("Unknown course");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
