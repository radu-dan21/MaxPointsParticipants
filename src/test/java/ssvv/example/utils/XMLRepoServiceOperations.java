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
import java.util.List;

public class XMLRepoServiceOperations {
    private static final String gradesRepoFilePath = "fisiere/Note_test.xml";
    private static final String assignmentsRepoFilePath = "fisiere/Teme_test.xml";
    private static final String studentsRepoFilePath = "fisiere/Studenti_test.xml";

    private static List<File> files;

    private static final Service service = initService();

    private static void createTestRepoFiles() {
        files = List.of(
                new File(studentsRepoFilePath), new File(assignmentsRepoFilePath), new File(gradesRepoFilePath)
        );
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

    private static Service initService() {
        createTestRepoFiles();
        reInitRepos();

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(studentsRepoFilePath);

        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(assignmentsRepoFilePath);

        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(gradesRepoFilePath);

        return new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    public static Service getService() {
        return service;
    }

    public static void reInitRepos() {
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
}
