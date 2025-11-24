package com.example.aalittle.ToolRental.validation;

import com.example.aalittle.ToolRental.enums.ToolCodeEnum;
import com.example.aalittle.ToolRental.model.RentalTerm;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GlobalErrorHandlerTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidRentalTerm_NoViolations() {
        RentalTerm term = new RentalTerm(
                ToolCodeEnum.LADW,
                LocalDate.of(2024, 6, 3),
                5,
                10
        );
        Set<ConstraintViolation<RentalTerm>> violations = validator.validate(term);
        assertTrue(violations.isEmpty(), "Valid rental term should have no violations");
    }

    @Test
    void testRentalDays_Null_IsInvalid() {
        RentalTerm term = new RentalTerm(
                ToolCodeEnum.LADW,
                LocalDate.of(2024, 6, 3),
                null, // invalid!
                10
        );
        Set<ConstraintViolation<RentalTerm>> violations = validator.validate(term);
        assertFalse(violations.isEmpty(), "Null rental days should be invalid");
        assertEquals(1, violations.size());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("rentalDayCount")));
    }

    @Test
    void testRentalDays_Zero_IsInvalid() {
        RentalTerm term = new RentalTerm(
                ToolCodeEnum.LADW,
                LocalDate.of(2024, 6, 3),
                0,
                10
        );
        Set<ConstraintViolation<RentalTerm>> violations = validator.validate(term);
        assertFalse(violations.isEmpty(), "Rental days of 0 should be invalid");
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().contains("Rental day count must be 1 or more")));
    }

    @Test
    void testRentalDays_Negative_IsInvalid() {
        RentalTerm term = new RentalTerm(
                ToolCodeEnum.LADW,
                LocalDate.of(2024, 6, 3),
                -5,
                10
        );
        Set<ConstraintViolation<RentalTerm>> violations = validator.validate(term);
        assertFalse(violations.isEmpty(), "Negative rental days should be invalid");
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().contains("Rental day count must be 1 or more")));
    }

    @Test
    void testDiscount_TooHigh_IsInvalid() {
        RentalTerm term = new RentalTerm(
                ToolCodeEnum.LADW,
                LocalDate.of(2024, 6, 3),
                5,
                101 // invalid!
        );
        Set<ConstraintViolation<RentalTerm>> violations = validator.validate(term);
        assertFalse(violations.isEmpty(), "Discount over 100 should be invalid");
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().contains("Discount Percent must be 100 or under")));
    }

    @Test
    void testDiscount_Negative_IsInvalid() {
        RentalTerm term = new RentalTerm(
                ToolCodeEnum.LADW,
                LocalDate.of(2024, 6, 3),
                5,
                -10
        );
        Set<ConstraintViolation<RentalTerm>> violations = validator.validate(term);
        assertFalse(violations.isEmpty(), "Negative discount should be invalid");
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().contains("Discount Percent must be 0 or more")));
    }

    @Test
    void testDiscount_Zero_IsValid() {
        RentalTerm term = new RentalTerm(
                ToolCodeEnum.LADW,
                LocalDate.of(2024, 6, 3),
                5,
                0
        );
        Set<ConstraintViolation<RentalTerm>> violations = validator.validate(term);
        assertTrue(violations.isEmpty(), "Discount of 0% should be valid");
    }

    @Test
    void testDiscount_OneHundred_IsValid() {
        RentalTerm term = new RentalTerm(
                ToolCodeEnum.LADW,
                LocalDate.of(2024, 6, 3),
                5,
                100
        );
        Set<ConstraintViolation<RentalTerm>> violations = validator.validate(term);
        assertTrue(violations.isEmpty(), "Discount of 100% should be valid");
    }

    @Test
    void testToolCode_Null_IsInvalid() {
        RentalTerm term = new RentalTerm(
                null,
                LocalDate.of(2024, 6, 3),
                5,
                10
        );
        Set<ConstraintViolation<RentalTerm>> violations = validator.validate(term);
        assertFalse(violations.isEmpty(), "Null tool code should be invalid");
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("toolCode")));
    }

    @Test
    void testCheckoutDate_Null_IsInvalid() {
        RentalTerm term = new RentalTerm(
                ToolCodeEnum.LADW,
                null,
                5,
                10
        );
        Set<ConstraintViolation<RentalTerm>> violations = validator.validate(term);
        assertFalse(violations.isEmpty(), "Null checkout date should be invalid");
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("checkoutDate")));
    }

    @Test
    void testMultipleViolations_AllCaptured() {
        RentalTerm term = new RentalTerm(
                null,
                null,
                0,
                101
        );
        Set<ConstraintViolation<RentalTerm>> violations = validator.validate(term);
        assertFalse(violations.isEmpty(), "Multiple violations should be captured");
        assertTrue(violations.size() >= 4, "Should have at least 4 violations");
    }
}