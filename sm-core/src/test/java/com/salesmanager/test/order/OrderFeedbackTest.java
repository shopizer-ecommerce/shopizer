package com.salesmanager.test.order;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

import com.salesmanager.core.business.repositories.order.feedback.OrderFeedbackRepository;
import com.salesmanager.core.business.services.order.feedback.OrderFeedbackService;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.order.feedback.OrderFeedback;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

/**
 * Test class for {@link OrderFeedbackService}.
 */
public class OrderFeedbackTest {

    private OrderFeedbackService orderFeedbackService;
    private OrderFeedbackRepository orderFeedbackRepository;

    private Order mockOrder;

    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void setUp() {
        orderFeedbackRepository = mock(OrderFeedbackRepository.class);
        orderFeedbackService = new OrderFeedbackService(orderFeedbackRepository);
    }

    /**
     * Test case for the {@code createFeedback} method.
     */
    @Test
    public void testCreateFeedback() {
        OrderFeedback orderFeedback = new OrderFeedback();
        mockOrder = mock(Order.class);
        orderFeedback.setId(123L);
        orderFeedback.setComments("This is a test comment");
        orderFeedback.setRating(3);
        orderFeedback.setOrder(mockOrder);
        when(orderFeedbackRepository.save(orderFeedback)).thenReturn(orderFeedback);

        OrderFeedback createdFeedback = orderFeedbackService.createFeedback(orderFeedback);

        assertEquals(orderFeedback, createdFeedback);
        verify(orderFeedbackRepository, times(1)).save(orderFeedback);
    }

    /**
     * Test case for the {@code updateFeedback} method.
     */
    @Test
    public void testUpdateFeedback() {
        Long feedbackId = 123L;
        OrderFeedback existingFeedback = new OrderFeedback();

        when(orderFeedbackRepository.findById(feedbackId)).thenReturn(Optional.of(existingFeedback));

        OrderFeedback updatedFeedback = new OrderFeedback();
        updatedFeedback.setId(feedbackId);
        updatedFeedback.setComments("Updated comments");
        updatedFeedback.setRating(4);

        when(orderFeedbackRepository.save(updatedFeedback)).thenReturn(updatedFeedback);

        OrderFeedback result = orderFeedbackService.updateFeedback(feedbackId, updatedFeedback);

        assertEquals("Updated comments", result.getComments());
        assertEquals(4, result.rating());
        verify(orderFeedbackRepository, times(1)).findById(feedbackId);
        verify(orderFeedbackRepository, times(1)).save(updatedFeedback);
    }

    /**
     * Test case for the {@code deleteFeedback} method.
     */
    @Test
    public void testDeleteFeedback() {
        Long feedbackId = 123L;
        OrderFeedback existingFeedback = new OrderFeedback();
        existingFeedback.setId(feedbackId);

        when(orderFeedbackRepository.findById(feedbackId)).thenReturn(Optional.of(existingFeedback));

        orderFeedbackService.deleteFeedback(feedbackId);

        verify(orderFeedbackRepository, times(1)).findById(feedbackId);
        verify(orderFeedbackRepository, times(1)).delete(existingFeedback);
    }
}
