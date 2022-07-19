package tracker;

import java.util.ArrayList;
import java.util.List;

public class CourseAgg {

    List<Course> coursesList;

    public CourseAgg(List<Course> coursesList) {
        this.coursesList = coursesList;
    }

    public void showCourseAggStatistics() {
        if (isEmptyCourses()) {
            printAggStatistics("n/a", "n/a", "n/a", "n/a", "n/a", "n/a");
        } else {
            List<String> mostPop = findMostPopular();
            List<String> leastPop = findLeastPopular();
            List<String> mostAct = findMostActivity();
            List<String> leastAct = findLeastActivity();
            List<String> easiest = findEasiestCourse();
            List<String> hardest = findHardestCourse();

            leastPop.removeAll(mostPop);
            leastAct.removeAll(mostAct);
            hardest.removeAll(easiest);

            printAggStatistics(String.join(", ", mostPop), String.join(", ", leastPop),
                    String.join(", ", mostAct), String.join(", ", leastAct),
                    String.join(", ", easiest), String.join(", ", hardest));
        }
    }

    private void printAggStatistics(String mostPop, String leastPop, String mostAct, String leastAct,
                                    String easiest, String hardest) {

        System.out.printf("Most popular: %s\n" +
                        "Least popular: %s\n" +
                        "Highest activity: %s\n" +
                        "Lowest activity: %s\n" +
                        "Easiest course: %s\n" +
                        "Hardest course: %s\n",
                mostPop.length() == 0 ? "n/a" : mostPop, leastPop.length() == 0 ? "n/a" : leastPop,
                mostAct.length() == 0 ? "n/a" : mostAct, leastAct.length() == 0 ? "n/a" : leastAct,
                easiest.length() == 0 ? "n/a" : easiest, hardest.length() == 0 ? "n/a" : hardest);
    }

    private boolean isEmptyCourses() {
        for (int i = 0; i < 4; i++) {
            if (coursesList.get(i).countOfStudent() > 0) {
                return false;
            }
        }
        return true;
    }

    private List<String> findMostPopular() {
        int max = 0;
        int current;
        List<String> res = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            current = coursesList.get(i).countOfStudent();
            if (current > max) {
                max = current;
                res.clear();
                res.add(coursesList.get(i).getName());
            } else if (current == max) {
                res.add(coursesList.get(i).getName());
            }
        }
        return res;
    }

    private List<String> findLeastPopular() {
        int min = Integer.MAX_VALUE;
        int current;
        List<String> res = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            current = coursesList.get(i).countOfStudent();
            if (current < min) {
                min = current;
                res.clear();
                res.add(coursesList.get(i).getName());
            } else if (current == min) {
                res.add(coursesList.get(i).getName());
            }
        }
        return res;
    }

    private List<String> findMostActivity() {
        int max = 0;
        int current;
        List<String> res = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            current = coursesList.get(i).studentActivity();
            if (current > max) {
                max = current;
                res.clear();
                res.add(coursesList.get(i).getName());
            } else if (current == max) {
                res.add(coursesList.get(i).getName());
            }
        }
        return res;
    }

    private List<String> findLeastActivity() {
        int min = Integer.MAX_VALUE;
        int current;
        List<String> res = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            current = coursesList.get(i).studentActivity();
            if (current < min) {
                min = current;
                res.clear();
                res.add(coursesList.get(i).getName());
            } else if (current == min) {
                res.add(coursesList.get(i).getName());
            }
        }
        return res;
    }

    private List<String> findEasiestCourse() {
        double max = 0;
        double current;
        List<String> res = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            current = coursesList.get(i).averageGrade();
            if (current > max) {
                max = current;
                res.clear();
                res.add(coursesList.get(i).getName());
            } else if (current == max) {
                res.add(coursesList.get(i).getName());
            }
        }
        return res;
    }

    private List<String> findHardestCourse() {
        double min = Integer.MAX_VALUE;
        double current;
        List<String> res = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            current = coursesList.get(i).averageGrade();
            if (current < min) {
                min = current;
                res.clear();
                res.add(coursesList.get(i).getName());
            } else if (current == min) {
                res.add(coursesList.get(i).getName());
            }
        }
        return res;
    }
}