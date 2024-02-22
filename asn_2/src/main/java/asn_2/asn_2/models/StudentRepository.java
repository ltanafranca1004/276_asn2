package asn_2.asn_2.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // Method to find a student by their name
    List<Student> findByName(String name);

    // Method to find a student by their email - assuming email is unique
    Student findByEmail(String email);

    // Any additional query methods you need can be defined here
}

