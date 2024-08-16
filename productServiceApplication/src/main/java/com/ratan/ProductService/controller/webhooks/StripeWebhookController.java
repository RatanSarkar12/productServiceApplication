package com.ratan.ProductService.controller.webhooks;

import com.stripe.net.Webhook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.events.Event;

@RestController
@RequestMapping("/webhooks/stripe")
public class StripeWebhookController {
    @GetMapping("/")
    public void handleWebhookRequest(@RequestBody Event eventWebhook){

    }
}
