package com.suhas.bms.service;

import com.suhas.bms.dtos.RequestDto;
import com.suhas.bms.dtos.ResponseDto;
import com.suhas.bms.dtos.ResponseStatus;
import com.suhas.bms.models.*;
import com.suhas.bms.repository.BookingRepository;
import com.suhas.bms.repository.ShowRepository;
import com.suhas.bms.repository.ShowSeatRepository;
import com.suhas.bms.repository.UserRepository;
import com.suhas.bms.strategies.AmountCalculationStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BookingService {

    UserRepository userRepository;
    ShowRepository showRepository;
    ShowSeatRepository showSeatRepository;
    BookingRepository bookingRepository;
    AmountCalculationStrategy strategy;

    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          BookingRepository bookingRepository,
                          AmountCalculationStrategy strategy) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
        this.strategy = strategy;
    }


/*
    1. Get user by userId;
    2. Get all the showseats using ids

    ***** BEGIN TXN ****
    3. Block all the seats if avaialable.
    4. if not available throw exception.
    ***** END TXN ******
    5. Create Booking Response and return.
     */

    public ResponseDto bookShow(RequestDto requestDto){

        Optional<User> optionalUser = userRepository.findById(requestDto.getUserId());

        if(optionalUser.isEmpty()){
            throw new RuntimeException("User not Found");
        }

        User bookedBy = optionalUser.get();
        log.info("Booked by : {} ", bookedBy);

        Optional<Show> optionalShow = showRepository.findById(requestDto.getShowId());
        if(optionalShow.isEmpty()){
            throw new RuntimeException("Show does not exist.");
        }

        Show bookedShow = optionalShow.get();
        log.info("Show details {} " , bookedShow);
        List<ShowSeat> showSeats = getAndLockShowSeats(requestDto.getSeatIds());

        Booking booking = new Booking();
        booking.setBookedAt(new Date());
        booking.setShow(bookedShow);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setUser(bookedBy);
        booking.setSeatList(showSeats);

        Booking booking1 = bookingRepository.save(booking);
        int amount = strategy.getAmount();
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        responseDto.setBookingId(booking1.getId());
        responseDto.setAmount(amount);
        return responseDto;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    private List<ShowSeat> getAndLockShowSeats(List<Long> seatIds) {

        List<ShowSeat> showSeats = showSeatRepository.findAllById(seatIds);

        for(ShowSeat showSeat : showSeats){
            if(!((showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) ||
                    (showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED) &&
                           Duration.between(showSeat.getBlockedAt().toInstant(),LocalDate.now()).toMinutes() > 15))){
                throw new RuntimeException("Something Went Wrong...");
            }
        }

        List<ShowSeat> bookedShowSeat = new ArrayList<>();
        for(ShowSeat showSeat : showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeat.setBlockedAt(new Date());
            bookedShowSeat.add(showSeat);
        }

        showSeatRepository.saveAll(bookedShowSeat);
        return bookedShowSeat;
    }

}
