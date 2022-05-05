package com.fixedasset.controller;

import com.fixedasset.common.lang.Result;
import com.fixedasset.entity.Payment;
import com.fixedasset.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Resource PaymentService paymentService;

    @PostMapping("/save")
    public Result savePay(@RequestBody Payment payment) {
        payment.setPaymentTime(LocalDateTime.now());
        paymentService.save(payment);
        return Result.succ(payment);
    }
}
