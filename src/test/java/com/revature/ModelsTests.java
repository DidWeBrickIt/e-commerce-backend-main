package com.revature;

import com.revature.annotations.AuthRestriction;
import com.revature.models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ModelsTests {

    @Test
    void address_model_test(){
        Address address = new Address();

        address.setId(999);
        address.setUserid(999);
        address.setAddress1("address1");
        address.setAddress2("address2");
        address.setCity("city2");
        address.setState("state2");
        address.setZip(99999);
        address.setCountry("country2");

        Assertions.assertEquals(999, address.getId());
        Assertions.assertEquals(999, address.getUserid());
        Assertions.assertEquals("address1", address.getAddress1());
        Assertions.assertEquals("address2", address.getAddress2());
        Assertions.assertEquals("city2", address.getCity());
        Assertions.assertEquals("state2", address.getState());
        Assertions.assertEquals(99999, address.getZip());
        Assertions.assertEquals("country2", address.getCountry());
    }

    @Test
    void order_model_test(){
        Order order = new Order();

        order.setId(1);
        order.setUserId(1);
        order.setProdId(1);
        order.setQuantity(1);
        order.setTimePurchased(1);

        Assertions.assertEquals(1, order.getId());
        Assertions.assertEquals(1, order.getUserId());
        Assertions.assertEquals(1, order.getProdId());
        Assertions.assertEquals(1, order.getQuantity());
        Assertions.assertEquals(1, order.getTimePurchased());

        Assertions.assertEquals("Order{id=1, userId=1, prodId=1, quantity=1, timePurchased=1}", order.toString());
        Assertions.assertTrue(order.equals(order));
        Assertions.assertEquals(727250580, order.hashCode());

    }

    @Test
    void payment_model_test(){
        Payment pay = new Payment();
        Payment pay2 = new Payment(2,2,"2222222222222222", "12/22");

        pay.setId(1);
        pay.setUserid(1);
        pay.setCredit_card_number("1234123412341234");
        pay.setExpiration("12/22");

        Assertions.assertEquals(1, pay.getId());
        Assertions.assertEquals(1, pay.getUserid());
        Assertions.assertEquals("1234123412341234", pay.getCredit_card_number());
        Assertions.assertEquals("12/22", pay.getExpiration());

        Assertions.assertEquals("Payment(id=1, userid=1, credit_card_number=1234123412341234, expiration=12/22)", pay.toString());
        Assertions.assertTrue(pay.equals(pay));
        Assertions.assertEquals(1510498579, pay.hashCode());
    }

    @Test
    void product_model_test(){
        Product prod = new Product();

        prod.setId(1);
        prod.setQuantity(1);
        prod.setPrice(1.00);
        prod.setDescription("desc");
        prod.setImage("image");
        prod.setName("name");

        Assertions.assertEquals(1, prod.getId());
        Assertions.assertEquals(1, prod.getQuantity());
        Assertions.assertEquals(1.00, prod.getPrice());
        Assertions.assertEquals("desc", prod.getDescription());
        Assertions.assertEquals("image", prod.getImage());
        Assertions.assertEquals("name", prod.getName());

        Assertions.assertEquals("Product(id=1, quantity=1, price=1.0, description=desc, image=image, name=name)", prod.toString());
        Assertions.assertTrue(prod.equals(prod));
        Assertions.assertEquals(2034870322, prod.hashCode());
    }

    @Test
    void review_model_test(){
        Review rev = new Review();

        rev.setId(1);
        rev.setUserId(1);
        rev.setProdId(1);
        rev.setTimestamp(1);
        rev.setDescription("desc");
        rev.setRating(1);

        Assertions.assertEquals(1, rev.getId());
        Assertions.assertEquals(1, rev.getUserId());
        Assertions.assertEquals(1, rev.getProdId());
        Assertions.assertEquals(1, rev.getTimestamp());
        Assertions.assertEquals("desc", rev.getDescription());
        Assertions.assertEquals(1, rev.getRating());

        Assertions.assertEquals("Review(id=1, userId=1, prodId=1, timestamp=1, description=desc, rating=1)", rev.toString());
        Assertions.assertTrue(rev.equals(rev));
        Assertions.assertEquals(-38808915, rev.hashCode());

    }

    @Test
    void user_model_test(){
        User u = new User();

        u.setId(1);
        u.setEmail("email");
        u.setPassword("pass");
        u.setFirstName("first");
        u.setLastName("last");
        u.setImageurl("image");
        u.setAuthRestriction(AuthRestriction.USER);

        Assertions.assertEquals(1, u.getId());
        Assertions.assertEquals("email", u.getEmail());
        Assertions.assertEquals("pass", u.getPassword());
        Assertions.assertEquals("first", u.getFirstName());
        Assertions.assertEquals("last", u.getLastName());
        Assertions.assertEquals("image", u.getImageurl());
        Assertions.assertEquals(AuthRestriction.USER, u.getAuthRestriction());

        Assertions.assertEquals("User(id=1, email=email, password=pass, firstName=first, lastName=last, imageurl=image, authRestriction=USER)", u.toString());
        Assertions.assertTrue(u.equals(u));
        Assertions.assertNotEquals(0, u.hashCode());

    }
}
