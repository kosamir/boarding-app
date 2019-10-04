package hr.optimus.boardingapp.controller.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hr.optimus.boardingapp.config.BoardingAppConfig;
import hr.optimus.boardingapp.service.BoardingTemplateService;
import hr.optimus.boardingapp.service.dto.FormDTO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final BoardingTemplateService service;
	private final static String INDEX = "index";
	private final BoardingAppConfig config;

	
	private Long getFormId(Long templateId, int completedSteps) {
		List<FormDTO> forms = service.getAllFormsFromBoard(templateId);
		return (forms!=null && forms.size()>completedSteps)?
				forms.get(completedSteps).getId():
					null;
		
	}
	

	@RequestMapping(value = { "/{name}", ""," "})
	public ModelAndView index(@PathVariable String name) {

		ModelAndView mav = new ModelAndView(INDEX);
		Long templateId = service.getTemplateByName(name);
		
		int completedSteps = 0;
		List<FormDTO> formDTOs = service.getAllFormsFromBoard(templateId);
		Long currentFormId = getFormId(templateId, completedSteps);
		completedSteps++;
		Long nextFormId = getFormId(templateId, completedSteps);
		
		mav.addObject("over", nextFormId==null?true:false);
		
		FormDTO formDTO = service.getFormById(new Long(templateId), new Long(currentFormId));
		mav.addObject("template", templateId!=null?name:"nepostojeci template");
		mav.addObject("numForms", formDTOs!=null?formDTOs.size():"0");
		mav.addObject("link", config.getWebhost()+ "/candidate/collect/response");
		
		mav.addObject("completedSteps", completedSteps);
		mav.addObject("formId", currentFormId);
		mav.addObject("nextFormId", nextFormId);
		
		mav.addObject("templateId", templateId);
		
		mav.addObject("form", formDTO);
		
		for (FormDTO dto : formDTOs) {
			System.out.println(dto.toString());
			
		}
		
		return mav;
	}
	
	@RequestMapping(value = { "{p_templateName}/{p_templateId}/{p_formId}/{p_numForms}/{p_numSteps}" })
	public ModelAndView nextForm(@PathVariable String p_templateName,
			@PathVariable String p_templateId, 
			@PathVariable String p_formId, 
			@PathVariable String p_numForms,
			@PathVariable String p_numSteps) {
		
		ModelAndView mav = new ModelAndView(INDEX);
		FormDTO dto = service.getFormById(new Long(p_templateId), new Long(p_formId));
		
		Integer numSteps = new Integer(p_numSteps);
		Integer numForms = new Integer(p_numForms);
		Long templateId = new Long(p_templateId);
		
		numSteps++;
		Long nextFormId = getFormId(templateId, numSteps);

		mav.addObject("form", dto);
		mav.addObject("link", config.getWebhost()+ "/candidate/collect/response");
		mav.addObject("template", p_templateName);
		mav.addObject("numForms", numForms);
		mav.addObject("completedSteps",++numSteps);
		mav.addObject("formId", p_formId);
		mav.addObject("nextFormId", nextFormId);
		mav.addObject("over", nextFormId==null?true:false);
		mav.addObject("templateId", templateId);
		
		return mav;
	}
	
	
	 	
}
