
package nbu.uni.cscb869.winter_olympics.controller;

import lombok.RequiredArgsConstructor;
import nbu.uni.cscb869.winter_olympics.dto.AthleteDTO;
import nbu.uni.cscb869.winter_olympics.dto.CompetitionDTO;
import nbu.uni.cscb869.winter_olympics.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    // ==========================================
    // ALL COMPETITIONS
    // ==========================================

    @GetMapping("/competitions")
    public ResponseEntity<List<CompetitionDTO>> getAllCompetitions() {
        return ResponseEntity.ok(
                statisticsService.getAllCompetitions()
        );
    }

    // ==========================================
    // MEDAL TABLE
    // ==========================================

    @GetMapping("/medals")
    public ResponseEntity<Map<String, Integer>> getMedalTable() {
        return ResponseEntity.ok(
                statisticsService.getMedalTable()
        );
    }

    // ==========================================
    // AVERAGE ATHLETE AGE
    // ==========================================

    @GetMapping("/average-age")
    public ResponseEntity<Double> getAverageAge() {
        return ResponseEntity.ok(
                statisticsService.getAverageAge()
        );
    }

    // ==========================================
    // YOUNGEST MEDALIST
    // ==========================================

    @GetMapping("/youngest-medalist")
    public ResponseEntity<AthleteDTO> getYoungestMedalist() {
        return ResponseEntity.ok(
                statisticsService.getYoungestMedalist()
        );
    }

    // ==========================================
    // OLDEST MEDALIST
    // ==========================================

    @GetMapping("/oldest-medalist")
    public ResponseEntity<AthleteDTO> getOldestMedalist() {
        return ResponseEntity.ok(
                statisticsService.getOldestMedalist()
        );
    }
}
