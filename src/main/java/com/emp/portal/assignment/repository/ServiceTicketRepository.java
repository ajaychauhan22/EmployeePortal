package com.emp.portal.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emp.portal.assignment.entity.ServiceTicket;

/**
 * 
 * @author ajaychauhan01
 *
 */
@Repository
public interface ServiceTicketRepository extends JpaRepository<ServiceTicket, Long> {

}
