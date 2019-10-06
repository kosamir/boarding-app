package hr.optimus.boardingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.optimus.boardingapp.model.Form;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {

}
