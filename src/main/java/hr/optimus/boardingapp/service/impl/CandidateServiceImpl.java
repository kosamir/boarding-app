package hr.optimus.boardingapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
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

	@Override
	public CandidateDTO addNewCandidate(String payload) {
		CandidateDTO dto = toCandidateDTO(payload);
		Candidate entity = mapper.toEntity(dto);
		repository.save(entity);
		return mapper.toDTO(entity);
	}

	// SHORT_TEXT_FIELD_25=amir&SHORT_TEXT_FIELD_26=kos&SHORT_TEXT_FIELD_27=koste&DATE_28=2019-10-03&CHOICE_29=Male
	// REFACTOR!!
	private CandidateDTO toCandidateDTO(String payload) {
		CandidateDTO dto = new CandidateDTO();
		String[] arr = payload.split("&");
		String firstName = (arr[0].split("="))[1];
		dto.setFirstName(firstName);
		String lastName = (arr[1].split("="))[1];
		dto.setLastName(lastName);
		String adress = (arr[2].split("="))[1].replace("+", " ");
		;
		dto.setAddress(adress);
		String date = (arr[3].split("="))[1];
		dto.setDate(date);
		String gender = (arr[4].split("="))[1].toUpperCase();
		dto.setGender(gender);
		return dto;
	}

	@Override
	// templateId=23&formId=30&candiateId=candiateId&CHOICE_31=%20Prolog
	// REFACTOR!
	public List<CandidateResponseDTO> addCandidateResponses(String payload) {
		String[] array = payload.split("&");
		Long templateId = new Long((array[0].split("="))[1].trim());
		Long formId = new Long((array[1].split("="))[1].trim());
		Long candidateId = new Long(array[2].split("=")[1].trim());
		List<CandidateResponse> lst = new ArrayList<CandidateResponse>();
		int num_data_on_form = 0;
		for (int i = 3; i < array.length; ++i) {
			CandidateResponse response = new CandidateResponse();
			response.setBoardId(templateId);
			response.setFormId(formId);
			response.setCandidateId(candidateId);
			Map<String, String> ret = extractValue(array[i]);

			response.setFieldId(new Long(ret.get("fieldId")));
			response.setAnswer(ret.get("value"));
			lst.add(response);
			num_data_on_form++;
		}
		List<CandidateResponse> ret = responseRepository.saveAll(lst);
		return toDto(ret);
	}
	private Map<String, String> extractValue(String pattern){
		Map<String, String> ret = new HashMap<String, String>();
		String [] data = pattern.split("=");
		ret.put("fieldId",data[0].substring(data[0].lastIndexOf("_")+1));
		ret.put("value", data[1].replace("+", ""));
		
		return ret;
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
			CandidateResponseDTO dto = new CandidateResponseDTO();
			dto.setId(candidateResponse.getId());
			dto.setBoardName((boardingRepository.findById(candidateResponse.getBoardId()).orElse(null)).getName());
			dto.setFormName((formRepository.findById(candidateResponse.getFormId()).orElse(null)).getName());
			dto.setFieldName((fieldRepository.findById(candidateResponse.getFieldId()).orElse(null)).getLabel());
			dto.setAnswer(candidateResponse.getAnswer());
			ret.add(dto);
		}
		return ret;
	}

}
