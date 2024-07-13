package com.geektrust.backend.repositories;

public interface CRUDRepository<T> {
    public void  save(T entity);
}

