
package nbu.uni.cscb869.winter_olympics.service;

import nbu.uni.cscb869.winter_olympics.data.entity.Athlete;
import nbu.uni.cscb869.winter_olympics.data.entity.Olympic;
import nbu.uni.cscb869.winter_olympics.dto.MedalDTO;

import java.util.List;
import java.util.Map;

public interface OlympicService {

    // CRUD operations
    Olympic create(Olympic olympic);

    Olympic update(Long id, Olympic olympic);

    Olympic getById(Long id);

    List<Olympic> getAll();

    void delete(Long id);

    // Medalist statistics
    List<MedalDTO> getMedalists(Long olympicId);

    Map<String, Integer> getMedalistByCounty(String country);

    Athlete getYoungestMedalist(Long olympicId);

    Athlete getOldestMedalist(Long olympicId);
}
