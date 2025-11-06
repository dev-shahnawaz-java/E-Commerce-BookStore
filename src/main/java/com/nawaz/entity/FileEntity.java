package com.nawaz.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ;
	
	@Column(name = "fileName")
	private String fileName;
	
	@Column(name="fileType")
	private String fileType;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Column(columnDefinition ="longblob")
	@Lob
	private byte[] data;
	
	@CreationTimestamp
	@Column(name="createdDate")
	public LocalDateTime createdDate;
	
	@UpdateTimestamp
	@Column(name="updatedDate")
	public LocalDateTime updatedDate;

	
	

}
