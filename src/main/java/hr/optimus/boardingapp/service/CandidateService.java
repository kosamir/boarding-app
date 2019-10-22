package hr.optimus.boardingapp.service;

import java.util.List;
import java.util.Map;

import hr.optimus.boardingapp.service.dto.CandidateDTO;
import hr.optimus.boardingapp.service.dto.CandidateResponseDTO;

public interface CandidateService {

	
	CandidateDTO addNewCandidate(List<Map<String, String>> formData);
	
	CandidateDTO addNewCandidate(CandidateDTO dto);

	List<CandidateResponseDTO> addCandidateResponses(List<Map<String, String>> formData);
	
	CandidateResponseDTO addCandidateResponse(CandidateResponseDTO dto);

	List<CandidateResponseDTO> getCandiatesResponses(Long candidateId);

}
