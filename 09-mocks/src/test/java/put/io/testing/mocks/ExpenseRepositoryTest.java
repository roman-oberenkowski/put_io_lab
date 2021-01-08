package put.io.testing.mocks;

import org.junit.jupiter.api.*;
import org.mockito.*;
import put.io.students.fancylibrary.database.IFancyDatabase;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ExpenseRepositoryTest {
    private static ExpenseRepository er;
    private static IFancyDatabase md;

    @BeforeEach
    public void setup() {
        md = mock(IFancyDatabase.class);
        er = new ExpenseRepository(md);
    }

    @Test
    public void loadExpensesTest() {
        when(md.queryAll()).thenReturn(Collections.emptyList());
        InOrder in = inOrder(md);
        er.loadExpenses();
        assertTrue(er.getExpenses().isEmpty());
        in.verify(md).connect();
        in.verify(md).queryAll();
        in.verify(md).close();
    }

    @Test
    public void saveExpensesTest() {
        for (int i = 0; i < 5; i++) {
            er.addExpense(new Expense());
        }
        er.saveExpenses();
        //verify(md).persist(new Expense());
        verify(md, times(5)).persist(argThat(exp -> exp.getClass() == Expense.class));
    }

}
