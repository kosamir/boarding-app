package hr.optimus.boardingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.optimus.boardingapp.model.Form;

public interface FormRepository extends JpaRepository<Form, Long> {

}
