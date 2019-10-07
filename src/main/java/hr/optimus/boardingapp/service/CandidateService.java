package hr.optimus.boardingapp.service;

import java.util.List;

import hr.optimus.boardingapp.service.dto.CandidateDTO;
import hr.optimus.boardingapp.service.dto.CandidateResponseDTO;

public interface CandidateService {

	
	CandidateDTO addNewCandidate(String payload);
	
	CandidateDTO addNewCandidate(CandidateDTO dto);

	List<CandidateResponseDTO> addCandidateResponses(String payload);
	
	CandidateResponseDTO addCandidateResponse(CandidateResponseDTO dto);

	List<CandidateResponseDTO> getCandiatesResponses(Long candidateId);

}
