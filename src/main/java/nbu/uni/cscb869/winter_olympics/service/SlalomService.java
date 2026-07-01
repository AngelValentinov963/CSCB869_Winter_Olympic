package nbu.uni.cscb869.winter_olympics.service;

public interface SlalomService {

    void enterFirstRun(Long competitionId,
                       Long athleteId,
                       Double time);

    //List<ResultDto> determineSecondRunParticipants(Long competitionId);

    void enterSecondRun(Long competitionId,
                        Long athleteId,
                        Double time);

    //List<ResultDto> calculateFinalRanking(Long competitionId);

}