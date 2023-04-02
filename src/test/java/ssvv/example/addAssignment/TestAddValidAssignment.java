package ssvv.example.addAssignment;

import org.junit.Assert;
import org.junit.Test;
import ssvv.example.addAssignment.base.BaseTestAddAssignment;

public class TestAddValidAssignment extends BaseTestAddAssignment {
    @Test
    public void testAddAssignmentPasses() {
        assertExpectedAssignmentCount(0);
        Assert.assertNull(service.addTema(validAssignment));
        assertCanFindAssignment(validAssignment);

        assertExpectedAssignmentCount(1);
    }

    @Test
    public void testAddDuplicateAssignmentFails() {
        assertExpectedAssignmentCount(0);
        Assert.assertNull(service.addTema(validAssignment));
        assertCanFindAssignment(validAssignment);

        assertExpectedAssignmentCount(1);
        Assert.assertEquals(service.addTema(validAssignment), validAssignment);
        assertExpectedAssignmentCount(1);
    }
}
