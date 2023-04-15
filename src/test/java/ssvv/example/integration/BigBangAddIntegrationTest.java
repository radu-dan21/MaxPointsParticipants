package ssvv.example.integration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ssvv.example.service.Service;
import ssvv.example.utils.XMLRepoServiceOperations;
import ssvv.example.validation.ValidationException;

public class BigBangAddIntegrationTest extends BaseIntegrationTest {
    protected Service service = XMLRepoServiceOperations.getService();

    @Before
    public void setup() {
        XMLRepoServiceOperations.reInitObjects();
        service = XMLRepoServiceOperations.getService();
    }

    @Test
    public void testAddStudent() {
        Assert.assertNull(
                "Can't add student <" + validStudent + "> to repository!", service.addStudent(validStudent)
        );
        Assert.assertNotNull(
                "Can't delete student <" + validStudent + "> from repository!",
                service.deleteStudent(validStudent.getID())
        );
    }

    @Test
    public void testAddGrade() {
        try {
            service.addNota(validGrade, "feedback");
            Assert.fail("Grade <" + validGrade + "> should not be added to repository without student/assignment!");
        } catch (ValidationException | NullPointerException ignored) {}
    }

    @Test
    public void testAddAssignment() {
        Assert.assertNull(
                "Can't add assignment <" + validAssignment + "> to repository!",
                service.addTema(validAssignment)
        );
        Assert.assertNotNull(
                "Can't delete assignment <" + validAssignment + "> from repository!",
                service.deleteTema(validAssignment.getID())
        );
    }

    @Test
    public void testAddEntities() {
        Assert.assertNull(
                "Can't add assignment <" + validAssignment + "> to repository!",
                service.addTema(validAssignment)
        );

        Assert.assertNull(
                "Can't add student <" + validStudent + "> to repository!", service.addStudent(validStudent)
        );

        createTestFileForStudentGradeFile(validStudent);
        Assert.assertEquals(
                "Can't add grade <" + validGrade + "> to repository!",
        9.5,
                service.addNota(validGrade, "feedback"),
                0.001
        );
    }
}
