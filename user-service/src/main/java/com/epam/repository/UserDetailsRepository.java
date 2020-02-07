package com.epam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.epam.entity.UserEntity;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserEntity, Integer> {

	@Query("select d from UserEntity d where d.domainId= ?1")
	public List<UserEntity> findByDomainId(int domainId);

}
