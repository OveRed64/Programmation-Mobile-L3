package com.example.tp6;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/** Data access object (DAO)
 * PlanetDao provides the methods that the rest of the app planets to interact with data in the planet table.
 */

@Dao
public interface PlanetDao {
    @Query("SELECT * FROM Planet")
    List<Planet> getAll();

    @Query("SELECT * FROM Planet WHERE uid IN (:planetIds)")
    List<Planet> loadAllByIds(int[] planetIds);

    @Query("SELECT * FROM Planet WHERE name LIKE :n ")
    Planet findByName(String n);

    @Insert
    void insertAll(Planet... planets);

    @Delete
    void delete(Planet planet);

    @Insert
    void insert(Planet planet);
}

