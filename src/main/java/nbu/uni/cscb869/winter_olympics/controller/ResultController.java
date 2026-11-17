
package nbu.uni.cscb869.winter_olympics.controller;

import lombok.RequiredArgsConstructor;
import nbu.uni.cscb869.winter_olympics.dto.BiathlonResultDTO;
import nbu.uni.cscb869.winter_olympics.dto.SlalomResultDTO;
import nbu.uni.cscb869.winter_olympics.service.ResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    // ==========================================
    // SLALOM
    // ==========================================

    @GetMapping("/slalom/{competitionId}")
    public ResponseEntity<List<SlalomResultDTO>> getSlalomResults(
            @PathVariable Long competitionId
    ) {
        List<SlalomResultDTO> results =
                resultService.getSlalomResults(competitionId);

        return ResponseEntity.ok(results);
    }

    // ==========================================
    // BIATHLON
    // ==========================================

    @GetMapping("/biathlon/{competitionId}")
    public ResponseEntity<List<BiathlonResultDTO>> getBiathlonResults(
            @PathVariable Long competitionId
    ) {
        List<BiathlonResultDTO> results =
                resultService.getBiathlonFinalRanking(competitionId);

        return ResponseEntity.ok(results);
    }
}
