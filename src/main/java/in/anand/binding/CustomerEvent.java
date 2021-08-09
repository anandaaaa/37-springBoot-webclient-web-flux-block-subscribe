package in.anand.binding;

import java.util.Date;

public class CustomerEvent {
	
	public String CustomerEvent;
	public Date date;
	public String getCustomerEvent() {
		return CustomerEvent;
	}
	public void setCustomerEvent(String customerEvent) {
		CustomerEvent = customerEvent;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "CustomerEvent [CustomerEvent=" + CustomerEvent + ", date=" + date + "]";
	}
	public CustomerEvent(String customerEvent, Date date) {
		super();
		CustomerEvent = customerEvent;
		this.date = date;
	}
	
	

}
