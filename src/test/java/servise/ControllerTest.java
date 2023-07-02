package servise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void example() {
        Controller controller = new Controller();
        int calculate = controller.calculate(1, 2);

        assertEquals(3, calculate);
    }

    @Test
    void npuTest() {
        Controller controller = new Controller();

        assertThrows(IllegalArgumentException.class, () -> {
            controller.division(5,0);
        });
    }

}