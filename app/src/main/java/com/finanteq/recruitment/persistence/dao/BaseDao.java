package com.finanteq.recruitment.persistence.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.List;

public interface BaseDao<E> {

    @Insert
    Long insertEntity(E obj);

    @Insert
    List<Long> insertEntities(E[] obj);

    @Insert
    List<Long> insertEntities(List<E> obj);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertEntityR(E obj);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertEntitiesR(E[] obj);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertEntitiesR(List<E> obj);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insertEntityI(E obj);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertEntitiesI(E[] obj);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertEntitiesI(List<E> obj);

    @Update
    void updateEntity(E obj);

    @Update
    void updateEntities(E[] obj);

    @Update
    void updateEntities(List<E> obj);

    @Delete
    void deleteEntity(E obj);

    @Delete
    void deleteEntities(E[] obj);

    @Delete
    void deleteEntities(List<E> obj);
}