package ssvv.example.addStudent;

import ssvv.example.addStudent.base.AddStudentStringFieldBaseTest;
import ssvv.example.domain.Student;

public class AddStudentEmailTest extends AddStudentStringFieldBaseTest {
    private final String email;

    public AddStudentEmailTest(String email, boolean isValid) {
        super(isValid);
        this.email = email;
    }

    public Student getStudent() {
        return new Student("valid_id", "valid_name", 50, email);
    }
}
