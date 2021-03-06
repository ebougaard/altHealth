package com.bharath.flightreservation.repos;

import com.bharath.flightreservation.entities.Appointment;
import com.bharath.flightreservation.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;



@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	@Query("from Appointment where dateOfAppointment=:dateOfAppointment")
	List<Appointment> findAppointments(
	        @Param("dateOfAppointment") Date departureDate);
}
