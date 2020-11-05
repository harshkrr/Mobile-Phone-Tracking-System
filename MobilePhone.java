public class MobilePhone {
	int id;
	boolean checkstatus;
	Exchange base ;

	public MobilePhone(int n){
		id = n;
	}	

	public int number() {
		return id;
	}

	public boolean Status() {
		return checkstatus;
	}

	public void switchOn() {
		checkstatus = true;
	}

	public void switchOff() {
		checkstatus = false;
	}

	public void setlocation(Exchange a) {
		base = a;
	}

	class InvalidExchangeException extends Exception {
		InvalidExchangeException(String s){
			super(s);
		}
	}
	public Exchange location() throws InvalidExchangeException {
		if (checkstatus) {
			return base;
		}
		else 
			throw new InvalidExchangeException("Phone is switch off");
	}
}

