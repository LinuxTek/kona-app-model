package com.linuxtek.kona.app.sales.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KInvoice extends KEntityObject {

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.id
	 *
	 * @return the value of invoice.id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Long getId();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.id
	 *
	 * @param id the value for invoice.id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setId(Long id);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.app_id
	 *
	 * @return the value of invoice.app_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Long getAppId();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.app_id
	 *
	 * @param appId the value for invoice.app_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setAppId(Long appId);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.user_id
	 *
	 * @return the value of invoice.user_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Long getUserId();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.user_id
	 *
	 * @param userId the value for invoice.user_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setUserId(Long userId);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.account_id
	 *
	 * @return the value of invoice.account_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Long getAccountId();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.account_id
	 *
	 * @param accountId the value for invoice.account_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setAccountId(Long accountId);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.currency_id
	 *
	 * @return the value of invoice.currency_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Long getCurrencyId();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.currency_id
	 *
	 * @param currencyId the value for invoice.currency_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setCurrencyId(Long currencyId);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.invoice_no
	 *
	 * @return the value of invoice.invoice_no
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getInvoiceNo();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.invoice_no
	 *
	 * @param invoiceNo the value for invoice.invoice_no
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setInvoiceNo(String invoiceNo);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.start_balance
	 *
	 * @return the value of invoice.start_balance
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	BigDecimal getStartBalance();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.start_balance
	 *
	 * @param startBalance the value for invoice.start_balance
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setStartBalance(BigDecimal startBalance);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.end_balance
	 *
	 * @return the value of invoice.end_balance
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	BigDecimal getEndBalance();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.end_balance
	 *
	 * @param endBalance the value for invoice.end_balance
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setEndBalance(BigDecimal endBalance);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.subtotal
	 *
	 * @return the value of invoice.subtotal
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	BigDecimal getSubtotal();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.subtotal
	 *
	 * @param subtotal the value for invoice.subtotal
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setSubtotal(BigDecimal subtotal);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.tax
	 *
	 * @return the value of invoice.tax
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	BigDecimal getTax();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.tax
	 *
	 * @param tax the value for invoice.tax
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setTax(BigDecimal tax);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.shipping
	 *
	 * @return the value of invoice.shipping
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	BigDecimal getShipping();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.shipping
	 *
	 * @param shipping the value for invoice.shipping
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setShipping(BigDecimal shipping);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.discount
	 *
	 * @return the value of invoice.discount
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	BigDecimal getDiscount();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.discount
	 *
	 * @param discount the value for invoice.discount
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setDiscount(BigDecimal discount);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.total
	 *
	 * @return the value of invoice.total
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	BigDecimal getTotal();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.total
	 *
	 * @param total the value for invoice.total
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setTotal(BigDecimal total);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.amount_due
	 *
	 * @return the value of invoice.amount_due
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	BigDecimal getAmountDue();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.amount_due
	 *
	 * @param amountDue the value for invoice.amount_due
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setAmountDue(BigDecimal amountDue);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.amount_paid
	 *
	 * @return the value of invoice.amount_paid
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	BigDecimal getAmountPaid();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.amount_paid
	 *
	 * @param amountPaid the value for invoice.amount_paid
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setAmountPaid(BigDecimal amountPaid);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.paid
	 *
	 * @return the value of invoice.paid
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	boolean isPaid();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.paid
	 *
	 * @param paid the value for invoice.paid
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setPaid(boolean paid);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.closed
	 *
	 * @return the value of invoice.closed
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	boolean isClosed();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.closed
	 *
	 * @param closed the value for invoice.closed
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setClosed(boolean closed);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.created_date
	 *
	 * @return the value of invoice.created_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Date getCreatedDate();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.created_date
	 *
	 * @param createdDate the value for invoice.created_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setCreatedDate(Date createdDate);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.invoice_date
	 *
	 * @return the value of invoice.invoice_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Date getInvoiceDate();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.invoice_date
	 *
	 * @param invoiceDate the value for invoice.invoice_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setInvoiceDate(Date invoiceDate);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.due_date
	 *
	 * @return the value of invoice.due_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Date getDueDate();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.due_date
	 *
	 * @param dueDate the value for invoice.due_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setDueDate(Date dueDate);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.paid_date
	 *
	 * @return the value of invoice.paid_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Date getPaidDate();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.paid_date
	 *
	 * @param paidDate the value for invoice.paid_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setPaidDate(Date paidDate);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.closed_date
	 *
	 * @return the value of invoice.closed_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Date getClosedDate();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.closed_date
	 *
	 * @param closedDate the value for invoice.closed_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setClosedDate(Date closedDate);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.payment_attempted
	 *
	 * @return the value of invoice.payment_attempted
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	boolean isPaymentAttempted();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.payment_attempted
	 *
	 * @param paymentAttempted the value for invoice.payment_attempted
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setPaymentAttempted(boolean paymentAttempted);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.payment_attempt_count
	 *
	 * @return the value of invoice.payment_attempt_count
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Integer getPaymentAttemptCount();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.payment_attempt_count
	 *
	 * @param paymentAttemptCount the value for invoice.payment_attempt_count
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setPaymentAttemptCount(Integer paymentAttemptCount);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.last_payment_attempt_date
	 *
	 * @return the value of invoice.last_payment_attempt_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Date getLastPaymentAttemptDate();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.last_payment_attempt_date
	 *
	 * @param lastPaymentAttemptDate the value for invoice.last_payment_attempt_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setLastPaymentAttemptDate(Date lastPaymentAttemptDate);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.next_payment_attempt_date
	 *
	 * @return the value of invoice.next_payment_attempt_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Date getNextPaymentAttemptDate();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.next_payment_attempt_date
	 *
	 * @param nextPaymentAttemptDate the value for invoice.next_payment_attempt_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setNextPaymentAttemptDate(Date nextPaymentAttemptDate);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.payment_card_last4
	 *
	 * @return the value of invoice.payment_card_last4
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getPaymentCardLast4();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.payment_card_last4
	 *
	 * @param paymentCardLast4 the value for invoice.payment_card_last4
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setPaymentCardLast4(String paymentCardLast4);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.payment_ref
	 *
	 * @return the value of invoice.payment_ref
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getPaymentRef();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.payment_ref
	 *
	 * @param paymentRef the value for invoice.payment_ref
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setPaymentRef(String paymentRef);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.notes
	 *
	 * @return the value of invoice.notes
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getNotes();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.notes
	 *
	 * @param notes the value for invoice.notes
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setNotes(String notes);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice.last_updated
	 *
	 * @return the value of invoice.last_updated
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Date getLastUpdated();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice.last_updated
	 *
	 * @param lastUpdated the value for invoice.last_updated
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setLastUpdated(Date lastUpdated);

}