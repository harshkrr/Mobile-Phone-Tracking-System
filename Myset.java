public class Myset{
	Node head;
	
	public Boolean IsEmpty() {
		if(this.head == null)
			return true;
		else 
			return false;
	}
	
	public Boolean IsMember(Object o){
		Node temp  = this.head;
		int n  =  0;
		while(temp != null) {
			if(temp.data == o ){
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
	// public void Insert(Object o) {
	// 	if(!(this.IsMember(o))){
	// 	    Node nd = new Node(o);
	// 		nd.next = this.head;
	// 		this.head = nd;
	// 	}
	// }

	public void Insert(Object o){
		if(!(this.isMember(0))){
	        Node temp = head;
			Node temp1 = new Node(o);
			if(head == null){
				head = temp1;
			}else{
				while(temp.next!=null){
					temp = temp.next;
				}
				temp.next = temp1;
			}
		}		  
	}

	public int numOfObject() {
	 	Node temp = head;
	 	int t = 0;
	 	while (temp!=null) {
	 		t++;
	 		temp = temp.next;
	 	} 
	 	return t;
	}
	 

	public Node ithObject(int i) {
	 	Node temp = head;
	 	for(int t=0; t<i; t++) {
	 		if(temp==null)
	 			break;
	 		temp=temp.next;
	 	} 
	 	return temp;
	}		  
	 
	 
	public void Delete(Object o) {
		Node curr = this.head;
		Node prev = null;
		while(curr !=null && curr.data != o){
			prev = curr;
			curr = curr.next;
		}
		if(curr!=null){
			prev.next = curr.next;
		}
	}
	 
	 
	public Myset Union(Myset a) { 
		Myset c = new Myset();
		Node temp = this.head;
		Node t = a.head;
		while(temp != null){ 
		    c.Insert(temp.data);
			temp=temp.next;
		}
		while(t != null ) {
			c.Insert(t.data);
			t =t.next;
		}
		return c;	 
	}
	 
	public Myset Intersection(Myset a) {
		Myset m = new Myset();
		Node temp = a.head;
		while(temp != null){
			if((this.IsMember(temp.data))){
				m.Insert(temp.data);
			}
			temp=temp.next;
		} 
		return m;
	}	
}
