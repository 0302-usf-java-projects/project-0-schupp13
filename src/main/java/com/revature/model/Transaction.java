package com.revature.model;

public class Transaction {

	public String transType;
	public int toAccount;
	public int fromAccount;
	public float Amount;
	public int id;
	
	public Transaction( int id, String transType, int fromAccount,int toAccount,  float amount) {
		super();
		this.id = id;
		this.transType = transType;
		this.toAccount = toAccount;
		this.fromAccount = fromAccount;
		this.Amount = amount;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getTransType() {
		return transType;
	}
	
	public void setTransType(String transType) {
		this.transType = transType;
	}
	
	public int getToAccount() {
		return toAccount;
	}
	
	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}
	
	public int getFromAccount() {
		return fromAccount;
	}
	
	public void setFromAccount(int fromAccount) {
		this.fromAccount = fromAccount;
	}
	
	public float getAmount() {
		return Amount;
	}
	
	public void setAmount(float amount) {
		Amount = amount;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(Amount);
		result = prime * result + fromAccount;
		result = prime * result + toAccount;
		result = prime * result + ((transType == null) ? 0 : transType.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (Float.floatToIntBits(Amount) != Float.floatToIntBits(other.Amount))
			return false;
		if (fromAccount != other.fromAccount)
			return false;
		if (toAccount != other.toAccount)
			return false;
		if (transType == null) {
			if (other.transType != null)
				return false;
		} else if (!transType.equals(other.transType))
			return false;
		return true;
	}
	
}
