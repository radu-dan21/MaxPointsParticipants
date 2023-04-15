package ssvv.example.integration;

import ssvv.example.domain.Nota;
import ssvv.example.domain.Student;
import ssvv.example.domain.Tema;
import ssvv.example.utils.XMLRepoServiceOperations;

import java.time.LocalDate;

public abstract class BaseIntegrationTest {
    protected static final Student validStudent = new Student(
            "idStudent", "nume", 50, "email@email.com"
    );
    protected static final Tema validAssignment = new Tema(
            "nrTema", "descriere", 7, 3
    );
    protected static final Nota validGrade = new Nota(
            "idNota", "idStudent", "nrTema", 9.50, LocalDate.of(2023, 4, 12)
    );

    protected static void createTestFileForStudentGradeFile(Student student) {
        XMLRepoServiceOperations.createTestFile("fisiere/" + student.getNume() + ".txt");
    }
}
