package com.ninjaone.dundie_awards.repository;

import com.ninjaone.dundie_awards.domain.DundieAward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DundieAwardRepository extends JpaRepository<DundieAward, Long> {
}
