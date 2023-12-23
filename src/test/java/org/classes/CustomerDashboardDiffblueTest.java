package org.classes;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class CustomerDashboardDiffblueTest {
    /**
     * Method under test: {@link CustomerDashboard#printProducts()}
     */
    @Test
    void testPrintProducts() {
        // Arrange
        CustomerDashboard customerDashboard = new CustomerDashboard();

        // Act
        customerDashboard.printProducts();

        // Assert that nothing has changed
        assertNull(customerDashboard.getCustomer());
    }
}
