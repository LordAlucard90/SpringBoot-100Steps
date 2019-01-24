package com.lordalucard90.mockito.mockitointroduction;

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
