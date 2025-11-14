package com.nawaz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nawaz.entity.BooksModule;
import com.nawaz.entity.Order;

@Repository
public interface OrderModuleRepo extends JpaRepository<Order, Long> {
	
	
    //Custom query to find all orders placed by a specific customer within the last 7 days.
   // This is useful for restricting non-prime users from placing  more than one order per week.
	@Query(value = "SELECT * FROM orders o WHERE o.customer_id = :customerId AND o.created_date > CURDATE() - INTERVAL 7 DAY", nativeQuery = true)
	List<Order> findAnyLastweekPlaced(@Param("customerId")Long customerId);


	  
	    //whether a book with the given title actually exists or not.
	  @Query(value = "SELECT b FROM BooksModule b WHERE b.title = :title")
	  public BooksModule findByBookName(String title);

}
																																								