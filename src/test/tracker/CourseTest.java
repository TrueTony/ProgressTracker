package tracker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CourseTest {

    private static List<Student> studentsList;
    private static int TEST_ID_COUNTER = 10000;
    private static Course courseJava;
    private static Course courseDsa;
    private static Course courseDatabase;
    private static Course courseSpring;

    @BeforeAll
    static void setUp() {
        studentsList = new ArrayList<>();
        courseJava = new Course("Java", 600, CoursesName.JAVA);
        courseDsa = new Course("DSA", 400, CoursesName.DSA);
        courseDatabase = new Course("Databases", 480, CoursesName.DATABASES);
        courseSpring = new Course("Spring", 550, CoursesName.SPRING);
        Student s1 = new Student(TEST_ID_COUNTER++, "Tony",  "True", "tony@yabdex.ru");
        Student s2 = new Student(TEST_ID_COUNTER++, "Vanya",  "Wow", "Vanya@yabdex.ru");
        Student s3 = new Student(TEST_ID_COUNTER++, "Lizza",  "Kuss", "Kuss@yabdex.ru");
        Student s4 = new Student(TEST_ID_COUNTER++, "Pizza",  "Hot", "Pizza@yabdex.ru");
        s1.setCoursePoint(CoursesName.JAVA, 5);
        s1.setCoursePoint(CoursesName.JAVA, 10);
        s2.setCoursePoint(CoursesName.JAVA, 600);
        studentsList.addAll(Arrays.asList(s1, s2, s3, s4));
        for (Student s: studentsList) {
            courseJava.addStudent(s);
            courseDsa.addStudent(s);
            courseDatabase.addStudent(s);
            courseSpring.addStudent(s);
        }
    }

    @Test
    @DisplayName("Course.countOfStudent work correctly with 2 students")
    public void testCountOfStudentTwo() {
        assertEquals(2, courseJava.countOfStudent());
    }

    @Test
    @DisplayName("Course.countOfStudent work correctly with 0 student")
    public void testCountOfStudentZero() {
        assertEquals(0, courseDsa.countOfStudent());
    }

    @Test
    public void testStudentActivityEqualsThree() {
        assertEquals(3, courseJava.studentActivity());
    }

    @Test
    public void testStudentActivityEqualsZero() {
        assertEquals(0, courseDsa.studentActivity());
    }

    @Test
    public void testAverageGradeAboveZero() {
        assertEquals((double) 615 / 3, courseJava.averageGrade());
    }

    @Test
    public void testAverageGradeZero() {
        assertEquals(0, courseDsa.averageGrade());
    }
}
