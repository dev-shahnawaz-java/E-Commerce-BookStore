package com.nawaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nawaz.entity.FileEntity;

@Repository
public interface FileRepo extends JpaRepository<FileEntity, Long> {

}
