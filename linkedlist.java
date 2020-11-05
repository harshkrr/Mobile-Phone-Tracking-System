class Node1 {
	Exchange data;
	Node1 next;

	Node1(Exchange d) {
		data = d;
		next = null;
	}
}

public class linkedlist{
	Node1 head;

	linkedlist() {
		head = null;
	}

	public Node1 reverseList(){
        Node1 prev = null;
        Node1 current = head;
        Node1 next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
        return head;
    }

	public Boolean isMember(Exchange o){
		Node1 temp  = this.head;
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

	public void add(Exchange o){
		if(!(this.isMember(o))){
	        Node1 temp = head;
			Node1 temp1 = new Node1(o);
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

	public void delete(Exchange o) throws NullPointerException {
		Node1  curr = this.head;
		Node1 prev = null;
		if(head.data == o) {
			head = head.next;
		} try {
			while (curr != null && curr.data != o) {
				prev = curr;
				curr = curr.next;
			} 
			if (curr != null) {
				prev.next = curr.next;
			}
		} catch (NullPointerException e) { }
	}
}