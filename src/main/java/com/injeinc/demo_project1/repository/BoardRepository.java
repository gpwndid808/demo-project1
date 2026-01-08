package com.injeinc.demo_project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.injeinc.demo_project1.entity.Board;


@Repository
public interface BoardRepository extends JpaRepository<Board, String> {

}
