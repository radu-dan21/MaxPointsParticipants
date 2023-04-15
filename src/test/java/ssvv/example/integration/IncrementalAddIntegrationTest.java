package ssvv.example.integration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import ssvv.example.repository.NotaXMLRepo;
import ssvv.example.repository.StudentXMLRepo;
import ssvv.example.repository.TemaXMLRepo;
import ssvv.example.service.Service;
import ssvv.example.validation.NotaValidator;
import ssvv.example.validation.StudentValidator;
import ssvv.example.validation.TemaValidator;

import static org.mockito.Mockito.*;

public class IncrementalAddIntegrationTest extends BaseIntegrationTest {
    @Mock
    private static StudentXMLRepo studentXMLRepoMock = mock(StudentXMLRepo.class);
    @Mock
    private static TemaXMLRepo assignmentXMLRepoMock = mock(TemaXMLRepo.class);
    @Mock
    private static NotaXMLRepo gradeXMLRepoMock = mock(NotaXMLRepo.class);

    private static final StudentValidator studentValidator = new StudentValidator();
    private static final TemaValidator assignmentValidator = new TemaValidator();
    private static final NotaValidator gradeValidator = new NotaValidator(studentXMLRepoMock, assignmentXMLRepoMock);

    private static final Service service = new Service(studentXMLRepoMock, studentValidator, assignmentXMLRepoMock, assignmentValidator, gradeXMLRepoMock, gradeValidator);

    @Before
    public void setup() {
        reset(studentXMLRepoMock, assignmentXMLRepoMock, gradeXMLRepoMock);
    }

    @Test
    public void testAddStudent() {
        Assert.assertNull(service.addStudent(validStudent));
        verify(studentXMLRepoMock).save(validStudent);
    }

    @Test
    public void testAddStudentAndAssignment() {
        Assert.assertNull(service.addStudent(validStudent));
        Assert.assertNull(service.addTema(validAssignment));
        verify(studentXMLRepoMock).save(validStudent);
        verify(assignmentXMLRepoMock).save(validAssignment);
    }

    @Test
    public void testAddStudentAndAssignmentAndGrade() {
        Assert.assertNull(service.addStudent(validStudent));
        Assert.assertNull(service.addTema(validAssignment));
        verify(studentXMLRepoMock).save(validStudent);
        verify(assignmentXMLRepoMock).save(validAssignment);

        when(studentXMLRepoMock.findOne(validStudent.getID())).thenReturn(validStudent);
        when(assignmentXMLRepoMock.findOne(validAssignment.getID())).thenReturn(validAssignment);

        createTestFileForStudentGradeFile(validStudent);
        Assert.assertEquals(9.5, service.addNota(validGrade, "feedback"), 0.001);
        verify(gradeXMLRepoMock).save(validGrade);
    }
}
