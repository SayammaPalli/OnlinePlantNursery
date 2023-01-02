package com.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Planter;

@Repository
public interface IPlanterRepository extends JpaRepository<Planter, Integer> {

}
