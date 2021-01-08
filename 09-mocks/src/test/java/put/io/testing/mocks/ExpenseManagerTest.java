package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;
import put.io.students.fancylibrary.service.FancyService;

import java.lang.reflect.Executable;
import java.net.ConnectException;
import java.util.*;

public class ExpenseManagerTest {
    private static ExpenseRepository er;
    private static FancyService fs;

    @Test
    public void calculateTotalTest() {
        er = mock(ExpenseRepository.class);
        fs = mock(FancyService.class);
        List<Expense> elist = new LinkedList<Expense>();

        for (int i = 0; i < 3; i++) {
            Expense tempe = new Expense();
            tempe.setAmount(i * 2 + 1);
            elist.add(tempe);
        }
        when(er.getExpenses()).thenReturn(Collections.unmodifiableList(elist));
        ExpenseManager em = new ExpenseManager(er, fs);
        long sum = em.calculateTotal();
        assertEquals(sum, 9);
    }

    @Test
    public void calculateTotalForCategoryTest() {
        er = mock(ExpenseRepository.class);
        fs = mock(FancyService.class);
        List<Expense> elist = new LinkedList<Expense>();



        Expense tempe0 = new Expense();
        tempe0.setAmount(1);
        tempe0.setCategory("Home");
        elist.add(tempe0);

        Expense tempe1 = new Expense();
        tempe1.setAmount(8);
        tempe1.setCategory("Car");
        elist.add(tempe1);

        Expense tempe2 = new Expense();
        tempe2.setAmount(9);
        tempe2.setCategory("Home");
        elist.add(tempe2);

        Expense tempe3 = new Expense();
        tempe3.setAmount(3);
        tempe3.setCategory("Car");
        elist.add(tempe3);

        Expense tempe4 = new Expense();
        tempe4.setAmount(4);
        tempe4.setCategory("Car");
        elist.add(tempe4);

        when(er.getExpenses()).thenReturn(Collections.unmodifiableList(elist));
        //5.1 tak ma znaczenie wtedy nie działa
        when(er.getExpensesByCategory(anyString())).thenReturn(Collections.emptyList());
        when(er.getExpensesByCategory("Car")).thenReturn(Arrays.asList(tempe1,tempe3,tempe4)   );
        when(er.getExpensesByCategory("Home")).thenReturn(Arrays.asList(tempe0,tempe2)   );

        ExpenseManager em = new ExpenseManager(er, fs);

        assertEquals(em.calculateTotalForCategory("Home"),10);
        assertEquals(em.calculateTotalForCategory("Car"),15);
        assertEquals(em.calculateTotalForCategory("Food"),0);
        assertEquals(em.calculateTotalForCategory("Sport"),0);

    }
    @Test
    public void calculateTotalInDollarsTest() throws ConnectException {
        er = mock(ExpenseRepository.class);
        fs= mock(FancyService.class);
        Expense e1 = new Expense();
        e1.setAmount(5);
        Expense e2 = new Expense();
        e2.setAmount(3);
        when(er.getExpenses()).thenReturn(Arrays.asList(e1,e2));
        when(fs.convert(anyDouble(), eq("PLN"), eq("USD"))).thenReturn(0.0);
        when(fs.convert(8.0,"PLN","USD")).thenReturn(2.0);
        ExpenseManager em=new ExpenseManager(er,fs);
        assertEquals(em.calculateTotalInDollars(),2.0,0.001);
    }
    @Test
    public void calculateTotalInDollarsExceptionTest() throws ConnectException {
        er = mock(ExpenseRepository.class);
        fs= mock(FancyService.class);
        Expense e1 = new Expense();
        e1.setAmount(5);
        Expense e2 = new Expense();
        e2.setAmount(3);
        when(er.getExpenses()).thenReturn(Arrays.asList(e1,e2));
        when(fs.convert(anyDouble(), eq("PLN"), eq("USD"))).thenThrow(new ConnectException());
        ExpenseManager em=new ExpenseManager(er,fs);
        assertEquals(em.calculateTotalInDollars(),-1.0,0.001);
    }
    @Test
    public void calculateTotalInDollarsCallbackTest() throws ConnectException {
        er = mock(ExpenseRepository.class);
        fs = mock(FancyService.class);
        Expense e1 = new Expense();
        e1.setAmount(5);
        Expense e2 = new Expense();
        e2.setAmount(3);
        when(er.getExpenses()).thenReturn(Arrays.asList(e1,e2));
        when(fs.convert(anyDouble(), eq("PLN"), eq("USD"))).thenAnswer(
            new Answer() {
                public Object answer(InvocationOnMock invocation) {
                    Object[] args = invocation.getArguments();
                    Object mock = invocation.getMock();
                    return (double) args[0] / 4;
                }
            }
        );

        ExpenseManager em=new ExpenseManager(er,fs);
        assertEquals(em.calculateTotalInDollars(),2.0,0.001);
    }
}
