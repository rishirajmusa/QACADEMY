package com.geektrust.backend.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.geektrust.backend.entities.Programmes;

public class ProgrammeRepository implements IProgrammeRepository{
private final Map<String, Double> programPriceMap ;
private final List<Programmes> programmeList = new ArrayList<>();

    public ProgrammeRepository() {
        programPriceMap = new HashMap<>();
        addProgramPrices();
    }

    public ProgrammeRepository(Map<String, Double> programPriceMap) {
        this.programPriceMap = programPriceMap;
    }

    @Override
    public void save(Programmes entity) {
        programmeList.add(entity);
    }

    @Override
    public List<Programmes> findAllUserAddedPrograms() {
        return programmeList;
    }

    @Override
    public boolean checkIfExist(String programName) {
        return programPriceMap.containsKey(programName);
    }

    @Override
    public int getCartSize() {
        return programmeList.size();
    }

    @Override
    public Double getProgramPricesByName(String programName){
        if(checkIfExist(programName)){
            return programPriceMap.get(programName);
        }
        return 0.0;
        
    }

    private void addProgramPrices(){
        programPriceMap.put("CERTIFICATION",3000.0);
        programPriceMap.put("DEGREE",5000.0);
        programPriceMap.put("DIPLOMA",2500.0);
    }

    
}
