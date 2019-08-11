package com.lordalucard90.mockito.mockitointroduction;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockReturnsTest {
    @Test
    public void testMultipleListReturns() {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(10).thenReturn(20);
        assertEquals(10, listMock.size());
        assertEquals(20, listMock.size());
    }

    @Test
    public void testMultipleListReturnsWithDifferentParameter() {
        List listMock = mock(List.class);
        when(listMock.get(0)).thenReturn("something");
        assertEquals("something", listMock.get(0));
        assertEquals(null, listMock.get(1));
    }

    @Test
    public void testSameListReturnsWithDifferentParameter() {
        List listMock = mock(List.class);
        when(listMock.get(Mockito.anyInt())).thenReturn("something");
        assertEquals("something", listMock.get(0));
        assertEquals("something", listMock.get(1));
    }

}
