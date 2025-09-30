package com.nareshit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nareshit.entity.FileEntity;

@Repository
public interface FileRepo extends JpaRepository<FileEntity, Long> {

}
