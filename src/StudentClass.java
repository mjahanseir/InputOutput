import java.io.Serializable;
import java.util.ArrayList;

public class StudentClass implements Serializable {

    private final int studentId;
    private final String program;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final ArrayList<String> course;
    private Boolean isSaved = false;

    public StudentClass(int studentId, String program, String firstName, String lastName, String email) {
        this.studentId = studentId;
        this.program = program;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.course = new ArrayList<>();
    }

    public StudentClass(int studentId, String program, String firstName, String lastName, String email, ArrayList<String> course) {
        this.studentId = studentId;
        this.program = program;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.course = course;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getProgram() {
        return program;
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

    public ArrayList<String> getCourse() {
        return course;
    }

    public Boolean getSaved() {
        return isSaved;
    }

    public void setSaved(Boolean saved) {
        isSaved = saved;
    }

    @Override
    public String toString() {
        return studentId + " " + program + " " + firstName + " " + lastName + " " + email + " " + course + "\n";
    }
}
