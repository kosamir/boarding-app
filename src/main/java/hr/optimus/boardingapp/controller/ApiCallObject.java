package hr.optimus.boardingapp.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiCallObject {
	private String imageBase64;
	private String [] recognizers ;//= {"CRO_ID_FRONT","CRO_ID_BACK"} ;
	private String userId;
	
	
	

}
