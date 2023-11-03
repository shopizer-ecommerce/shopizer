package com.salesmanager.core.business.repositories.order.feedback;

import com.salesmanager.core.model.order.feedback.OrderFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link OrderFeedback} entities.
 */
public interface OrderFeedbackRepository extends JpaRepository<OrderFeedback, Long> {
}