package ee.ut.math.tvt.salessystem.ui;

import ee.ut.math.tvt.salessystem.dao.SalesSystemDAO;
import ee.ut.math.tvt.salessystem.ui.commands.implementations.NewWarehouseItemCommand;
import org.junit.Test;


import static org.mockito.Mockito.*;

public class ConsoleUITest {

    @Test
    public void testAddingItemBeginsAndCommitsTransaction() {
        SalesSystemDAO dao = mock(SalesSystemDAO.class);
        NewWarehouseItemCommand command = new NewWarehouseItemCommand(dao);
        command.createNewInWarehouse(10L, 10, 10.0, "Test item");
        verify(dao, times(1)).beginTransaction();
        verify(dao, times(1)).commitTransaction();
    }

}