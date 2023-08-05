package com.pizzahub.coupon.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzahub.coupon.entity.Coupon;
import com.pizzahub.coupon.repo.CouponRepo;
import com.pizzahub.coupon.service.CouponService;

import java.util.ArrayList;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CouponController.class, CouponService.class})
@ExtendWith(SpringExtension.class)
class CouponControllerTest {
    @Autowired
    private CouponController couponController;

    @MockBean
    private CouponRepo couponRepo;

    /**
     * Method under test: {@link CouponController#addCoupon(Coupon)}
     */
    @Test
    void testAddCoupon3() throws Exception {
        Coupon coupon = new Coupon();
        coupon.setCouponDesc("Coupon Desc");
        coupon.setCouponName("Coupon Name");
        coupon.setCouponPrice(1L);
        coupon.setId(1L);
        coupon.setMinOrderAmount(1L);
        when(couponRepo.save((Coupon) any())).thenReturn(coupon);

        Coupon coupon1 = new Coupon();
        coupon1.setCouponDesc("Coupon Desc");
        coupon1.setCouponName("Coupon Name");
        coupon1.setCouponPrice(1L);
        coupon1.setId(1L);
        coupon1.setMinOrderAmount(1L);
        String content = (new ObjectMapper()).writeValueAsString(coupon1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/coupon/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(couponController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"couponName\":\"Coupon Name\",\"couponDesc\":\"Coupon Desc\",\"couponPrice\":1,\"minOrderAmount\":1}"));
    }

