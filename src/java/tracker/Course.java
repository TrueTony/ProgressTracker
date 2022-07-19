package tracker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Course {

    private final String name;
    private final int maxPoints;
    private List<Student> studentsList = new ArrayList<>();
    private final Enum<CoursesName> coursesNameEnum;

    public Course(String name, int maxPoints, Enum<CoursesName> coursesNameEnum) {
        this.name = name;
        this.maxPoints = maxPoints;
        this.coursesNameEnum = coursesNameEnum;
    }

    public String getName() {
        return name;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void addStudent(Student student) {
        studentsList.add(student);
    }

    public Enum<CoursesName> getCoursesNameEnum() {
        return coursesNameEnum;
    }

    public void showCourseStatistic() {
        double percentOfMax;
        List<Student> studentOfCourse = new ArrayList<>();

        System.out.printf("%s\n", name);
        System.out.println("id    points    completed");
        for (Student s : studentsList) {
            if (s.getPointsSum(coursesNameEnum) > 0) {
                studentOfCourse.add(s);
            }
        }

        studentOfCourse.sort(Comparator.comparing((Student s) -> s.getPointsSum(coursesNameEnum)).reversed().thenComparing(Student::getId));

        for (Student s : studentOfCourse) {
            percentOfMax = (s.getPointsSum(coursesNameEnum) / (double) maxPoints) * 100;
            System.out.printf("%5d %6d    %8.1f%%\n", s.getId(), s.getPointsSum(coursesNameEnum), percentOfMax);
        }
    }

    // Find out which courses are the most and least popular ones. The most popular has the biggest number of enrolled students;
    public int countOfStudent() {
        return (int) studentsList.stream().filter(s -> s.getPointsSum(coursesNameEnum) > 0).count();
    }

    // Find out which course has the highest and lowest student activity. Higher student activity means a bigger number of completed tasks;
    public int studentActivity() {
        return studentsList.stream().mapToInt(s -> s.getProjectsSize(coursesNameEnum)).sum();
    }

    // Establish the easiest and hardest course. The easiest course has the highest average grade per assignment;
    public double averageGrade() {
        int countOfTasks = studentActivity();
        if (countOfTasks == 0) {
            return 0;
        } else {
            int sumOfTasks = studentsList.stream().mapToInt(s -> s.getPointsSum(coursesNameEnum)).sum();
            return (double) sumOfTasks / countOfTasks;
        }
    }
}