package nbu.uni.cscb869.winter_olympics.service;

import nbu.uni.cscb869.winter_olympics.dto.SlalomRunDTO;
import nbu.uni.cscb869.winter_olympics.dto.SlalomResultDTO;

import java.util.List;
public interface SlalomService {

    void submitFirstRun(SlalomRunDTO dto);

    List<Long> getSecondRunStartOrder(Long competitionId);

    void submitSecondRun(SlalomRunDTO dto);


    List<SlalomResultDTO> getFinalRanking(Long competitionId);
}