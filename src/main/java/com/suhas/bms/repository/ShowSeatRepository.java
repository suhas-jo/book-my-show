package com.suhas.bms.repository;

import com.suhas.bms.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {

    @Override
    List<ShowSeat> findAllById(Iterable<Long> longs);
}
