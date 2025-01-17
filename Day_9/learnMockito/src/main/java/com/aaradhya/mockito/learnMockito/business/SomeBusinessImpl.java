package com.aaradhya.mockito.learnMockito.business;

public class SomeBusinessImpl {

    private DataService dataService;

    public SomeBusinessImpl(DataService dataService) {
        this.dataService = dataService;
    }

    public int  findGreatest()
    {
        int[] data = dataService.retrieveAllData();
        int greatest = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] > greatest) {
                greatest = data[i];
            }
        }
        return greatest;
    }
}

interface DataService
{
    int[] retrieveAllData();
}
