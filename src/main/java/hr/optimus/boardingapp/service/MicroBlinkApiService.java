package hr.optimus.boardingapp.service;

import org.springframework.web.multipart.MultipartFile;

import hr.optimus.boardingapp.service.dto.CandidateDTO;

public interface MicroBlinkApiService {
	CandidateDTO getDataFromEndPoint(MultipartFile file) throws Exception;

}
