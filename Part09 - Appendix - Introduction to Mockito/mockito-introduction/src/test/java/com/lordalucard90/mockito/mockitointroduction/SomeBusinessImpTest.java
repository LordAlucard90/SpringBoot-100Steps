package com.lordalucard90.mockito.mockitointroduction;

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
//        DataService dataServiceMock = mock(DataService.class);
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {24, 15, 3});
//        SomeBusinessImp someBusinessImp = new SomeBusinessImp(dataServiceMock);
        int gratest = someBusinessImp.findTheGratestFromAllDara();
        assertEquals(24, gratest);
    }
}
