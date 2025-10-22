package com.nareshit.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nareshit.model.BooksDto;
import com.nareshit.model.ResponseMessage;
import com.nareshit.service.BooksService;
import com.nareshit.utility.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/book-api")
public class BookController {

	@Autowired
	BooksService booksService;

	@Operation(summary = "Create user Customers", description = "e commerce online book store")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "user books saved successfully! "),
			@ApiResponse(responseCode = "400", description = "user books saved Failure ! "),
			@ApiResponse(responseCode = "500", description = "Internal server error ! "), })

	@PostMapping("/savebooks")
	public ResponseEntity<ResponseMessage> createBooks(@RequestBody BooksDto booksDto) {

		try {
			log.info("Creating new book with title: {}", booksDto.getTitle());

			if (booksDto.getName() == null || booksDto.getName().isEmpty() || booksDto.getTitle() == null
					|| booksDto.getTitle().isEmpty()) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
								"Books Name and titile can to be empty !!!..."));

			}

			BooksDto customersaveBooks = booksService.customerSaveBooks(booksDto);

			if (customersaveBooks != null) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
						"Customer book saved successfully", customersaveBooks));

			} else {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"custmer  books save Failed", customersaveBooks));
			}

		} catch (Exception e) {

			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
					"Internal server error !!!!!!!!...."));
		}

	}

	@Operation(summary = "Create user Customers", description = "e commerce online book store")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Books retrieved successfully"),
			@ApiResponse(responseCode = "204", description = "No books found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/getall")
	public ResponseEntity<ResponseMessage> getAllBooks() {

		try {
			log.info("fetching all books");
			List<BooksDto> books = booksService.getAllBooks();

			if (books == null || books.isEmpty()) {
				log.info("No books record found in the database... ");

				return ResponseEntity.status(HttpStatus.OK).body(
						new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "No books Found", books));
			}

			log.info("Retrieve {} books successfully ", books.size());
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
					"Books retrieved successfully", books));

		} catch (Exception e) {
			log.error("Error while fetching books: {} ", e.getMessage(), e);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Error occured while fetching books "));
		}

	}

	@Operation(summary = "Get book by ID", description = "Retrieve a specific book by its ID")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Book retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "Book not found"),
			@ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })

	@GetMapping("/{id}")
	public ResponseEntity<ResponseMessage> getBookById(
			@Parameter(description = "Id of the book to be retrieved ", required = true) @PathVariable Long id) {

		try {
			log.info("Fetching book by id: {}", id);

			if (id == null || id <= 0) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
						new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Invalid book ID"));
			}

			BooksDto book = booksService.getBookById(id);
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
					"Books retrieved successfully ", book));
		} catch (Exception e) {
			log.error("Error while fetching book with ID {}: {}", id, e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage(HttpURLConnection.HTTP_NOT_FOUND, Constants.FAILED, e.getMessage()));
		}

	}
	
	@Operation(summary = "Update a book", description = "Update an existing book's information")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Book updated successfully"),
        @ApiResponse(responseCode = "404", description = "Book not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
	
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseMessage> updateBook(
            @Parameter(description = "ID of the book to be updated", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated book object", required = true)
            @RequestBody BooksDto booksDto) {
        try {
            log.info("Updating book with ID: {}", id);
            
            if (id == null || id <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(
                        HttpURLConnection.HTTP_BAD_REQUEST,
                        Constants.FAILED,
                        "Invalid book ID"
                    ));
            }
            
            // Validation
            if(booksDto.getName() == null || booksDto.getName().trim().isEmpty() ||
               booksDto.getTitle() == null || booksDto.getTitle().trim().isEmpty() ||
               booksDto.getAuthor() == null || booksDto.getAuthor().trim().isEmpty()) {
                log.warn("Validation failed: Book name, title or author is empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(
                        HttpURLConnection.HTTP_BAD_REQUEST, 
                        Constants.FAILED, 
                        "Book name, title and author cannot be empty"
                    ));
            }
            
            BooksDto updatedBook = booksService.updateBook(id, booksDto);
            
            log.info("Book updated successfully with ID: {}", id);
            return ResponseEntity.ok(
                new ResponseMessage(
                    HttpURLConnection.HTTP_OK,
                    Constants.SUCCESS,
                    "Book updated successfully",
                    updatedBook
                )
            );
            
        } catch (Exception e) {
            log.error("Error while updating book with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseMessage(
                    HttpURLConnection.HTTP_NOT_FOUND,
                    Constants.FAILED,
                    e.getMessage()
                ));
        }
    }
	
	
	 @Operation(summary = "Create or update a book", description = "Create a new book or update if it already exists")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Book created/updated successfully"),
	        @ApiResponse(responseCode = "400", description = "Invalid input data"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	    })
	    @PostMapping("/create-or-update")
	    public ResponseEntity<ResponseMessage> createOrUpdateBook(
	            @Parameter(description = "Book object to be created or updated", required = true)
	            @RequestBody BooksDto booksDto) {
	        try {
	            log.info("Create or update book with title: {}", booksDto.getTitle());

	            // Validation
	            if(booksDto.getName() == null || booksDto.getName().trim().isEmpty() ||
	               booksDto.getTitle() == null || booksDto.getTitle().trim().isEmpty() ||
	               booksDto.getAuthor() == null || booksDto.getAuthor().trim().isEmpty()) {
	                log.warn("Validation failed: Book name, title or author is empty");
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body(new ResponseMessage(
	                        HttpURLConnection.HTTP_BAD_REQUEST, 
	                        Constants.FAILED, 
	                        "Book name, title and author cannot be empty"
	                    ));
	            }
	            
	            BooksDto resultBook = booksService.createOrUpdateBook(booksDto);
	            
	            String message = booksDto.getId() != null ? "updated" : "created";
	            log.info("Book {} successfully with ID: {}", message, resultBook.getId());
	            return ResponseEntity.ok(
	                new ResponseMessage(
	                    HttpURLConnection.HTTP_OK,
	                    Constants.SUCCESS,
	                    "Book " + message + " successfully",
	                    resultBook
	                )
	            );
	                        
	        } catch (Exception e) {
	            log.error("Error while creating/updating book: {}", e.getMessage(), e);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ResponseMessage(
	                    HttpURLConnection.HTTP_INTERNAL_ERROR,
	                    Constants.FAILED,
	                    "Internal server error while processing book"
	                ));
	        }
	    }
	 
	 
	 @Operation(summary = "Delete a book", description = "Delete a book by its ID")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Book deleted successfully"),
	        @ApiResponse(responseCode = "404", description = "Book not found"),
	        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	    })
	    @DeleteMapping("delete/{id}")
	    public ResponseEntity<ResponseMessage> deleteBookById(
	            @Parameter(description = "ID of the book to be deleted", required = true)
	            @PathVariable Long id) {
	        try {
	            log.info("Deleting book with ID: {}", id);
	            
	            if (id == null || id <= 0) {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body(new ResponseMessage(
	                        HttpURLConnection.HTTP_BAD_REQUEST,
	                        Constants.FAILED,
	                        "Invalid book ID"
	                    ));
	            }
	            
	            booksService.deleteBookById(id);
	            
	            log.info("Book deleted successfully with ID: {}", id);
	            return ResponseEntity.ok(
	                new ResponseMessage(
	                    HttpURLConnection.HTTP_OK,
	                    Constants.SUCCESS,
	                    "Book deleted successfully"
	                )
	            );
	            
	        } catch (Exception e) {
	            log.error("Error while deleting book with ID {}: {}", id, e.getMessage());
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new ResponseMessage(
	                    HttpURLConnection.HTTP_NOT_FOUND,
	                    Constants.FAILED,
	                    e.getMessage()
	                ));
	        }
	    }

}
































