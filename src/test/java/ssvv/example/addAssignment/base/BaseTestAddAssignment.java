package ssvv.example.addAssignment.base;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import ssvv.example.domain.Tema;
import ssvv.example.service.Service;
import ssvv.example.utils.XMLRepoServiceOperations;

import java.util.Collection;

@Ignore("Base class for concrete addAssignment test classes")
public class BaseTestAddAssignment {
    protected Service service = XMLRepoServiceOperations.getService();

    protected static final Tema validAssignment = new Tema("nrTema", "descriereTema", 10, 1);

    @Before
    public void setup() {
        XMLRepoServiceOperations.reInitObjects();
        service = XMLRepoServiceOperations.getService();
    }

    protected void assertExpectedAssignmentCount(int expectedCount) {
        Assert.assertEquals(((Collection<?>) service.getAllTeme()).size(), expectedCount);
    }

    protected void assertCanFindAssignment(Tema assignment) {
        Assert.assertEquals(service.findTema(assignment.getID()), assignment);
    }
}
