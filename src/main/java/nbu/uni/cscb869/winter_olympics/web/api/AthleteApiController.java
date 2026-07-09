package nbu.uni.cscb869.winter_olympics.web.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nbu.uni.cscb869.winter_olympics.dto.AthleteDTO.AthleteCreateDTO;
import nbu.uni.cscb869.winter_olympics.service.AthleteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/athletes")
public class AthleteApiController {
    private final AthleteService athleteService;


    @PostMapping("/register")
    public ResponseEntity<String> registerAthlete(@Valid @RequestBody AthleteCreateDTO athleteDTO) {
        athleteService.registerAthlete(athleteDTO);
        return ResponseEntity.ok("Спортистът е регистриран успешно!");
    }



}
