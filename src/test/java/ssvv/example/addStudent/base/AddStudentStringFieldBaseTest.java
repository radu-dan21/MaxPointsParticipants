package ssvv.example.addStudent.base;

import org.junit.Ignore;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@Ignore("Base class for concrete addStudent test classes related to String fields")
public abstract class AddStudentStringFieldBaseTest extends AddStudentBaseTest {
    public AddStudentStringFieldBaseTest(boolean isValid) {
        super(isValid);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> groupAndIsValidFlag() {
        return Arrays.asList(new Object[][] {
                {" ", true},
                {"valid_field_value", true},
                {"1234", true},
                {null, false},
                {"", false}
        });
    }
}
