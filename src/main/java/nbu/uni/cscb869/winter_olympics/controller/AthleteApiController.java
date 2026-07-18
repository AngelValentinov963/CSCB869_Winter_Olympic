package nbu.uni.cscb869.winter_olympics.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nbu.uni.cscb869.winter_olympics.data.entity.Athlete;
import nbu.uni.cscb869.winter_olympics.dto.AthleteCreateDTO;
import nbu.uni.cscb869.winter_olympics.service.AthleteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")

@RequestMapping("/api/athletes")
public class AthleteApiController {
    private final AthleteService athleteService;


    @PostMapping("/register")
    public ResponseEntity<String> registerAthlete(@Valid @RequestBody AthleteCreateDTO athleteDTO, Authentication authentication) {

        athleteService.create(athleteDTO,authentication);
        return ResponseEntity.ok("Спортистът е регистриран успешно!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Athlete> getAthlete(@PathVariable long id) {
        Athlete athlete = athleteService.findById(id);
        return ResponseEntity.ok(athlete);
    }

}
