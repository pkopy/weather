package pl.pkopy.weather.models.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.pkopy.weather.models.CityEntity;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<CityEntity, Integer> {
    List<CityEntity> findByNameContainingIgnoreCase(String city);
}
