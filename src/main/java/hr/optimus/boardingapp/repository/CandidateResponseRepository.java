package hr.optimus.boardingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.optimus.boardingapp.model.CandidateResponse;

@Repository
public interface CandidateResponseRepository extends JpaRepository<CandidateResponse, Long> {

	List<CandidateResponse> getCandidateResponseByCandidateId(Long candidateId);
}
