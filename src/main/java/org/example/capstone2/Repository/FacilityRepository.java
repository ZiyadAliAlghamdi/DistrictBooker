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
  where f.id in ?1
    and (?2 is null or f.neighborhoodId = ?2)
""")
    List<Facility> findByIdsWithFilter(List<Integer> ids, Integer neighborhoodId);


    @Query("select f from Facility f where f.neighborhoodId =?1")
    List<Facility> findFacilitiesByNeighborhood(Integer neighborhoodId);


}
