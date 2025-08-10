package org.example.capstone2.Repository;

import org.example.capstone2.Model.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Integer> {
    Neighborhood findNeighborhoodById(Integer id);

    List<Neighborhood> findAllByCityId(Integer cityId);
}
