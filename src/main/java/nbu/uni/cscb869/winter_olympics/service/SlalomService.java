package nbu.uni.cscb869.winter_olympics.service;

import nbu.uni.cscb869.winter_olympics.dto.SlalomDTO.SlalomFirstRunDTO;
import nbu.uni.cscb869.winter_olympics.dto.SlalomDTO.SlalomResultDTO;
import nbu.uni.cscb869.winter_olympics.dto.SlalomDTO.SlalomSecondRunDTO;
import org.springframework.stereotype.Service;

import java.util.List;
public interface SlalomService {

    void submitFirstRun(SlalomFirstRunDTO dto);

    List<Long> getSecondRunStartOrder(Long competitionId);

    void submitSecondRun(SlalomSecondRunDTO dto);

    List<SlalomResultDTO> getFinalRanking(Long competitionId);
}