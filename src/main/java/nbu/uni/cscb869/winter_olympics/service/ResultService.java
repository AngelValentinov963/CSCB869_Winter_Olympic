
package nbu.uni.cscb869.winter_olympics.service;

import nbu.uni.cscb869.winter_olympics.dto.SlalomResultDTO;
import nbu.uni.cscb869.winter_olympics.dto.BiathlonResultDTO;

import java.util.List;

public interface ResultService {

    List<SlalomResultDTO> getSlalomResults(Long competitionId);

    List<BiathlonResultDTO> getBiathlonFinalRanking(Long competitionId);
}
