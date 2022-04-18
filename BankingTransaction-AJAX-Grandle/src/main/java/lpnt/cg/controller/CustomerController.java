package lpnt.cg.controller;

import lpnt.cg.model.Customer;
import lpnt.cg.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/customers")
    private ModelAndView showList(Pageable pageable) {
        Iterable<Customer> customers = customerService.findAllBySuspendedIsFalse(pageable);
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
}
