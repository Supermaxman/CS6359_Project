package domain.user;

import java.sql.Date;

public class CreditCard {
	private Integer cardId;
	private String number;
	private Date expDate;
	private String ccv;
	
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public String getCcv() {
		return ccv;
	}
	public void setCcv(String ccv) {
		this.ccv = ccv;
	}
}
