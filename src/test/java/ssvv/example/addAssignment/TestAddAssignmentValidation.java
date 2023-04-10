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
                {new Tema("", "desc", 13, 2), false},
                {new Tema("nr", "", 13, 2), false},
                {new Tema("nr", "desc", -1, 1), false},
                {new Tema("nr", "desc", 13, -1), false},
                {new Tema("nr", "desc", 10, 11), false},
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
