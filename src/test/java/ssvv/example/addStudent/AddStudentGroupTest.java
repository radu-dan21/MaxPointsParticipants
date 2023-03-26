package ssvv.example.addStudent;

import org.junit.runners.Parameterized;
import ssvv.example.addStudent.base.AddStudentBaseTest;
import ssvv.example.domain.Student;

import java.util.Arrays;
import java.util.Collection;


public class AddStudentGroupTest extends AddStudentBaseTest {
    private final int group;

    public AddStudentGroupTest(int group, boolean isValid) {
        super(isValid);
        this.group = group;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> groupAndIsValidFlag() {
        return Arrays.asList(new Object[][] {
                {200, true},
                {1, true},
                {0, true},
                {-1, false},
                {-50, false}
        });
    }

    public Student getStudent() {
        return new Student("valid_id", "valid_name", group, "valid@email.com");
    }
}
