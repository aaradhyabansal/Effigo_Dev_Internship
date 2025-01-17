package com.aaradhya.mockito.learnMockito.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SomeBusinessImplStubTest {


    @Test
    void findGreatestFromArr()
    {
        DataServiceStub dataServiceStub=new DataServiceStub();
        SomeBusinessImpl someBusiness=new SomeBusinessImpl(dataServiceStub);
        assertEquals(30,someBusiness.findGreatest());
    }

}

class DataServiceStub implements DataService{

    @Override
    public int[] retrieveAllData() {
        return new int[]{25,13,14,30};
    }
}