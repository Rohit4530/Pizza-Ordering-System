package com.sides.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sides.entity.Sides;
import com.sides.service.exception.NullSideException;
import com.sides.service.exception.SideCannotUpdateException;
import com.sides.service.exception.SideNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class SidesServiceTest {
    @Autowired
    private SidesService sidesService;

    

    
    @Test
    void testFindBySideName() {
        assertThrows(SideNotFoundException.class, () -> sidesService.findBySideName("Name"));
    }

    
    @Test
    void testDeletebyId() {
        assertThrows(SideNotFoundException.class, () -> sidesService.deletebyId(101L));
        assertThrows(SideNotFoundException.class, () -> sidesService.deletebyId(202L));
        assertThrows(SideNotFoundException.class, () -> sidesService.deletebyId(405L));
    }

    
    @Test
    void testUpdateSides() {
        assertThrows(SideCannotUpdateException.class, () -> sidesService.updateSides(new Sides()));
    }

    @Test
    void testUpdateSides3() {
        

        sidesService.updateSides(new Sides(1L, "Side Cannot be Updated because No Id is provided",
                "Side Cannot be Updated because No Id is provided", 1L, 1L,
                "Side Cannot be Updated because No Id is provided", "https://example.org/example"));
    }

    
    @Test
    void testUpdateSides4() {
        

        Sides sides = new Sides();
        sides.setId(1L);
        sidesService.updateSides(sides);
    }

    
    @Test
    void testCreateSides() {
        assertThrows(NullSideException.class, () -> sidesService.createSides(new Sides()));
    }

    
    

    
    @Test
    void testCreateSides3() {
        

        sidesService.createSides(new Sides(1L, "Cannot add null side", "Cannot add null side", 1L, 1L,
                "Cannot add null side", "https://example.org/example"));
    }

    
    @Test
    void testCreateSides4() {
        Sides sides = new Sides();
        sides.setSideName("Cannot add null side");
        assertSame(sides, sidesService.createSides(sides));
    }


}

