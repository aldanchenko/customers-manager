package ua.customermanager.ui;

import javax.ejb.EJB;

import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import ua.customermanager.backend.service.CustomerManagerService;
import ua.customermanager.common.model.Customer;

import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@CDIUI("")
@Theme("valo")
public class CustomerManagerUI extends UI {

    @EJB
    CustomerManagerService customerManagerService;

    private Grid grid = new Grid();
    private CustomerForm customerForm = new CustomerForm(this);

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        customerForm.setCustomerManagerService(customerManagerService);

        final VerticalLayout layout = new VerticalLayout();

        Button addCustomerBtn = new Button("Add new customer");
        addCustomerBtn.addClickListener(e -> {
            grid.select(null);
            customerForm.setCustomer(new Customer());
        });

        HorizontalLayout toolbar = new HorizontalLayout(addCustomerBtn);
        toolbar.setSpacing(true);

        grid.setColumns("firstName", "lastName", "email", "phoneNumber");

        HorizontalLayout main = new HorizontalLayout(grid, customerForm);
        main.setSpacing(true);
        main.setSizeFull();
        grid.setSizeFull();
        main.setExpandRatio(grid, 1);

        layout.addComponents(toolbar, main);

        updateGridList();

        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);

        customerForm.setVisible(false);

        grid.addSelectionListener(event -> {
            if (event.getSelected().isEmpty()) {
                customerForm.setVisible(false);
            } else {
                Customer customer = (Customer) event.getSelected().iterator().next();

                customerForm.setCustomer(customer);
            }
        });
    }

    /**
     * Update Grid information.
     */
    void updateGridList() {
        List<Customer> customers = customerManagerService.getCustomers();

        grid.setContainerDataSource(new BeanItemContainer<>(Customer.class, customers));
    }
}
