package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FAQRepository extends JpaRepository<FAQ, Long> {
}
