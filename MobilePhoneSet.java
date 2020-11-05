public class MobilePhoneSet {
	Myset mobilephone = new Myset();

	public boolean IsEmpty() {
		return mobilephone.IsEmpty();
	}

	public void Insert(MobilePhone m) {
		mobilephone.Insert(m);	
	}
	public void Union(MobilePhoneSet m) {
		this.mobilephone.Union(m.mobilephone);
	}

	public void Delete(MobilePhone m) {
		mobilephone.Delete(m);
	}

	public int numMobile() {
		int c =0;
		Node head = mobilephone.head;
		while (head!=null){
			c++;
			head = head.next;
		}
		return c;
	}

	public MobilePhone returnMobile(int x) {
		MobilePhone ans = null;
		for (int i =0;i<this.numMobile();i++){
			if (this.ithMobile(i).id==x){
				ans = this.ithMobile(i);
				break;
			}
		}
		return ans;
	}

	public MobilePhone ithMobile(int i) {
		Node temp = mobilephone.ithObject(i);
		MobilePhone mt = (MobilePhone)temp.data;
		return mt;
	}

	public boolean IsMember(int a) {
		Node temp = mobilephone.head;
		int t = 0;
		while(temp!=null) {
			if(((MobilePhone)temp.data).id==a) {
				int k=1;
				break;
			} 
			temp = temp.next;
		} 
		if(t==1) 
			return true;
		else 
			return false;
	}

	public boolean IsMember(MobilePhone m) {
		return IsMember(m.id);
	}
}