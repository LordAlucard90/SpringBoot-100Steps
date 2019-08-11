# Part 09 - Appendix - Introduction to Mockito

## Initialization

Generate Spring Boot Initial project from [Spring Initializr](https://start.spring.io/), with:
- Generate a `Maven Project` with `Java` and Spring Boot `2.1.2`
- Group `com.lordalucard90.mockito`
- Artifact `mockito-introdution`

---

## Import project into IntelliJ IDEA

1. `file` > `new` > `Project from Existing Sources`
2. Select unzipped folder
3. `Import project from external model` > `Maven`
4. `next` until finish

---

## Simulating Methods

When there is a interface:

```java
public interface DataService {
    int[] retrieveAllData();
}
```
And there is a method who uses the that interface:

```java
public class SomeBusinessImp {
    private DataService dataService;

    public SomeBusinessImp(DataService dataService) {
        this.dataService = dataService;
    }

    int findTheGratestFromAllDara(){
        int[] allData = dataService.retrieveAllData();
        int greatest = Integer.MIN_VALUE;
        for (int value: allData){
            if (value > greatest){
                greatest = value;
            }
        }
        return greatest;
    }
}
```

It is necessary create mocks to simulate then method response:

```java
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SomeBusinessImpTest {
    @Test
    public void testFindTheGratestFromAllDara(){
        DataService dataServiceMock = mock(DataService.class);
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {24, 15, 3});
        SomeBusinessImp someBusinessImp = new SomeBusinessImp(dataServiceMock);
        int gratest = someBusinessImp.findTheGratestFromAllDara();
        assertEquals(24, gratest);
    }
}
```

`when` defines then method simulated, `thenReturn` defines the returned values.

A better way to use mock is to inject the service in this way:

```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SomeBusinessImpTest {

    @Mock
    DataService dataServiceMock;

    @InjectMocks
    SomeBusinessImp someBusinessImp;

    @Test
    public void testFindTheGratestFromAllDara(){
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {24, 15, 3});
        int gratest = someBusinessImp.findTheGratestFromAllDara();
        assertEquals(24, gratest);
    }
}
```
---

## Multiple Mock Returns

It is possible to change the return of the mock object per every call necessary:

```java
public void testMultipleListReturns(){
    List listMock = mock(List.class);
    when(listMock.size()).thenReturn(10).thenReturn(20);
    assertEquals(10, listMock.size());
    assertEquals(20, listMock.size());
}
```

By default if the mocked element is called with a different parameter it will return null:

```java
public void testMultipleListReturnsWithDifferentParameter(){
    List listMock = mock(List.class);
    when(listMock.get(0)).thenReturn("something");
    assertEquals("something", listMock.get(0));
    assertEquals(null, listMock.get(1));
}
```

It is possible to return always the same value using `anyInt`:

```java
public void testSameListReturnsWithDifferentParameter(){
    List listMock = mock(List.class);
    when(listMock.get(Mockito.anyInt())).thenReturn("something");
    assertEquals("something", listMock.get(0));
    assertEquals("something", listMock.get(1));
}
```
Mockito provides a lot of generic parameter for this scope.

