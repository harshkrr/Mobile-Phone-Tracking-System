public class Exchange {
	int id;
	Exchange parent = null;
	ExchangeList children = new ExchangeList();
	MobilePhoneSet mps = new MobilePhoneSet();

	public Exchange(int n) {
		id = n;
	}
	public Exchange() {
		
	}

	public Exchange parent() {
		return parent;
	}

	public int numChildren() {
		return children.getSize();
	}

	public Exchange child(int i) {
		return children.findExchange(i);
	}

	public boolean isRoot() {
		if(parent == null) return true;
		else return false;
	}
	public boolean isLeaf() {
		int n = numChildren();
		if (n==0) {return true;} return false;
	}

	public boolean containsExchange(Exchange a) {
		if (this.id == a.id)
			return true; 
		else {
			for(int i = 0; i<numChildren(); i++) {
				if (child(i).containsExchange(a))
					return true;
			} 
			return false;
		}
	}

	public RoutingMapTree subtree(int i) {
		RoutingMapTree r = new RoutingMapTree();
		r.root = this.child(i);											
		return r;
	}

	public void addMobile(MobilePhone a) {
		if(parent == null) {
			mps.Insert(a);
		} else {
			mps.Insert(a);
			parent.addMobile(a);
		}
	}

	public boolean findMobile(int a) {
		if ((mps.IsMember(a))) {
			return true;
		} else 
			return false;
	}

	

	public MobilePhoneSet residentSet() {
		return mps;
	}
}