
package nbu.uni.cscb869.winter_olympics.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nbu.uni.cscb869.winter_olympics.data.entity.Competition.Competition;
import nbu.uni.cscb869.winter_olympics.service.CompetitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competitions")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CompetitionApiController {

    private final CompetitionService competitionService;

    // CREATE
    @PostMapping
    public ResponseEntity<Competition> create(
            @Valid @RequestBody Competition competition) {

        Competition created = competitionService.create(competition);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Competition>> getAll() {
        return ResponseEntity.ok(competitionService.getAll());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Competition> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(competitionService.getById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Competition> update(
            @PathVariable Long id,
            @Valid @RequestBody Competition competition) {

        return ResponseEntity.ok(
                competitionService.update(id, competition));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        competitionService.delete(id);

        return ResponseEntity.noContent().build();
    }

    // REGISTER ATHLETE
    @PostMapping("/{competitionId}/athletes/{athleteId}")
    public ResponseEntity<Boolean> registerAthlete(
            @PathVariable Long competitionId,
            @PathVariable Long athleteId) {

        boolean registered = competitionService.registerAthlete(
                competitionId, athleteId);

        if (!registered) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
        }

        return ResponseEntity.ok(true);
    }

    // UNREGISTER ATHLETE
    @DeleteMapping("/{competitionId}/athletes/{athleteId}")
    public ResponseEntity<Boolean> unregisterAthlete(
            @PathVariable Long competitionId,
            @PathVariable Long athleteId) {

        boolean unregistered = competitionService.unregisterAthlete(
                competitionId, athleteId);

        if (!unregistered) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(true);
    }
}
