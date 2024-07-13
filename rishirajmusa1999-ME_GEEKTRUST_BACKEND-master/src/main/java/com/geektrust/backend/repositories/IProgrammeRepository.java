package com.geektrust.backend.repositories;

import java.util.List;
import com.geektrust.backend.entities.Programmes;

public interface IProgrammeRepository extends CRUDRepository<Programmes>{
    public Double getProgramPricesByName(String programName);
    public boolean checkIfExist(String programName);
    public int getCartSize();
    public List<Programmes> findAllUserAddedPrograms();
}
