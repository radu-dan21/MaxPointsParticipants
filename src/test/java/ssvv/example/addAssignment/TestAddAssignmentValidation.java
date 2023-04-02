package ssvv.example.addAssignment;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ssvv.example.addAssignment.base.BaseTestAddAssignment;
import ssvv.example.domain.Tema;
import ssvv.example.validation.ValidationException;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestAddAssignmentValidation extends BaseTestAddAssignment {
    private final Tema assignment;
    private final boolean isValid;

    public TestAddAssignmentValidation(Tema assignment, boolean isValid) {
        this.assignment = assignment;
        this.isValid = isValid;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> assignmentAndIsValidFlag() {
        return Arrays.asList(new Object[][] {
                {validAssignment, true},

                {new Tema(" ", "descriere", 13, 2), true},

                {new Tema("nr", " ", 13, 2), true},

                {new Tema("nr", "descriere", 1, 1), true},
                {new Tema("nr", "descriere", 14, 1), true},
                {new Tema("nr", "descriere", 14, 14), true},

                {new Tema("", "descriere", 13, 2), false},     // empty nrTema
                {new Tema(null, "descriere", 13, 2), false},   // null nrTema

                {new Tema("nr", "", 13, 2), false},            // empty description
                {new Tema("nr", null, 13, 2), false},          // null description

                {new Tema("nr", "descriere", 15, 2), false},   // deadline > 14
                {new Tema("nr", "descriere", 14, 15), false},  // primire > 14

                {new Tema("nr", "descriere", 0, 1), false},    // deadline < 1
                {new Tema("nr", "descriere", 1, 0), false},    // primire < 1

                {new Tema("nr", "descriere", 6, 7), false},    // primire > deadline
        });
    }

    @Test
    public void testAddAssignment() {
        assertExpectedAssignmentCount(0);
        if (isValid) {
            Assert.assertNull(service.addTema(assignment));
            assertCanFindAssignment(assignment);
            assertExpectedAssignmentCount(1);
        } else {
            Assert.assertThrows(ValidationException.class, () -> service.addTema(assignment));
            assertExpectedAssignmentCount(0);
        }
    }
}
