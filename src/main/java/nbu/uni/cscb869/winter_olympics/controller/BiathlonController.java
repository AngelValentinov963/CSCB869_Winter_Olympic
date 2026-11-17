
package nbu.uni.cscb869.winter_olympics.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import nbu.uni.cscb869.winter_olympics.dto.BiathlonInputDTO;
import nbu.uni.cscb869.winter_olympics.dto.BiathlonResultDTO;
import nbu.uni.cscb869.winter_olympics.service.BiathlonService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/biathlon")
@RequiredArgsConstructor
public class BiathlonController {

    private final BiathlonService biathlonService;

    // Enter skiing time
    @PutMapping("/competitions/{competitionId}/athletes/{athleteId}/ski-time")
    public ResponseEntity<Void> enterSkiTime(
            @PathVariable Long competitionId,
            @PathVariable Long athleteId,
            @RequestParam Double skiTime) {

        biathlonService.enterSkiTime(
                competitionId,
                athleteId,
                skiTime
        );

        return ResponseEntity.noContent().build();
    }

    // Enter missed shots
    @PutMapping("/competitions/{competitionId}/athletes/{athleteId}/misses")
    public ResponseEntity<Void> enterMisses(
            @PathVariable Long competitionId,
            @PathVariable Long athleteId,
            @RequestParam Integer misses) {

        biathlonService.enterMisses(
                competitionId,
                athleteId,
                misses
        );

        return ResponseEntity.noContent().build();
    }

    // Submit complete biathlon result
    @PutMapping("/competitions/{competitionId}/results")
    public ResponseEntity<BiathlonResultDTO> submitResult(
            @PathVariable Long competitionId,
            @Valid @RequestBody BiathlonInputDTO dto) {

        BiathlonResultDTO result =
                biathlonService.calculateAthleteFinalResult(
                        competitionId,
                        dto
                );

        return ResponseEntity.ok(result);
    }

    // Get competition ranking
    @GetMapping("/competitions/{competitionId}/ranking")
    public ResponseEntity<List<BiathlonResultDTO>> getRanking(
            @PathVariable Long competitionId) {

        return ResponseEntity.ok(
                biathlonService.calculateRanking(competitionId)
        );
    }
}
