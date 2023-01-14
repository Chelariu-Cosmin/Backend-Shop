package backend.springboot.online.shop.web;

import backend.springboot.online.shop.model.OrderDetails;
import backend.springboot.online.shop.service.OrderDetailsService;
import backend.springboot.online.shop.service.PaypalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orderDetails")
@Slf4j
public class PayPalController {

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";
    private final PaypalService paypalService;
    private final OrderDetailsService orderDetailsService;

    public PayPalController(PaypalService paypalService, OrderDetailsService orderDetailsService) {
        this.paypalService = paypalService;
        this.orderDetailsService = orderDetailsService;
    }

    @PostMapping("/pay")
    public String payment(@ModelAttribute("order") OrderDetails orderDetails) {

        OrderDetails _orderDetails = orderDetailsService.saveOrderDetails (orderDetails);
            log.info ("The order it was salved {}", orderDetails);

        return "register_success";
    }

//   @RequestMapping(value = "/pay", method = RequestMethod.POST)
//    public String payment(@ModelAttribute("order") OrderDetails order) {
//        try {
//            Payment payment = paypalService.createPayment (order.getPrice (), order.getCurrency (), order.getMethod (),
//                    order.getIntent (), order.getDescription (), "http://localhost:9090/" + CANCEL_URL,
//                    "http://localhost:9090/" + SUCCESS_URL);
//
//            OrderDetails orderDetails = orderDetailsService.saveOrderDetails (order);
//            log.info ("The order it was salved {}", order);
//
//            for (Links link : payment.getLinks ()) {
//                if (link.getRel ()
//                        .equals ("approval_url")) {
//                    return "redirect:" + link.getHref ();
//                }
//            }
//
//        } catch (PayPalRESTException e) {
//
//            e.printStackTrace ();
//        }
//        return "success";
//    }
//
//    @GetMapping(value = CANCEL_URL)
//    public String cancelPay() {
//        return "cancel";
//    }
//
//    @GetMapping(value = SUCCESS_URL)
//    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
//        try {
//            Payment payment = paypalService.executePayment (paymentId, payerId);
//            System.out.println (payment.toJSON ());
//            if (payment.getState ()
//                       .equals ("approved")) {
//                return "success";
//            }
//        } catch (PayPalRESTException e) {
//            System.out.println (e.getMessage ());
//        }
//        return "redirect:/";
//    }

    @GetMapping
    public String showOrderDetailsForm() {
        return "home";
    }
}
