package ua.customermanager.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.customermanager.common.model.Customer;

/**
 * Repository for {@link Customer} class.
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
