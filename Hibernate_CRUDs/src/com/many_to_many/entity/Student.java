package com.many_to_many.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name="course_student",
            joinColumns = @JoinColumn(name="student_id"),
            inverseJoinColumns=@JoinColumn(name="course_id")
    )
    private List<Course> courses;

    public Student() {}

    public Student(String x, String y, String z) {
            this.first_name = x;
            this.last_name = y;
            this.email = z;
        }

    public List<Course> getCourses() {
        return courses;
    }

    public int getId() {
        return id;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
