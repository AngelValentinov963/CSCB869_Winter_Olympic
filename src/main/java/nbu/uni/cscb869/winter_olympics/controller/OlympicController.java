
package nbu.uni.cscb869.winter_olympics.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nbu.uni.cscb869.winter_olympics.data.entity.Olympic;
import nbu.uni.cscb869.winter_olympics.service.OlympicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/olympics")
@RequiredArgsConstructor
public class OlympicController {

    private final OlympicService olympicService;

    @PostMapping
    public ResponseEntity<Olympic> create(
            @Valid @RequestBody Olympic olympic) {

        Olympic created = olympicService.create(olympic);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Olympic>> getAll() {
        return ResponseEntity.ok(olympicService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Olympic> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(olympicService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Olympic> update(
            @PathVariable Long id,
            @Valid @RequestBody Olympic olympic) {

        return ResponseEntity.ok(
                olympicService.update(id, olympic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        olympicService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
