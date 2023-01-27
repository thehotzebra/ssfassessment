package com.example.ssfassessment.model;

import java.io.Serializable;
import java.util.Random;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orderId;

    @NotNull(message = "Name cannot be null.")
    @Size(min = 3, message = "Minimum 3 characters.")
    private String name;

    @NotNull(message = "Address cannot be null.")
    private String address;

    @NotNull(message = "Phone number cannot be null.")
    @Size(min = 8, max = 8, message = "Phone number must be 8 digits.")
    private String phone;
    private boolean rush;
    private String comments;
    public String pizza;
    public String size;
    public Number quantity;
    private Number total;

    public Order() {
        this.orderId = generateId(8);
    }

    private synchronized String generateId(int numChars) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < numChars) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numChars);
    }
}
