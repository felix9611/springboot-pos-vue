package com.fixedasset.controller;

import com.fixedasset.common.lang.Result;
import com.fixedasset.entity.Payment;
import com.fixedasset.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Resource PaymentService paymentService;

    @PostMapping("/save")
    public Result savePay(@RequestBody Payment payment) {
        LocalDateTime date = LocalDateTime.now();
        payment.setPaymentTime(date);
        paymentService.save(payment);
        return Result.succ(payment);
    }

    @GetMapping("/list/{invoiceId}")
    public Result listItem(@PathVariable("invoiceId")int invoiceId) {
        return Result.succ(paymentService.listByInvId(invoiceId));
    }
}
