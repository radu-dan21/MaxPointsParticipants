package ssvv.example.addStudent;

import ssvv.example.addStudent.base.AddStudentStringFieldBaseTest;
import ssvv.example.domain.Student;

public class AddStudentNameTest extends AddStudentStringFieldBaseTest {
    private final String name;

    public AddStudentNameTest(String name, boolean isValid) {
        super(isValid);
        this.name = name;
    }

    public Student getStudent() {
        return new Student("valid_id", name, 50, "valid@email.com");
    }
}
