package gr.xxm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gr.xxm.entity.YdzfDz;

@Repository

public interface YdzfDzRepository extends CrudRepository<YdzfDz, Long> {
}
