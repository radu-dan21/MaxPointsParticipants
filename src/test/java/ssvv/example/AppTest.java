package ssvv.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ssvv.example.domain.Student;
import ssvv.example.repository.NotaXMLRepo;
import ssvv.example.repository.StudentXMLRepo;
import ssvv.example.repository.TemaXMLRepo;
import ssvv.example.service.Service;
import ssvv.example.validation.NotaValidator;
import ssvv.example.validation.StudentValidator;
import ssvv.example.validation.TemaValidator;
import ssvv.example.validation.ValidationException;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    private Service initService() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        return new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    private Student getStudent(Integer group) {
        return new Student("valid_id", "valid_name", group, "valid@email.com");
    }

    public void testAddStudentStrictlyPositiveGroup() {
        Service service = initService();
        Student student = getStudent(200);
        assertNull(service.addStudent(student));
        assertNotNull(service.deleteStudent(student.getID()));
    }

    public void testAddStudentGroup0() {
        Service service = initService();
        Student student = getStudent(0);
        assertNull(service.addStudent(student));
        assertNotNull(service.deleteStudent(student.getID()));
    }

    public void testAddStudentStrictlyNegativeGroup() {
        Service service = initService();
        Student student = getStudent(-50);
        try {
            service.addStudent(student);
            assert false;
        } catch (ValidationException ignored) {}
    }

}
