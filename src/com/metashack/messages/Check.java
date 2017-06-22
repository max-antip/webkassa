package com.metashack.messages;


import java.util.ArrayList;
import java.util.List;

public class Check implements Message {

    public static final String URL = "Check";

    public String token;
    public String cashBoxUniqueNumber;
    public Long operationType;
    public List<Position> positions;
    public List<Payment> payments;
    public Double change;
    public Integer roundType;
    public String externalCheckNumber;
    public String customerEmail;

    @Override
    public String getUrl() {
        return URL;
    }


    public static class Position {
        public Double count;
        public Double price;
        public Double tax;
        public Long taxType;
        public String positionName;
        public String positionCode;
        public Double discount;
        public Double markup;
        public String sectionCode;
        public Boolean isStorno;
        public Boolean markupDeleted;
        public Boolean discountDeleted;

    }

    public void addPosition(Position position) {
        if (positions == null) {
            positions = new ArrayList<>();
        }
        positions.add(position);
    }

    public void addPayment(Payment paym) {
        if (payments == null) {
            payments = new ArrayList<>();
        }
        payments.add(paym);
    }

    public static class Payment {
        public Double sum;
        public Long paymentType;

    }

}
