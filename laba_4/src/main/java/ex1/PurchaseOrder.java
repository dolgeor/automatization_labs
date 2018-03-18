package ex1;

import java.math.BigDecimal;
import java.util.Date;
import java.util.StringJoiner;

public class PurchaseOrder {
    private Date transactionDate;
    private String product;
    private BigDecimal price;
    private String paymentType;
    private String name;
    private String city;
    private String state;
    private String country;
    private Date accountCreated;
    private Date lastLogin;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public PurchaseOrder(Date transactionDate, String product, BigDecimal price, String paymentType, String name, String city, String state, String country, Date accountCreated, Date lastLogin, BigDecimal latitude, BigDecimal longitude) {
        this.transactionDate = transactionDate;
        this.product = product;
        this.price = price;
        this.paymentType = paymentType;
        this.name = name;
        this.city = city;
        this.state = state;
        this.country = country;
        this.accountCreated = accountCreated;
        this.lastLogin = lastLogin;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner("\n", "-----------------------------------------------\n",
                "\n-----------------------------------------------\n\n");
        stringJoiner.add(String.format("%16s:%30s", "Transaction_Date", transactionDate))
                .add(String.format("%16s:%30s","Product",product))
                .add(String.format("%16s:%29s$","Price", price))
                .add(String.format("%16s:%30s","Payment_Type",paymentType))
                .add(String.format("%16s:%30s","Name",name))
                .add((String.format("%16s:%30s","City" , city)))
                .add((String.format("%16s:%30s","State" , state)))
                .add((String.format("%16s:%30s","Account_Created" , accountCreated)))
                .add((String.format("%16s:%30s","Last_Login" , lastLogin)))
                .add((String.format("%16s:%30s","Latitude" , latitude)))
                .add((String.format("%16s:%30s","Longitude", longitude)));

        return stringJoiner.toString();
    }
}
