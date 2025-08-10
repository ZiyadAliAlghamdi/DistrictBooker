package org.example.capstone2.Repository;

import org.example.capstone2.Model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Integer>{
    Facility getFacilityById(Integer id);


    @Query("""
     select f from Facility f
     where f.id in :ids
       and (:neighborhood is null or f.neighborhoodId = :neighborhoodId)
     """)
    List<Facility> findByIdsWithFilter(List<Integer> ids, Integer neighborhoodId);

    @Query("select f from Facility f where f.neighborhoodId =?1")
    List<Facility> findFacilitiesByNeighborhood(Integer neighborhoodId);


}
