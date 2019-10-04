package hr.optimus.boardingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.optimus.boardingapp.model.BoardingTemplate;

@Repository
public interface BoardingTemplateRepository extends JpaRepository<BoardingTemplate, Long> {
	
	BoardingTemplate getBoardingTemplateByName(String name);

}
