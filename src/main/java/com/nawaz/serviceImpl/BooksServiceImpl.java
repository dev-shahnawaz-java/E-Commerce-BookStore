package com.nawaz.serviceImpl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.nawaz.entity.BooksModule;
import com.nawaz.entity.mongo.BookModuleMongo;
import com.nawaz.exceptions.ResourceNotFoundException;
import com.nawaz.model.BooksDto;
import com.nawaz.repository.BooksServiceRepository;
import com.nawaz.repository.momgo.BookMongoRepo;
import com.nawaz.service.BooksService;

import lombok.extern.slf4j.Slf4j;




@Service
@Slf4j
public class BooksServiceImpl implements BooksService {

	@Autowired BooksServiceRepository bsRepo;
	
	@Autowired BookMongoRepo bookMongoRepo;
	
	//convert entity to DTO 
	public static BooksDto toDto(BooksModule entity) {
		if(entity == null) {
			return null;
		}
		
		BooksDto dto = new BooksDto();
        dto.setId(entity.getId());  // Include ID for read operations
        dto.setName(entity.getName());
        dto.setTitle(entity.getTitle());
        dto.setAuthor(entity.getAuthor());
        dto.setCreatedDate(entity.getCreatedDate());  // Include for read operations
        dto.setUpdatedDate(entity.getUpdatedDate());  // Include for read operations
        
		
		return dto;
		
	}
	
	
	
	//convert DTO to entity
	
	public static BooksModule toEntity(BooksDto dto) {
		BooksModule entity = new BooksModule();
		entity.setName(dto.getName());
		entity.setAuthor(dto.getAuthor());
		entity.setTitle(dto.getTitle());
		
		return entity;
	}
	
	
	// For update operations - include ID
    public static BooksModule toEntityForUpdate(BooksDto dto, Long id) {
        BooksModule entity = new BooksModule();
        entity.setId(id);
        entity.setName(dto.getName());
        entity.setAuthor(dto.getAuthor());
        entity.setTitle(dto.getTitle());
        
        return entity;
    }
	
	
	
	@Override
	public BooksDto customerSaveBooks(BooksDto booksDto) {
		BooksModule entity = BooksServiceImpl.toEntity(booksDto);
		BooksModule savedEntity = bsRepo.save(entity);
		
		
		
		
		
		return BooksServiceImpl.toDto(savedEntity);
	}
	
	

	@Override
	@Cacheable(value ="getAllBooks")
	public List<BooksDto> getAllBooks() {
		List<BooksModule> booksEntities = bsRepo.findAll();
		
		
		return booksEntities.stream()
				            .map(BooksServiceImpl :: toDto)
				            .collect(Collectors.toList());
				         
	}


	@Override
	@Cacheable(value ="bookModule", key = "#id")
	public BooksDto getBookById(Long id) {

		log.info("Fetching book by ID: {}", id);
		BooksModule bookEntity = bsRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with id: "+id));
		
		
		
		return BooksServiceImpl.toDto(bookEntity);
	}
	

	@Override
    @Caching(
        put = {
            @CachePut(value = "book", key = "#id")
        },
        evict = {
            @CacheEvict(value = "books", allEntries = true)
        }
    )
    public BooksDto updateBook(Long id, BooksDto booksDto) {
        log.info("Updating book with ID: {}", id);
        
        // Check if book exists
        if (!bsRepo.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        
        BooksModule entity = BooksServiceImpl.toEntityForUpdate(booksDto, id);
        BooksModule updatedEntity = bsRepo.save(entity);
        return BooksServiceImpl.toDto(updatedEntity);
    }
	
	@Override
	@Caching(
			put= {
					@CachePut(value ="book", key = "#booksDto.id")
			},
			evict = {
					
					@CacheEvict(value="book", allEntries =true)
			}
			)
	public BooksDto createOrUpdateBook(BooksDto booksDto) {
		
		if(booksDto.getId() != null && bsRepo.existsById(booksDto.getId()) ) {
			
			log.info("updating existing book with id: { }", booksDto.getId());
			return updateBook(booksDto.getId(),booksDto);
		}else {
			log.info("Creating new book");
			return customerSaveBooks(booksDto);
		}
		
		
	}

	@Override
	@Caching(evict = {
			@CacheEvict(value="book", key ="id"),
			@CacheEvict(value ="book", allEntries =true)
	})
	public void deleteBookById(Long id) {
		
		log.info("Deleting book with id : {}",id);
		BooksModule bookEntity = bsRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book is not found with id :"+id));
		
		bsRepo.delete(bookEntity);
		log.info("Book deleted successfully with id: {} ", id);
		
	}



}


























