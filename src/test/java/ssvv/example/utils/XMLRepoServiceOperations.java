package ssvv.example.utils;

import ssvv.example.repository.NotaXMLRepo;
import ssvv.example.repository.StudentXMLRepo;
import ssvv.example.repository.TemaXMLRepo;
import ssvv.example.service.Service;
import ssvv.example.validation.NotaValidator;
import ssvv.example.validation.StudentValidator;
import ssvv.example.validation.TemaValidator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class XMLRepoServiceOperations {
    private static final String gradesRepoFilePath = "fisiere/Note_test.xml";
    private static final String assignmentsRepoFilePath = "fisiere/Teme_test.xml";
    private static final String studentsRepoFilePath = "fisiere/Studenti_test.xml";

    private static List<File> files;

    private static StudentXMLRepo studentRepo;
    private static TemaXMLRepo assignmentRepo;
    private static NotaXMLRepo gradesRepo;

    private static Service service;

    public static Service getService() {
        initIfNull(service);
        return service;
    }

    public static StudentXMLRepo getStudentRepo() {
        initIfNull(studentRepo);
        return studentRepo;
    }

    public static TemaXMLRepo getAssignmentRepo() {
        initIfNull(assignmentRepo);
        return assignmentRepo;
    }

    public static NotaXMLRepo getGradesRepo() {
        initIfNull(gradesRepo);
        return gradesRepo;
    }

    private static void initIfNull(Object o) {
        if (o == null) {
            createTestRepoFiles();
            reInitObjects();
        }
    }

    private static void createTestRepoFiles() {
        files = new ArrayList<>();
        files.add(new File(studentsRepoFilePath));
        files.add(new File(assignmentsRepoFilePath));
        files.add(new File(gradesRepoFilePath));

        boolean allFilesCreated = true;

        for (File f: files) {
            try {
                allFilesCreated &= f.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            f.deleteOnExit();
        }

        if (!allFilesCreated) {
            throw new RuntimeException("Could not create all XMLRepository files");
        }
    }

    public static void reInitObjects() {
        deleteRepoFilesContent();
        initRepos();
        initService();
    }

    private static void deleteRepoFilesContent() {
        PrintWriter writer;
        for (File f: files) {
            try {
                writer = new PrintWriter(f);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><inbox/>");
            writer.close();
        }
    }

    private static void initRepos() {
        studentRepo = new StudentXMLRepo(studentsRepoFilePath);
        assignmentRepo = new TemaXMLRepo(assignmentsRepoFilePath);
        gradesRepo = new NotaXMLRepo(gradesRepoFilePath);
    }

    private static void initService() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        NotaValidator notaValidator = new NotaValidator(studentRepo, assignmentRepo);
        service = new Service(studentRepo, studentValidator, assignmentRepo, temaValidator, gradesRepo, notaValidator);
    }
}
