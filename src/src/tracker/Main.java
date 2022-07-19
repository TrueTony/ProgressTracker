package tracker;

import java.util.*;

public class Main {

    private static int ID_COUNTER = 10000;
    private static int countOfStudent = 0;
    private static Map<Integer, Student> studentsMap = new LinkedHashMap<>();
    private static final CourseAgg courseAgg = new CourseAgg(new ArrayList<>() {
        {
            add(new Course("Java", 600, CoursesName.JAVA));
            add(new Course("DSA", 400, CoursesName.DSA));
            add(new Course("Databases", 480, CoursesName.DATABASES));
            add(new Course("Spring", 550, CoursesName.SPRING));
        }
    });

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Learning Progress Tracker");
        while (true) {
            input = scanner.nextLine().toLowerCase();
            switch (input.trim()) {
                case "exit":
                    System.out.println("Bye!");
                    break;
                case "":
                    System.out.println("No input.");
                    continue;
                case "add students":
                    addStudents(scanner);
                    continue;
                case "back":
                    System.out.println("Enter 'exit' to exit the program.");
                    continue;
                case "list":
                    listOfStudents();
                    continue;
                case "add points":
                    addPoints(scanner);
                    continue;
                case "find":
                    findStudent(scanner);
                    continue;
                case "statistics":
                    showStatistics(scanner);
                    continue;
                case "notify":
                    notifyStudent();
                    continue;
                default:
                    System.out.println("Error: unknown command!");
                    continue;
            }
            break;
        }
        scanner.close();
    }

    private static void notifyStudent() {
        Enum<CoursesName> coursesNameEnum;
        Set<Student> notifiedStudents = new HashSet<>();

        for (Student s :studentsMap.values()) {
            for (int i = 0; i < 4; i++) {
                coursesNameEnum = courseAgg.coursesList.get(i).getCoursesNameEnum();
                if (s.getPointsSum(coursesNameEnum) >= courseAgg.coursesList.get(i).getMaxPoints()
                    && !s.courseGraduated.get(coursesNameEnum)) {
                    notifyStudentMessage(s.getEmail(), s.getFirstName(), s.getLastName(),
                            courseAgg.coursesList.get(i).getName());
                    notifiedStudents.add(s);
                    s.setCourseGraduated(coursesNameEnum, true);
                }
            }
        }
        System.out.printf("Total %d students have been notified.\n", notifiedStudents.size());
    }

    private static void notifyStudentMessage(String email, String name, String lastName, String courseName) {
        System.out.printf("To: %s\n", email);
        System.out.println("Re: Your Learning Progress");
        System.out.printf("Hello, %s %s! You have accomplished our %s course!\n",
                name, lastName, courseName);
    }

    private static void showStatistics(Scanner scanner) {
        String course;

        System.out.println("Type the name of a course to see details or 'back' to quit:");
        courseAgg.showCourseAggStatistics();
        while (true) {
            course = scanner.nextLine().toLowerCase();
            switch (course) {
                case "back":
                    break;
                case "java":
                    courseAgg.coursesList.get(0).showCourseStatistic();
                    continue;
                case "dsa":
                    courseAgg.coursesList.get(1).showCourseStatistic();
                    continue;
                case "databases":
                    courseAgg.coursesList.get(2).showCourseStatistic();
                    continue;
                case "spring":
                    courseAgg.coursesList.get(3).showCourseStatistic();
                    continue;
                default:
                    System.out.println("Unknown course.");
                    continue;
            }
            break;
        }
    }

    private static void findStudent(Scanner scanner) {
        String input;

        System.out.println("Enter an id or 'back' to return");
        while (true) {
            input = scanner.nextLine();
            if ("back".equals(input)) {
                break;
            }
            if (isExistingStudent(input)) {
                Student s = studentsMap.get(Integer.parseInt(input));
                System.out.printf("%d points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n", s.getId(),
                        s.getPointsSum(CoursesName.JAVA), s.getPointsSum(CoursesName.DSA),
                        s.getPointsSum(CoursesName.DATABASES), s.getPointsSum(CoursesName.SPRING));
            }
        }
    }

    private static void addPoints(Scanner scanner) {
        String input;
        String[] params;

        System.out.println("Enter an id and points or 'back' to return");
        while (true) {
            input = scanner.nextLine();
            if ("back".equals(input)) {
                break;
            }
            params = input.split(" ");
            if (isValidPoints(input) && isExistingStudent(params[0])) {
                Student student = studentsMap.get(Integer.parseInt(params[0]));
                if (Integer.parseInt(params[1]) > 0) {
                    student.setCoursePoint(CoursesName.JAVA, Integer.parseInt(params[1]));
                }
                if (Integer.parseInt(params[2]) > 0) {
                    student.setCoursePoint(CoursesName.DSA, Integer.parseInt(params[2]));
                }
                if (Integer.parseInt(params[3]) > 0) {
                    student.setCoursePoint(CoursesName.DATABASES, Integer.parseInt(params[3]));
                }
                if (Integer.parseInt(params[4]) > 0) {
                    student.setCoursePoint(CoursesName.SPRING, Integer.parseInt(params[4]));
                }
                System.out.println("Points updated.");
            }
        }
    }

    static boolean isValidPoints(String params) {
        if (!params.matches("\\w+ \\d+ \\d+ \\d+ \\d+")) {
            System.out.println("Incorrect points format");
            return false;
        }
        return true;
    }

    private static boolean isExistingStudent(String student) {
        if (!student.matches("\\d+")) {
            System.out.printf("No student is found for id=%s\n", student);
            return false;
        }
        if (studentsMap.containsKey(Integer.parseInt(student))) {
            return true;
        } else {
            System.out.printf("No student is found for id=%s\n", student);
            return false;
        }
    }

    private static void listOfStudents() {
        if (studentsMap.isEmpty()) {
            System.out.println("No students found");
        } else {
            System.out.println("Students:");
            for (Integer id: studentsMap.keySet()) {
                System.out.println(id);
            }
        }
    }

    private static void addStudents(Scanner scanner) {
        String input;

        countOfStudent = 0;
        System.out.println("Enter student credentials or 'back' to return:");
        while (true) {
            input = scanner.nextLine();
            if ("back".equals(input)) {
                System.out.printf("Total %d students have been added.\n", countOfStudent);
                break;
            } else {
                addOneStudent(input);
            }
        }
    }

    private static void addOneStudent(String input) {
        String[] credentials = input.split(" ");

        if (credentials.length < 3) {
            System.out.println("Incorrect credentials.");
        } else if (!verifyFirstName(credentials[0])) {
            System.out.println("Incorrect first name.");
        } else if (!verifySecondName(Arrays.copyOfRange(credentials, 1, credentials.length - 1))) {
            System.out.println("Incorrect last name.");
        } else if (!verifyEmail(credentials[credentials.length -1])) {
            System.out.println("Incorrect email.");
        } else {
            if (emailExist(credentials[credentials.length -1])) {
                System.out.println("This email is already taken.");
            } else {
                int id = ID_COUNTER++;
                Student student = new Student(id, credentials[0],
                        String.join(" ",
                                Arrays.copyOfRange(credentials, 1, credentials.length - 1)),
                        credentials[credentials.length -1]);
                studentsMap.put(id,student);
                courseAgg.coursesList.get(0).addStudent(student);
                courseAgg.coursesList.get(1).addStudent(student);
                courseAgg.coursesList.get(2).addStudent(student);
                courseAgg.coursesList.get(3).addStudent(student);
                countOfStudent++;
                System.out.println("The student has been added.");
            }
        }
    }

    private static boolean emailExist(String email) {
        for (Map.Entry<Integer, Student> entry: studentsMap.entrySet()) {
            if (entry.getValue().getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    static boolean verifyFirstName(String name) {
        return name.matches("[A-z]+(['-]?[A-z]+)+");
    }

    private static boolean verifySecondName(String[] name) {
        for (String s : name) {
            if (!verifyFirstName(s)) {
                return false;
            }
        }
        return true;
    }

    static boolean verifyEmail(String email) {
        return email.matches("[A-z.0-9]+@\\w+[\\.]\\w+");
    }
}