    /**
     * Method under test: {@link CouponController#applyCoupon(Long)}
     */
    @Test
    void testApplyCoupon2() throws Exception {
        Coupon coupon = new Coupon();
        coupon.setCouponDesc("Coupon Desc");
        coupon.setCouponName("Coupon Name");
        coupon.setCouponPrice(1L);
        coupon.setId(1L);
        coupon.setMinOrderAmount(1L);
        Optional<Coupon> ofResult = Optional.of(coupon);
        when(couponRepo.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/coupon/applyCoupon/{id}", 1L);
        MockMvcBuilders.standaloneSetup(couponController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    /**
     * Method under test: {@link CouponController#deleteCoupon(Long)}
     */
    @Test
    void testDeleteCoupon2() throws Exception {
        when(couponRepo.existsById((Long) any())).thenReturn(true);
        doNothing().when(couponRepo).deleteById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/coupon/delete/{id}", 1L);
        MockMvcBuilders.standaloneSetup(couponController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link CouponController#getApplicableCoupons(Long)}
     */
    @Test
    void testGetApplicableCoupons2() throws Exception {
        when(couponRepo.findByMinOrderAmountLessThanEqual((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/coupon/getApplicable/{orderAmount}",
                1L);
        MockMvcBuilders.standaloneSetup(couponController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CouponController#getApplicableCoupons(Long)}
     */
    @Test
    void testGetApplicableCoupons3() throws Exception {
        Coupon coupon = new Coupon();
        coupon.setCouponDesc("Coupon Desc");
        coupon.setCouponName("Coupon Name");
        coupon.setCouponPrice(1L);
        coupon.setId(1L);
        coupon.setMinOrderAmount(1L);
        Optional<Coupon> ofResult = Optional.of(coupon);
        when(couponRepo.findByCouponName((String) any())).thenReturn(ofResult);
        when(couponRepo.findByMinOrderAmountLessThanEqual((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/coupon/getApplicable/{orderAmount}",
                "", "Uri Variables");
        MockMvcBuilders.standaloneSetup(couponController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"couponName\":\"Coupon Name\",\"couponDesc\":\"Coupon Desc\",\"couponPrice\":1,\"minOrderAmount\":1}"));
    }

    /**
     * Method under test: {@link CouponController#getCouponById(Long)}
     */
    @Test
    void testGetCouponById2() throws Exception {
        Coupon coupon = new Coupon();
        coupon.setCouponDesc("Coupon Desc");
        coupon.setCouponName("Coupon Name");
        coupon.setCouponPrice(1L);
        coupon.setId(1L);
        coupon.setMinOrderAmount(1L);
        Optional<Coupon> ofResult = Optional.of(coupon);
        when(couponRepo.findById((Long) any())).thenReturn(ofResult);
        when(couponRepo.existsById((Long) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/coupon/get/{couponId}", 1L);
        MockMvcBuilders.standaloneSetup(couponController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"couponName\":\"Coupon Name\",\"couponDesc\":\"Coupon Desc\",\"couponPrice\":1,\"minOrderAmount\":1}"));
    }

    /**
     * Method under test: {@link CouponController#getCouponByName(String)}
     */
    @Test
    void testGetCouponByName2() throws Exception {
        Coupon coupon = new Coupon();
        coupon.setCouponDesc("Coupon Desc");
        coupon.setCouponName("Coupon Name");
        coupon.setCouponPrice(1L);
        coupon.setId(1L);
        coupon.setMinOrderAmount(1L);
        Optional<Coupon> ofResult = Optional.of(coupon);
        when(couponRepo.findByCouponName((String) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/coupon/{couponName}", "Coupon Name");
        MockMvcBuilders.standaloneSetup(couponController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"couponName\":\"Coupon Name\",\"couponDesc\":\"Coupon Desc\",\"couponPrice\":1,\"minOrderAmount\":1}"));
    }

    /**
     * Method under test: {@link CouponController#getallCoupons()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetallCoupons() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new CouponController()).getallCoupons();
    }

    /**
     * Method under test: {@link CouponController#getCouponByName(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCouponByName() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new CouponController()).getCouponByName("Coupon Name");
    }

    /**
     * Method under test: {@link CouponController#getCouponById(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCouponById() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new CouponController()).getCouponById(1L);
    }

    /**
     * Method under test: {@link CouponController#addCoupon(Coupon)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddCoupon() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        CouponController couponController = new CouponController();
        couponController.addCoupon(new Coupon(1L, "Coupon Name", "Coupon Desc", 1L, 1L));
    }

    /**
     * Method under test: {@link CouponController#addCoupon(Coupon)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddCoupon2() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new CouponController()).addCoupon(mock(Coupon.class));
    }

    /**
     * Method under test: {@link CouponController#getApplicableCoupons(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetApplicableCoupons() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new CouponController()).getApplicableCoupons(1L);
    }

    /**
     * Method under test: {@link CouponController#deleteCoupon(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteCoupon() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new CouponController()).deleteCoupon(1L);
    }

    /**
     * Method under test: {@link CouponController#getallCoupons()}
     */
    @Test
    void testGetallCoupons2() throws Exception {
        when(couponRepo.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/coupons");
        MockMvcBuilders.standaloneSetup(couponController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CouponController#getallCoupons()}
     */
    @Test
    void testGetallCoupons3() throws Exception {
        when(couponRepo.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/coupons");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(couponController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CouponController#updateCoupon(Coupon)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateCoupon() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        CouponController couponController = new CouponController();
        couponController.updateCoupon(new Coupon(1L, "Coupon Name", "Coupon Desc", 1L, 1L));
    }

    /**
     * Method under test: {@link CouponController#updateCoupon(Coupon)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateCoupon2() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new CouponController()).updateCoupon(mock(Coupon.class));
    }

    /**
     * Method under test: {@link CouponController#applyCoupon(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testApplyCoupon() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new CouponController()).applyCoupon(1L);
    }

    /**
     * Method under test: {@link CouponController#updateCoupon(Coupon)}
     */
    @Test
    void testUpdateCoupon3() throws Exception {
        Coupon coupon = new Coupon();
        coupon.setCouponDesc("Coupon Desc");
        coupon.setCouponName("Coupon Name");
        coupon.setCouponPrice(1L);
        coupon.setId(1L);
        coupon.setMinOrderAmount(1L);
        when(couponRepo.save((Coupon) any())).thenReturn(coupon);

        Coupon coupon1 = new Coupon();
        coupon1.setCouponDesc("Coupon Desc");
        coupon1.setCouponName("Coupon Name");
        coupon1.setCouponPrice(1L);
        coupon1.setId(1L);
        coupon1.setMinOrderAmount(1L);
        String content = (new ObjectMapper()).writeValueAsString(coupon1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/coupon/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(couponController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"couponName\":\"Coupon Name\",\"couponDesc\":\"Coupon Desc\",\"couponPrice\":1,\"minOrderAmount\":1}"));
    }
}

