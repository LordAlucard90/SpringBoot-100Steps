# Part 08 - Appendix - Introduction to JUnit

## Import

Depending on the version of JUnit the import syntax is:

```java
// JUnit 5
import org.junit.*;
import static org.junit.Assert.*;
// JUnit 5
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
```
---

## Creating A Test

Each test has to be annotated with `@Test` annotation.

```java
public class MyMathTest {

    @Test
    public void test(){
      System.out.println("Simple Test");
    }
}
```
---

## Assertions

A test without fails automatically passes. It is possible add some checks using the assertions.

Base syntax: `assertType(expected, actual);`

- **assertEquals** - checks if `expected` and `actual` are equal.
- **assertNotEquals** - checks if `expected` and `actual` are different.
- **assertTrue** - checks if `actual` is **True**.
- **assertFalse** - checks if `actual` is **False**.
- **assertNotNull** - checks if `actual` is not **Null**.
- **assertNull** - checks if `actual` is **Null**.
- **assertArrayEquals** - checks if each element of `expected` and `actual` is equal.
- **assertArrayNotEquals** - checks if each element of `expected` and `actual` is different.

---

## Before And After

It is possible execute some actions before or after the execution of all or each test.

```java
// JUnit4 -> @BeforeClass
@BeforeAll
public static void beforeAll(){
    System.out.println("Before All");
}

// JUnit4 -> @Before
@BeforeEach
public void beforeEach(){
    System.out.println("Before Each");
}

// JUnit4 -> @After
@AfterEach
public void afterEach(){
    System.out.println("After Each");
}

// JUnit4 -> @AfterClAss
@AfterAll
public static void afterAll(){
    System.out.println("After All");
  }
```
- **BeforeAll** - executes some code before all the tests.
- **BeforeEach** - executes some code before each test.
- **AfterEach** - executes some code after each the tests.
- **AfterAll** - executes some code after all the tests.
