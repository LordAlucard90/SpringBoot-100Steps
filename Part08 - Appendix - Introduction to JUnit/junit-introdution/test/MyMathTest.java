import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class MyMathTest {
    MyMath myMath = new MyMath();

//    @Test
//    public void test(){
//        fail("not impleentes");
//    }

//    @BeforeClass
    @BeforeAll
    public static void beforeAll(){
        System.out.println("Before All");
    }

//    @Before
    @BeforeEach
    public void beforeEach(){
        System.out.println("Before Each");
    }

//    @After
    @AfterEach
    public void afterEach(){
        System.out.println("After Each");
    }

//    @AfterClAss
    @AfterAll
    public static void afterAll(){
        System.out.println("After All");
    }

    @Test
    public void sum_with_one_numbers(){
        int result = myMath.sum(new int[]{1});
        assertEquals(1, result);
    }

    @Test
    public void sum_with_three_numbers(){
        int result = myMath.sum(new int[]{1, 2, 3});
        assertEquals(6, result);
    }
}
