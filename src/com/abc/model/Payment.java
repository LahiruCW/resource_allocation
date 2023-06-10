package com.abc.model;

//importing necessary packages
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 * Represents a payment for a booking.
 *
 * @author LahiruCW
 * @version 1.0
 */
public class Payment {

    private String paymentId;
    private Booking booking;
    private String cardNumber;
    private LocalDate expirationDate;
    private int cvv;

    /**
     * Constructs a new Payment object with the specified payment ID, booking,
     * card number, expiration date, and CVV.
     *
     * @param paymentId the payment ID, which must not be changed after the
     * object is created
     * @param booking the booking associated with this payment, which must not
     * be changed after the object is created
     * @param cardNumber the card number for this payment
     * @param expirationDate the expiration date for the card
     * @param cvv the CVV for the card
     */
    public Payment(String paymentId, Booking booking, String cardNumber, LocalDate expirationDate, int cvv) {

        //payment ID is system generated. must not be changed after creation of the object
        this.paymentId = paymentId;

        //booking must not be changed after setting.
        //customer/admin is not allowed to make the changes to the payment that he or she booked
        //no setter method
        //validation code to check the booking object whether null or not
        if (booking != null) {
            this.booking = booking;
        }else {
            JOptionPane.showMessageDialog(null, "First You Need To Book Hall/Halls", "Error", JOptionPane.ERROR_MESSAGE);
        }

        setCardNumber(cardNumber);
        setExpirationDate(expirationDate);
        setCvv(cvv);
    }

    /**
     * Returns the payment ID.
     *
     * @return the payment ID
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * Returns the associated booking.
     *
     * @return the booking
     */
    public Booking getBooking() {
        return booking;
    }

    /**
     * Returns the card number.
     *
     * @return the card number
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the card number for this payment.
     *
     * @param cardNumber the card number to set
     * @throws IllegalArgumentException if the card number is invalid
     */
    public void setCardNumber(String cardNumber) {
        if (isValidCardNumber(cardNumber)) {
            this.cardNumber = cardNumber;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid card number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Returns the card's expiration date.
     *
     * @return the expiration date
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the card's expiration date.
     *
     * @param expirationDate the expiration date to set
     * @throws IllegalArgumentException if the expiration date is invalid
     */
    public void setExpirationDate(LocalDate expirationDate) {
        if (isValidExpirationDate(expirationDate)) {
            this.expirationDate = expirationDate;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid expiration date", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Returns the card's CVV.
     *
     * @return the CVV
     */
    public int getCvv() {
        return cvv;
    }

    /**
     * Sets the card's CVV.
     *
     * @param cvv the CVV to set
     * @throws IllegalArgumentException if the CVV is invalid
     */
    public void setCvv(int cvv) {
        if (isValidCVV(cvv)) {
            this.cvv = cvv;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid CVV", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //seperation of the validations //DO NOT MODIFY THE CODE
    private boolean isValidCardNumber(String cardNumber) {
        //use the Luhn algorithm to validate the card number

        //Remove any non-digit characters from the card number
        cardNumber = cardNumber.replaceAll("[^0-9]", "");

        //Reverse the card number
        StringBuilder sb = new StringBuilder(cardNumber);
        sb = sb.reverse();
        String reversedCardNumber = sb.toString();

        int sum = 0;

        for (int i = 0; i < reversedCardNumber.length(); i++) {
            int digit = Character.getNumericValue(reversedCardNumber.charAt(i));

            if (i % 2 == 1) {

                //double every other digits
                digit *= 2;

                if (digit > 9) {

                    //Add the digits of the doubled number
                    digit = digit / 10 + digit % 10;
                }
            }

            sum += digit;
        }

        return sum % 10 == 0;
    }

    private boolean isValidExpirationDate(LocalDate expirationDate) {

        //check if the expiration date is in the future
        return expirationDate.isAfter(LocalDate.now());
    }

    private boolean isValidCVV(int cvv) {
        //check if the CVV is a 3 or 4 digit number
        return cvv >= 100 && cvv < 9999;
    }
}
