package ua.customermanager.ui;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction.KeyCode;
import ua.customermanager.backend.service.CustomerManagerService;
import ua.customermanager.common.model.Customer;

/**
 * Customer form.
 */
class CustomerForm extends CustomerFormDesign {

    private Customer customer;
    private CustomerManagerUI customerManagerUI;
    private CustomerManagerService customerManagerService;

    /**
     * Default constructor.
     *
     * @param customerManagerUI -   source UI
     */
    public CustomerForm(CustomerManagerUI customerManagerUI) {
        this.customerManagerUI = customerManagerUI;

        save.setClickShortcut(KeyCode.ENTER);
        save.addClickListener(e -> this.saveClickListener());
        delete.addClickListener(e -> this.deleteClickListener());
    }

    /**
     * Set {@link CustomerManagerService} instance.
     *
     * @param customerManagerService    -   source customer manager service
     */
    public void setCustomerManagerService(CustomerManagerService customerManagerService) {
        this.customerManagerService = customerManagerService;
    }

    /**
     * Set customer bean.
     *
     * @param customer  -    source
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;

        BeanFieldGroup.bindFieldsUnbuffered(customer, this);

        setVisible(true);

        firstName.selectAll();
    }

    /**
     * Delete button event click listener.
     */
    private void deleteClickListener() {
        customerManagerService.delete(customer);

        customerManagerUI.updateGridList();

        setVisible(false);
    }

    /**
     * Save button event click listener.
     */
    private void saveClickListener() {
        customerManagerService.save(customer);

        customerManagerUI.updateGridList();

        setVisible(false);
    }
}