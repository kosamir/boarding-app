package hr.optimus.boardingapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import hr.optimus.boardingapp.model.Candidate;
import hr.optimus.boardingapp.model.CandidateResponse;
import hr.optimus.boardingapp.repository.BoardingTemplateRepository;
import hr.optimus.boardingapp.repository.CandidateRepository;
import hr.optimus.boardingapp.repository.CandidateResponseRepository;
import hr.optimus.boardingapp.repository.FieldRepository;
import hr.optimus.boardingapp.repository.FormRepository;
import hr.optimus.boardingapp.service.CandidateService;
import hr.optimus.boardingapp.service.dto.CandidateDTO;
import hr.optimus.boardingapp.service.dto.CandidateResponseDTO;
import hr.optimus.boardingapp.service.dto.mapper.CandidateMapper;
import hr.optimus.boardingapp.service.dto.mapper.CandidateResponseMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

	private final CandidateRepository repository;
	private final CandidateResponseRepository responseRepository;
	private final BoardingTemplateRepository boardingRepository;
	private final FormRepository formRepository;
	private final FieldRepository fieldRepository;
	private final CandidateMapper mapper;
	private final CandidateResponseMapper responseMapper;

	@Override
	public CandidateDTO addNewCandidate(List<Map<String, String>> formData) {
		CandidateDTO dto = toCandidateDTO(formData);
		Candidate entity = mapper.toEntity(dto);
		repository.save(entity);
		return mapper.toDTO(entity);
	}
	
	

	private CandidateDTO toCandidateDTO(List<Map<String, String>> formData) {
		CandidateDTO dto = new CandidateDTO();
		
		String firstName = formData.get(0).get("value");
		dto.setFirstName(firstName);
		
		String lastName = formData.get(1).get("value");
		dto.setLastName(lastName);
		
		String adress = formData.get(2).get("value").replace("+", " ");
		dto.setAddress(adress);
		
		String date = formData.get(3).get("value");
		dto.setDate(date);
		
		String gender = formData.get(4).get("value").toUpperCase();
		dto.setGender(gender);
		return dto;
	}

	@Override
	public List<CandidateResponseDTO> addCandidateResponses(List<Map<String, String>> formData) {
		Long templateId = new Long(formData.get(0).get("value")); 
		Long formId = new Long(formData.get(1).get("value"));
		Long candidateId = new Long(formData.get(2).get("value"));
		List<CandidateResponse> lst = new ArrayList<CandidateResponse>();
		for (int i = 3; i < formData.size(); ++i) {
			CandidateResponse response = new CandidateResponse();
			response.setCandidate(repository.getOne(candidateId));
			response.setTemplate(boardingRepository.getOne(templateId));
			response.setForm(formRepository.getOne(formId));
			response.setField(fieldRepository.getOne(new Long(formData.get(i).get("name").substring(formData.get(i).get("name").lastIndexOf("_")+1))));
			response.setAnswer(formData.get(i).get("value"));
			lst.add(response);
		}
		List<CandidateResponse> ret = responseRepository.saveAll(lst);
		return toDto(ret);
	}
	
	@Override
	public List<CandidateResponseDTO> getCandiatesResponses(Long candidateId) {
		Candidate candidate = repository.findById(candidateId).orElse(null);
		List<CandidateResponseDTO> ret = new ArrayList<CandidateResponseDTO>();
		if (null != candidate) {
			List<CandidateResponse> lst = responseRepository.getCandidateResponseByCandidateId(candidate.getId());
			ret = toDto(lst);
		}
		return ret;
	}

	private List<CandidateResponseDTO> toDto(List<CandidateResponse> responses) {
		List<CandidateResponseDTO> ret = new ArrayList<CandidateResponseDTO>();
		for (CandidateResponse candidateResponse : responses) {
			CandidateResponseDTO dto = responseMapper.toDto(candidateResponse);
			ret.add(dto);
		}
		return ret;
	}

	@Override
	public CandidateDTO addNewCandidate(CandidateDTO dto) {
		Candidate entity = mapper.toEntity(dto);
		repository.save(entity);
		return mapper.toDTO(repository.getOne(entity.getId()));
	}

	@Override
	public CandidateResponseDTO addCandidateResponse(CandidateResponseDTO dto) {
		CandidateResponse entity = responseMapper.toEntity(dto);
		responseRepository.save(entity);
		return responseMapper.toDto(entity);
	}

}
