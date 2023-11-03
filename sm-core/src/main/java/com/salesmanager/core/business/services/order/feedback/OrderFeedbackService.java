package com.salesmanager.core.business.services.order.feedback;

import com.salesmanager.core.business.repositories.order.feedback.OrderFeedbackRepository;
import com.salesmanager.core.model.order.feedback.OrderFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing {@link OrderFeedback} entities.
 */
@Service
public class OrderFeedbackService {

    private final OrderFeedbackRepository orderFeedbackRepository;

    /**
     * Constructs an {@code OrderFeedbackService} with the given repository.
     *
     * @param orderFeedbackRepository The repository for order feedback.
     */
    @Autowired
    public OrderFeedbackService(OrderFeedbackRepository orderFeedbackRepository) {
        this.orderFeedbackRepository = orderFeedbackRepository;
    }

    /**
     * Creates a new order feedback.
     *
     * @param orderFeedback The order feedback to create.
     * @return The created order feedback.
     */
    public OrderFeedback createFeedback(OrderFeedback orderFeedback) {
        return orderFeedbackRepository.save(orderFeedback);
    }

    /**
     * Updates an existing order feedback.
     *
     * @param feedbackId      The identifier of the order feedback to update.
     * @param updatedFeedback The updated order feedback data.
     * @return The updated order feedback.
     */
    public OrderFeedback updateFeedback(Long feedbackId, OrderFeedback updatedFeedback) {
        Optional<OrderFeedback> existingFeedback = orderFeedbackRepository.findById(feedbackId);
        if (existingFeedback.isPresent()) {
            updatedFeedback.setId(feedbackId);
            return orderFeedbackRepository.save(updatedFeedback);
        }
        return null; // or throw an exception if feedback is not found
    }

    /**
     * Deletes an existing order feedback.
     *
     * @param feedbackId The identifier of the order feedback to delete.
     */
    public void deleteFeedback(Long feedbackId) {
        orderFeedbackRepository.findById(feedbackId).ifPresent(orderFeedbackRepository::delete);
    }
}
