package ssvv.example.addStudent;

import ssvv.example.addStudent.base.AddStudentStringFieldBaseTest;
import ssvv.example.domain.Student;

public class AddStudentIDTest extends AddStudentStringFieldBaseTest {
    private final String id;

    public AddStudentIDTest(String id, boolean isValid) {
        super(isValid);
        this.id = id;
    }

    public Student getStudent() {
        return new Student(id, "valid_name", 50, "valid@email.com");
    }
}
