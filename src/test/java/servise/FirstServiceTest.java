package servise;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FirstServiceTest {

    @Test
    void test() {

        SecondService mockSecondService = Mockito.mock(SecondService.class);
        SecondService spySecondService = Mockito.spy(SecondService.class);

        FirstService firstService = new FirstService(mockSecondService);

        when(mockSecondService.testCall(5))
                .thenReturn(5);

        int i = firstService.doSomething(5);
        assertEquals(50, i);
    }

}