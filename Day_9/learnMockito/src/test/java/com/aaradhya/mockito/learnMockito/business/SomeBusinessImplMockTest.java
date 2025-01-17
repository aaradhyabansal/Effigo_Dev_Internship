package com.aaradhya.mockito.learnMockito.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SomeBusinessImplMockTest {

    @Mock
    private
    DataService dataServiceMock;

    @InjectMocks
    private SomeBusinessImpl someBusiness;

    @Test
    void findGreatestFromArr()
    {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{30,21,22});
        assertEquals(30,someBusiness.findGreatest());
    }

}
