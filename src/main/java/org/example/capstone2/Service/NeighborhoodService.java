package org.example.capstone2.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone2.ApiResponse.ApiException;
import org.example.capstone2.Model.Neighborhood;
import org.example.capstone2.Repository.NeighborhoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NeighborhoodService {

    private final NeighborhoodRepository neighborhoodRepository;

    public List<Neighborhood> getAllNeighborhoods() {
        return neighborhoodRepository.findAll();
    }

    public Neighborhood getNeighborhoodById(Integer id) {
        Neighborhood neighborhood = neighborhoodRepository.findNeighborhoodById(id);
        if (neighborhood == null) {
            throw new ApiException("Neighborhood not found");
        }
        return neighborhood;
    }

    public void addNeighborhood(Neighborhood neighborhood) {
        neighborhoodRepository.save(neighborhood);
    }

    public void updateNeighborhood(Integer id, Neighborhood neighborhood) {
        Neighborhood oldNeighborhood = neighborhoodRepository.findNeighborhoodById(id);
        if (oldNeighborhood == null) {
            throw new ApiException("Neighborhood not found");
        }
        oldNeighborhood.setName(neighborhood.getName());
        oldNeighborhood.setCityId(neighborhood.getCityId());
        neighborhoodRepository.save(oldNeighborhood);
    }

    public void deleteNeighborhood(Integer id) {
        Neighborhood neighborhood = neighborhoodRepository.findNeighborhoodById(id);
        if (neighborhood == null) {
            throw new ApiException("Neighborhood not found");
        }
        neighborhoodRepository.delete(neighborhood);
    }

    public List<Neighborhood> getNeighborhoodsByCityId(Integer cityId) {
        return neighborhoodRepository.findAllByCityId(cityId);
    }
}
