package hr.optimus.boardingapp.error;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ApiError {

	private HttpStatus status;
	private String message;
	private List<String> errors;

}
