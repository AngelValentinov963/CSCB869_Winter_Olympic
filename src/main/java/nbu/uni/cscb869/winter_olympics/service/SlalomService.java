package nbu.uni.cscb869.winter_olympics.service;

import nbu.uni.cscb869.winter_olympics.dto.SlalomFirstRunDTO;
import nbu.uni.cscb869.winter_olympics.dto.SlalomResultDTO;
import nbu.uni.cscb869.winter_olympics.dto.SlalomSecondRunDTO;

import java.util.List;

public interface SlalomService {

    void submitFirstRun(SlalomFirstRunDTO dto);

    List<Long> getSecondRunStartOrder(Long competitionId);

    void submitSecondRun(SlalomSecondRunDTO dto);

    List<SlalomResultDTO> getFinalRanking(Long competitionId);
}