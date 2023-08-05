package com.beverages.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.beverages.service.exception.BeverageNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BeveragesServiceTest {
    @Autowired
    private BeveragesService beveragesService;

    
    @Test
    void testDeletebyId() {
        assertThrows(BeverageNotFoundException.class, () -> beveragesService.deletebyId(1L));
        assertThrows(BeverageNotFoundException.class, () -> beveragesService.deletebyId(2L));
        assertThrows(BeverageNotFoundException.class, () -> beveragesService.deletebyId(7L));
    }

    
    
}

