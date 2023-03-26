package ssvv.example.addStudent.base;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ssvv.example.domain.Student;
import ssvv.example.service.Service;
import ssvv.example.utils.XMLRepoServiceOperations;
import ssvv.example.validation.ValidationException;

import java.util.Collection;
import java.util.Collections;

@RunWith(Parameterized.class)
@Ignore("Base class for concrete addStudent test classes")
public abstract class AddStudentBaseTest {
    protected final boolean isValid;

    protected static final Service service = XMLRepoServiceOperations.getService();

    public AddStudentBaseTest(boolean isValid) {
        this.isValid = isValid;
    }

    @BeforeClass
    public static void setup() {
        XMLRepoServiceOperations.reInitRepos();
    }

    // Re-define this in base classes for parametrization
    @Parameterized.Parameters
    public static Collection<Object[]> groupAndIsValidFlag() {
        return Collections.emptyList();
    }

    @Test
    public void testAddStudent() {
        Student student = getStudent();
        if (isValid) {
            Assert.assertNull(
                    "Can't add student <" + student + "> to repository!", service.addStudent(student)
            );
            Assert.assertNotNull(
                    "Can't delete student <" + student + "> from repository!",
                    service.deleteStudent(student.getID())
            );
        } else {
            try {
                service.addStudent(student);
                Assert.fail("Invalid student <" + student + "> could be added to repository!");
            } catch (ValidationException | NullPointerException ignored) {}
        }
    }

    protected abstract Student getStudent();
}
