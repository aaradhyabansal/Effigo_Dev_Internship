import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class myCalculatorTest {
    private myCalculator myCalculator = new myCalculator();
    @BeforeAll
    static void beforeAll() {
        System.out.println("Executing @BeforeAll: Runs once before all tests.");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Executing @BeforeEach: Runs before each test.");
    }
    @Test
    void test_five_length_array(){
        assertEquals(15,myCalculator.add(new int[]{1,2,3,4,5}));
    }

    @Test
    void test_empty_array() {

        assertEquals(0,myCalculator.add(new int[]{}));
    }
    @AfterEach
    void afterEach() {
        System.out.println("Executing @AfterEach: Runs after each test.");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Executing @AfterAll: Runs once after all tests.");
    }

}