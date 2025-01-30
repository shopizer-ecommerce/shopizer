package com.salesmanager.core.model.order.feedback;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesmanager.core.model.generic.SalesManagerEntity;
import com.salesmanager.core.model.order.Order;

/**
 * Represents feedback for an order in the system.
 */
@Entity
@Table (name="ORDER_FEEDBACK" )
public class OrderFeedback extends SalesManagerEntity<Long, OrderFeedback> {
    private static final long serialVersionUID = 1L;

    @Id
    @Column (name="ORDER_FEEDBACK_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "ORDER_FEEDBACK_ID_NEXT_VALUE")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column (name="COMMENTS")
    private String comments;

    @Column (name="RATING")
    private int rating;

    @JsonIgnore
    @OneToOne(targetEntity = Order.class)
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    public OrderFeedback() {
    }

    /**
     * Gets the unique identifier for the order feedback.
     *
     * @return The order feedback identifier.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the order feedback.
     *
     * @param id The order feedback identifier.
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * Gets the comments provided in the feedback.
     *
     * @return The feedback comments.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the comments for the feedback.
     *
     * @param comments The feedback comments.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Gets the rating given in the feedback.
     *
     * @return The feedback rating.
     */
    public int rating() {
        return rating;
    }

    /**
     * Sets the rating for the feedback.
     *
     * @param rating The feedback rating.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Gets the order associated with the feedback.
     *
     * @return The associated order.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets the order associated with the feedback.
     *
     * @param order The associated order.
     */
    public void setOrder(Order order) {
        this.order = order;
    }

}
