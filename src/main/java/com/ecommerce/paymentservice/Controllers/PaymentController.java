package com.ecommerce.paymentservice.Controllers;

import com.ecommerce.paymentservice.DTO.GeneratePaymentLinkRequestDTO;
import com.ecommerce.paymentservice.Services.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

       private  PaymentService paymentService;
       public PaymentController(@Qualifier("StripePaymentService") PaymentService paymentService) {
               this.paymentService = paymentService;
       }
        @PostMapping
        public String generatePaymentLink(@RequestBody GeneratePaymentLinkRequestDTO requestDTO) {

               try {
                        return paymentService.generatePaymentLink(requestDTO.getOrderId());
                }
                catch(RazorpayException | StripeException ex) { //ideally exceptions should be handled in a global exception handler
                        return ex.getMessage();
                }

        }

}
