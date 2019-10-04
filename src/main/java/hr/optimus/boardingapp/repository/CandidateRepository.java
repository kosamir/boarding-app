package hr.optimus.boardingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.optimus.boardingapp.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

}
