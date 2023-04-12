package ssvv.example.integration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ssvv.example.domain.Nota;
import ssvv.example.domain.Student;
import ssvv.example.domain.Tema;
import ssvv.example.service.Service;
import ssvv.example.utils.XMLRepoServiceOperations;
import ssvv.example.validation.ValidationException;

import java.time.LocalDate;

public class AddIntegrationTest {
    private static final Student validStudent = new Student("idStudent", "nume", 50, "email@email.com");
    private static final Tema validAssignment = new Tema("nrTema", "descriere", 7, 3);
    private static final Nota validGrade = new Nota("idNota", "idStudent", "nrTema", 9.50, LocalDate.of(2023, 4, 12));

    protected Service service = XMLRepoServiceOperations.getService();

    @Before
    public void setup() {
        XMLRepoServiceOperations.reInitObjects();
        service = XMLRepoServiceOperations.getService();
    }

    @Test
    public void testAddStudent() {
        Student student = validStudent;
        Assert.assertNull(
                "Can't add student <" + student + "> to repository!", service.addStudent(student)
        );
        Assert.assertNotNull(
                "Can't delete student <" + student + "> from repository!",
                service.deleteStudent(student.getID())
        );
    }

    @Test
    public void testAddGrade() {
        Nota grade = validGrade;
        try {
            service.addNota(grade, "feedback");
            Assert.fail("Grade <" + grade + "> should not be added to repository without student/assignment!");
        } catch (ValidationException | NullPointerException ignored) {}
    }

    @Test
    public void testAddAssignment() {
        Tema assignment = validAssignment;
        Assert.assertNull(
                "Can't add assignment <" + assignment + "> to repository!", service.addTema(assignment)
        );
        Assert.assertNotNull(
                "Can't delete assignment <" + assignment + "> from repository!",
                service.deleteTema(assignment.getID())
        );
    }

    @Test
    public void testAddEntities() {
        Student student = validStudent;
        Tema assignment = validAssignment;
        Nota grade = validGrade;

        Assert.assertNull(
                "Can't add assignment <" + assignment + "> to repository!", service.addTema(assignment)
        );

        Assert.assertNull(
                "Can't add student <" + student + "> to repository!", service.addStudent(student)
        );


        XMLRepoServiceOperations.createTestFile("fisiere/" + student.getNume() + ".txt");
        Assert.assertEquals(
                "Can't add grade <" + grade + "> to repository!",
        9.5,
                service.addNota(grade, "feedback"),
                0.001
        );
    }
}
