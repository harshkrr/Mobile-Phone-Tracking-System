public class ExchangeList{
	linkedlist list  = new linkedlist();


	ExchangeList() {
	}

	public ExchangeList(linkedlist l){
		list = l;
	}

	public int getSize() {
		Node1 temp = list.head;
		int k = 0;
		while (temp!=null) {
			k++;
			temp = temp.next;
		} return k;
	}

	public boolean IsEmpty() {
		return (list.head==null);
	}

	public Boolean isMember(Exchange o){
		Node1 temp  = list.head;
		int n  =  0;
		while(temp != null) {
			if(temp.data == o){
				n++;
			}
			temp = temp.next;
		}
		if(n>0)
			return true;
		else{
			return false;
		}
	}

	public void Insert(Exchange o){
		list.add(o);	  
	}

	public void Insert(int a) {
		Exchange tn = new Exchange(a);
		list.add(tn);
	}


	public void Delete(Exchange a) {
		list.delete(a);
	}

	public Exchange findExchange(int a) {
		Node1 temp = list.head;
		for (int i = 0; i<a; i++) {
			if (temp == null)
				break;
			    else
			    temp = temp.next;
		} 
		if (temp!=null)
			return temp.data;
		else 
			return null;
	}
}