package nbu.uni.cscb869.winter_olympics.service;

import nbu.uni.cscb869.winter_olympics.dto.SlalomDTO.SlalomResultDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ResultService {
    List<SlalomResultDTO> getSlalomResults(Long competitionId);

    List<?> getBiathlonFinalRanking(Long competitionId);
}
