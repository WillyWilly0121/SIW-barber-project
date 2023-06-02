package it.uniroma3.siw.repository;

import java.util.Optional;

import it.uniroma3.siw.model.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Credentials;
import org.springframework.data.repository.query.Param;

public interface CredentialsRepository extends CrudRepository<Credentials, Long> {

	Optional<Credentials> findByUsername(String username);
	
	Iterable<Credentials> findAllByRole(String role);

	@Query("select c.utente from Credentials c where c.utente.id=:id and c.role=:defaultRole")
    Optional<Utente> findByRoleAndId(@Param("defaultRole") String defaultRole,@Param("id") Long id);
}