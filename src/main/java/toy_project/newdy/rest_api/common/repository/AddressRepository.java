package toy_project.newdy.rest_api.common.repository;

import toy_project.newdy.rest_api.common.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
}
