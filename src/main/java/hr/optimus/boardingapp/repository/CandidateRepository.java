package hr.optimus.boardingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.optimus.boardingapp.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

}
