package com.scaler.paymentService.services.paymentgateway;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripePaymentGateway implements PaymentGateway{

    @Value("${STRIPE_SECRET_KEY}")
    private String stripeSecretKey;
    @Override
    public String generatePaymentLink(String orderId, String email, String phoneNumber, Long amount){
        try {
//            Stripe.apiKey = "sk_test_51OgVYESEoxAXcCTuJ5hWrfQ4yGQTMHwUXqoRDkEnIRGueTbxnc3PmLkBXrfPwRWd4TQy5LMyvxesQJhlnNfxwjsD00cT9TBvPJ";

            Stripe.apiKey = stripeSecretKey;

//            PriceCreateParams params1 =
//                    PriceCreateParams.builder()
//                            .setCurrency("usd")
//                            .setUnitAmount(1000L)
//                            .setRecurring(
//                                    PriceCreateParams.Recurring.builder()
//                                            .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
//                                            .build()
//                            )
//                            .setProductData(
//                                    PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
//                            )
//                            .build();

            Map<String, Object> params1 = new HashMap<>();
            params1.put("unit_amount", amount);
            params1.put("currency", "inr");

            Map<String, Object> productData = new HashMap<>();

            productData.put("name", "iPad mini");

            params1.put("product_data", productData);

            Price price = Price.create(params1);


            PaymentLinkCreateParams params =
                    PaymentLinkCreateParams.builder()
                            .addLineItem(
                                    PaymentLinkCreateParams.LineItem.builder()
                                            .setPrice(price.getId())
                                            .setQuantity(1L)
                                            .build()
                            )
                            .build();

//        PaymentLink paymentLink = PaymentLink.create(params);

            PaymentLink paymentLink = PaymentLink.create(params);

            return paymentLink.toString();

        }catch(Exception e){
            System.out.println(e.getMessage());
            return "Something went wrong";
        }


    }
}
