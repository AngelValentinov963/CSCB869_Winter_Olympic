package nbu.uni.cscb869.winter_olympics.service;

public interface BiathlonService {

    void enterSkiTime(Long competitionId,
                      Long athleteId,
                      Double skiTime);

    void enterMisses(Long competitionId,
                     Long athleteId,
                     Integer misses);

    //List<ResultDto> calculateRanking(Long competitionId);

}