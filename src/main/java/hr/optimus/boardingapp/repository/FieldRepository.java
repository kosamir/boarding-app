package hr.optimus.boardingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.optimus.boardingapp.model.Field;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {

}
