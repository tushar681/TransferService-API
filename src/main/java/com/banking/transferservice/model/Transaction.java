package com.banking.transferservice.model;

import java.io.Serializable;
import java.util.Objects;

public class Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sourceAccountNumber;

	private String destinationAccountNumber;

	private Double amount;

	public String getSourceAccountNumber() {
		return sourceAccountNumber;
	}

	public void setSourceAccountNumber(String sourceAccountNumber) {
		this.sourceAccountNumber = sourceAccountNumber;
	}

	public String getDestinationAccountNumber() {
		return destinationAccountNumber;
	}

	public void setDestinationAccountNumber(String destinationAccountNumber) {
		this.destinationAccountNumber = destinationAccountNumber;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Transaction transaction = (Transaction) o;
		return Objects.equals(this.sourceAccountNumber, transaction.sourceAccountNumber)
				&& Objects.equals(this.destinationAccountNumber, transaction.destinationAccountNumber)
				&& Objects.equals(this.amount, transaction.amount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sourceAccountNumber, destinationAccountNumber, amount);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Transaction {\n");

		sb.append("    sourceAccountNumber: ").append(toIndentedString(sourceAccountNumber)).append("\n");
		sb.append("    destinationAccountNumber: ").append(toIndentedString(destinationAccountNumber)).append("\n");
		sb.append("    balance: ").append(toIndentedString(amount)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

}
