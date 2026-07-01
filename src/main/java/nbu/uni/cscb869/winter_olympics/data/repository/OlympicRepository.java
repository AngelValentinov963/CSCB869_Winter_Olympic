package nbu.uni.cscb869.winter_olympics.data.repository;

import nbu.uni.cscb869.winter_olympics.data.entity.Olympic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OlympicRepository extends JpaRepository<Olympic, Integer> {
}
