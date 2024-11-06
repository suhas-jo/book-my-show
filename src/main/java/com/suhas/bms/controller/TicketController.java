package com.suhas.bms.controller;

import com.suhas.bms.dtos.RequestDto;
import com.suhas.bms.dtos.ResponseDto;
import com.suhas.bms.models.ShowSeat;
import com.suhas.bms.models.User;
import com.suhas.bms.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<ResponseDto> bookTicket(@RequestBody RequestDto requestDto){

       return new ResponseEntity<>(bookingService.bookShow(requestDto), HttpStatus.CREATED);

    }
}
