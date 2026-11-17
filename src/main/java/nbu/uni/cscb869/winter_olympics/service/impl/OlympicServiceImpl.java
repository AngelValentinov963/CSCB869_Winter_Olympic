
package nbu.uni.cscb869.winter_olympics.service.impl;

import lombok.RequiredArgsConstructor;
import nbu.uni.cscb869.winter_olympics.data.entity.Athlete;
import nbu.uni.cscb869.winter_olympics.data.entity.Olympic;
import nbu.uni.cscb869.winter_olympics.data.repository.OlympicRepository;
import nbu.uni.cscb869.winter_olympics.dto.MedalDTO;
import nbu.uni.cscb869.winter_olympics.service.OlympicService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OlympicServiceImpl implements OlympicService {

    private final OlympicRepository olympicRepository;

    @Override
    public Olympic create(Olympic olympic) {
        olympic.setId(null);
        return olympicRepository.save(olympic);
    }

    @Override
    public Olympic update(Long id, Olympic olympic) {
        Olympic existingOlympic = getById(id);

        existingOlympic.setName(olympic.getName());
        existingOlympic.setYear(olympic.getYear());

        return olympicRepository.save(existingOlympic);
    }

    @Override
    public Olympic getById(Long id) {
        return olympicRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Olympic not found with id: " + id));
    }

    @Override
    public List<Olympic> getAll() {
        return olympicRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Olympic olympic = getById(id);
        olympicRepository.delete(olympic);
    }

    @Override
    public List<MedalDTO> getMedalists(Long olympicId) {
        return List.of();
    }

    @Override
    public Map<String, Integer> getMedalistByCounty(String country) {
        return Map.of();
    }

    @Override
    public Athlete getYoungestMedalist(Long olympicId) {
        return null;
    }

    @Override
    public Athlete getOldestMedalist(Long olympicId) {
        return null;
    }

    // Medalist methods will be implemented separately.
}
