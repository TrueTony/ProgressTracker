package tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final List<Integer> javaPoints = new ArrayList<>();
    private final List<Integer> dsaPoints = new ArrayList<>();
    private final List<Integer> databasePoints = new ArrayList<>();
    private final List<Integer> springPoints = new ArrayList<>();
    Map<Enum<CoursesName>, Boolean> courseGraduated = new HashMap<>() {{
        put(CoursesName.JAVA, false);
        put(CoursesName.DSA, false);
        put(CoursesName.DATABASES, false);
        put(CoursesName.SPRING, false);
    }};

    public Student (int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getJavaPoints() {
        return javaPoints;
    }

    public List<Integer> getDsaPoints() {
        return dsaPoints;
    }

    public List<Integer> getDatabasePoints() {
        return databasePoints;
    }

    public List<Integer> getSpringPoints() {
        return springPoints;
    }

    public Boolean getCourseGraduated(Enum<CoursesName> coursesName) {
        return courseGraduated.get(coursesName);
    }

    public void setCourseGraduated(Enum<CoursesName> coursesName, Boolean value) {
        this.courseGraduated.put(coursesName, value);
    }

    private List<Integer> getCourse(Enum<CoursesName> coursesName) {
        List<Integer> course = null;

        try {
            if (coursesName == CoursesName.JAVA) {
                course = getJavaPoints();
            } else if (coursesName == CoursesName.DSA) {
                course = getDsaPoints();
            } else if (coursesName == CoursesName.DATABASES) {
                course = getDatabasePoints();
            } else if (coursesName == CoursesName.SPRING) {
                course = getSpringPoints();
            } else {
                throw new Exception("Wrong course name!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return course;
    }

    public int getPointsSum(Enum<CoursesName> coursesName) {
        return getCourse(coursesName).stream().mapToInt(Integer::intValue).sum();
    }

    public int getProjectsSize(Enum<CoursesName> coursesName) {
        return getCourse(coursesName).size();
    }

    public void setCoursePoint(Enum<CoursesName> coursesNameEnum, int points) {
        getCourse(coursesNameEnum).add(points);
    }

}