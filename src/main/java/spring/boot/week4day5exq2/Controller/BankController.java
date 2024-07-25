package spring.boot.week4day5exq2.Controller;

import org.springframework.web.bind.annotation.*;
import spring.boot.week4day5exq2.Api.ApiBank;
import spring.boot.week4day5exq2.Model.Customers;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bank")
public class BankController {
    //Q2
    ArrayList<Customers> customers = new ArrayList<>();

    //Get
    @GetMapping("/get")
    public ArrayList<Customers> getCustomers() {
        return customers;
    }

    //Post
    @PostMapping("/add")
    public ApiBank addCustomers(@RequestBody Customers customers) {
        try {
            this.customers.add(customers);
            return new ApiBank("Success tasks add", "200");
        } catch (Exception e) {
            System.out.println("Error adding new customers");
        }
        return new ApiBank("Failed to add new customers", "400");
    }

    //Update
    @PutMapping("/update/{index}")
    public ApiBank updateCustomers(@PathVariable int index, @RequestBody Customers updateCustomers) {
        try {
            customers.set(index, updateCustomers);
            return new ApiBank("Success tasks update", "200");
        } catch (Exception e) {
            System.out.println("Error updating new customers");
        }
        return new ApiBank("Failed to update customers", "400");
    }

    //Delete
    @DeleteMapping("/delete/{index}")
    public ApiBank deleteCustomers(@PathVariable int index) {
        try {
            customers.remove(index);
            return new ApiBank("Success tasks delete", "200");
        } catch (Exception e) {
            System.out.println("Error deleting new customers");
        }
        return new ApiBank("Failed to delete customers", "400");
    }

    //Deposit money to customer
    @PutMapping("deposit/{id}/{amount}")
    public ApiBank deposit(@PathVariable int id, @PathVariable double amount) {
        try {
            Customers customer = customers.get(id);
            customer.setBalance(customer.getBalance() + amount);
            return new ApiBank("Success tasks deposit", "200");
        } catch (Exception e) {
            System.out.println("Error deposit customers");
        }
        return new ApiBank("Failed to deposit customers", "400");
    }

    //Withdraw money from customers
    @PutMapping("/withdraw/{id}/{amount}")
    public ApiBank withdraw(@PathVariable int id, @PathVariable double amount) {
        try {
            Customers customer = customers.get(id);
            if (customer.getBalance() >= amount) {
                customer.setBalance(customer.getBalance() - amount);
                return new ApiBank("Success tasks withdraw", "200");
            }
        } catch (Exception e) {
            System.out.println("Error withdraw customers");
        }
        return new ApiBank("You don't have enough money", "400");
    }
}
