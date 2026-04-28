public class Dosen extends Person {
    private int numCourses = 0;
    private String[] courses;
    private static final int MAX_COURSES = 30;

    public Dosen(String name, String address) {
        super(name, address);
        courses = new String[MAX_COURSES];
    }

    @Override
    public String toString() {
        return "Dosen: " + super.toString();
    }

    public boolean addCourse(String course) {
        // Return false if the course already existed
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].equalsIgnoreCase(course)) {
                return false; 
            }
        }
        if (numCourses < MAX_COURSES) {
            courses[numCourses] = course;
            numCourses++;
            return true;
        }
        return false;
    }

    public boolean removeCourse(String course) {
        int courseIndex = -1;
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].equalsIgnoreCase(course)) {
                courseIndex = i;
                break;
            }
        }
        
        // Return false if the course does not exist
        if (courseIndex == -1) {
            return false; 
        }
        
        // Menggeser array untuk menghapus elemen
        for (int i = courseIndex; i < numCourses - 1; i++) {
            courses[i] = courses[i + 1];
        }
        courses[numCourses - 1] = null;
        numCourses--;
        return true;
    }
}