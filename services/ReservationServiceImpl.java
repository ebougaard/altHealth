package com.bharath.flightreservation.services;

import com.bharath.flightreservation.entities.Appointment;
import com.bharath.flightreservation.repos.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.bharath.flightreservation.dto.ReservationRequest;

import com.bharath.flightreservation.entities.Passenger;
import com.bharath.flightreservation.entities.Reservation;
import com.bharath.flightreservation.repos.PassengerRepository;
import com.bharath.flightreservation.repos.ReservationRepository;
import com.bharath.flightreservation.util.EmailUtil;
import com.bharath.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("${com.bharath.flightreservation.itinerary.dirpath}")
	private String ITINERARY_DIR;

/*	@Autowired
	FlightRepository flightRepository;*/

	@Autowired
	AppointmentRepository appointmentRepository;

	@Autowired
	PassengerRepository passengerRepository;

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	PDFGenerator pdfGenerator;

	@Autowired
	EmailUtil emailUtil;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Override
	//@Transactional
	public Reservation bookFlight(ReservationRequest request) {

		LOGGER.info("Inside bookFlight()");
		// Make Payment

		Long appointmentId = request.getappointmentId();
		//Long appointmentId = request.getappointmentId();
		LOGGER.info("Fetching  flight for flight id:" + appointmentId);
		Appointment appointment = appointmentRepository.findOne(appointmentId);

		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		LOGGER.info("Saving the passenger:" + passenger);
		Passenger savedPassenger = passengerRepository.save(passenger);

		Reservation reservation = new Reservation();
		reservation.setAppointment(appointment);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);

		LOGGER.info("Saving the reservation:" + reservation);
		Reservation savedReservation = reservationRepository.save(reservation);

		String filePath = ITINERARY_DIR + savedReservation.getId()
				+ ".pdf";
		LOGGER.info("Generating  the itinerary");
		pdfGenerator.generateItinerary(savedReservation, filePath);
		LOGGER.info("Emailing the Itinerary");
		emailUtil.sendItinerary(passenger.getEmail(), filePath);

		return savedReservation;
	}

}
