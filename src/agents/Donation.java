package agents;

import java.util.Date;
public class Donation {
	
	private int id_donation;
	private Date Date_Donation;
	private boolean result;
	
	public Donation(int id_donation, Date date_Donation, boolean result) {
		this.id_donation = id_donation;
		Date_Donation = date_Donation;
		this.result = result;
	}

	@Override
	public String toString() {
		return "Donation [id_donation=" + id_donation + ", Date_Donation=" + Date_Donation + ", result=" + result + "]";
	}

	public int getId_donation() {
		return id_donation;
	}

	public void setId_donation(int id_donation) {
		this.id_donation = id_donation;
	}

	public Date getDate_Donation() {
		return Date_Donation;
	}

	public void setDate_Donation(Date date_Donation) {
		Date_Donation = date_Donation;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
	
}
