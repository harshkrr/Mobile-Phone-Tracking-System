import java.util.*;

public class RoutingMapTree {
	Exchange root;
	Exchange temp;
	public RoutingMapTree(){
		root = new Exchange(0);
	}

	public RoutingMapTree(Exchange a) {
		root = a;
	}

	public Exchange returnRoot() {
		return root;
	}

	public boolean containsNode(Exchange a) {
		if (root == null) 
			return false;
		if(root.id==a.id) 
			return true;
		int ch = root.numChildren();
		RoutingMapTree tem = null;
	   	for(int i = 0; i < ch; i++) {
			tem=root.subtree(i);	
			if(tem!=null && (tem.containsNode(a)))
				return true;
		}
		return false;
	}

	public boolean containsNode(int a) {
		Exchange temp = new Exchange(a);
		return this.containsNode(temp);
	}

	public Exchange findExchange(int a){
		Exchange E = null;
		temp = root;
		if(temp.id == a){
			E = temp;
		}else if (temp.numChildren()!=0){
			for(int i = 0; i<temp.numChildren(); i++){
				if(temp.subtree(i).findExchange(a)!=null) {
					E = temp.subtree(i).findExchange(a);
				}
			}
		}
		return E;
	}

	public Exchange findphone(MobilePhone m) {
		// System.out.println(root.mps.returnMobile(0));
		if(!(root.mps.IsMember(m))) {
			System.out.println("Mobile phone "+m+" not found");
			return null;
		}
		return m.base;
	}

	public void movePhone(int a1, int b1){
		MobilePhone a = root.mps.returnMobile(a1);
		Exchange b = findExchange(b1);
		Exchange e = findphone(a);
		if(e==null) {
			return;
		}
		if(!(root.mps.IsMember(a))){
			System.out.println("Exchange "+b+" not found");
			return;
		}
		if(b.isLeaf()){
			System.out.println("Exchange "+b+" is not a base station");
			return;
		}
		if(!a.Status()){
			System.out.println("MobilePhone "+a+" is switched Off");
			return;
		}
		a.base = null;
		b.addMobile(a);
	}

	public Exchange lowestRouter(Exchange a, Exchange b){
		if(!this.containsNode(a)){
			System.out.println("Exchange a is not found");
			return null;
		}else if(!containsNode(b)){
			System.out.println("Exchange b is not found");
			return null;
		}else{
			while(a!=null && a!=b){
				a = a.parent;
				b = b.parent;
			}
			return a;
		}
	}

	public Exchange lowestRouter(int a, int b){
		Exchange a1 = findExchange(a);
		Exchange a2 = findExchange(b);
		return lowestRouter(a1, a2);
	}

	public ExchangeList routeCall(MobilePhone a, MobilePhone b){
		ExchangeList ans = new ExchangeList();
		if(!(root.mps.IsMember(a))) {
			System.out.println("MobilePhone "+a+" not found");
		}else if(!(root.mps.IsMember(b))) {
			System.out.println("MobilePhone "+b+" not found");
		}else if(!a.Status()){
			System.out.println("MobilePhone "+a+" is switched Off");
		}else if(!b.Status()){
			System.out.println("MobilePhone "+b+" is switched Off");
		}else{
			Exchange a1 = findphone(a);
			Exchange b1 = findphone(b);
			Exchange e = lowestRouter(a1, b1);
			while(a1.id!=e.id){
				ans.Insert(a1);
				a1 = a1.parent;
			}
			ans.Insert(a1);
			ExchangeList temp1 = new ExchangeList();
			while(b1.id!=e.id){
				temp1.Insert(b1);
				b1 = b1.parent;
			}
			Node1 rev = temp1.list.reverseList();
			Node1 n1 = rev;
			while(n1!=null){
				ans.Insert(n1.data);
			}
		}
		return ans;
	}

	public ExchangeList routeCall(int a, int b){
		MobilePhone mp1 = root.mps.returnMobile(a);
		MobilePhone mp2 = root.mps.returnMobile(b);
		return routeCall(a, b);
	}

	public void switchOn(MobilePhone a, Exchange b) {
		if (!a.Status()){
			a.switchOn();
			a.base = b;
			while(b!=null) {
				b.mps.Insert(a);
				b = b.parent;
			}
		}
	}
	public void switchOff(MobilePhone a) throws Exception {
		if (a.Status())
			a.switchOff();
		else throw new Exception("a is already off");
	}
	public String performAction(String actionMessage) {
			String t = (actionMessage);
			String[] s = t.split(" ");
			 try {
				if(s[0].equals("addExchange")) {
					int a = Integer.parseInt(s[1]);
					int b = Integer.parseInt(s[2]);
					Exchange temp = new Exchange(b);
					String x = "";
					try {
						if((this.containsNode(a))) {
							Exchange temp1 = findExchange(a);
							temp1.children.Insert(temp);
							temp.parent = temp1;

						}
					} catch(NullPointerException e) {
						System.out.println(a + " Not Found");
					} 
					return x;
				}

				else if (s[0].equals("switchOnMobile")) {
					int a = Integer.parseInt(s[1]);
					int b = Integer.parseInt(s[2]);
					String x = "";
					try{
						if (this.containsNode(b)) {
							Exchange temp = this.findExchange(b);
							if(temp.findMobile(a)) {
								MobilePhone mp = temp.mps.returnMobile(a);
								this.switchOn(mp, temp);
							} else {
								MobilePhone new_m = new MobilePhone(a);
								temp.addMobile(new_m);
								this.switchOn(new_m, temp);
								new_m.base = temp;
							}
						}
					} catch (Exception e) {
						System.out.println("Error- No exchange with identifier b");
					}
					return x;
				} 

				else if  (s[0].equals("switchOffMobile")) {
					int a = Integer.parseInt(s[1]);
					String x = "";
					try {
						MobilePhone temp = root.mps.returnMobile(a);
						this.switchOff(temp);
						return x;
					}
					 catch(NullPointerException e) {
					} 
				}

				else if (s[0].equals("queryNthChild")) {
					int a = Integer.parseInt(s[1]);
					int b = Integer.parseInt(s[2]);
					String x = t;
					Exchange temp = this.findExchange(a);
					try {
						int size = temp.numChildren();
						Exchange temp1 = temp.child(size-1-b);
						int k = temp1.id;
						String n = Integer.toString(k);
						x = x+": "+n;
						return x;
					} catch(NullPointerException e) {
						return s[2]+ "th child not found";
					} 
				}

				else if (s[0].equals("queryMobilePhoneSet")) {
						int a = Integer.parseInt(s[1]);
					try {
					    Exchange temp = this.findExchange(a);
						MobilePhoneSet m = temp.mps;
						int k = m.numMobile();
						String x = t+": ";
						for(int i=0; i<k; i++){
							 if ((m.ithMobile(i).Status())) {
							 	int num = m.ithMobile(i).id;
								x = x + " " + Integer.toString(num)+",";
							}
						} 
						x = x.substring(0, x.length()-1);
						return x;
					}catch(NullPointerException e ) {
						return ("Error- No b child of a");
					} 
				}

				else if(s[0].equals("findPhone")){
					// System.out.println(root.mps.numMobile());
					int a = Integer.parseInt(s[1]);
					MobilePhone mp = root.mps.returnMobile(a);
					if(mp==null){
						return "Mobile phone "+a+" does not exist or either it is switched off";
					}
				    Exchange temp = this.findphone(mp);
				    // System.out.println(temp);
				    if(temp!=null){
						String x = t+": "+temp.id;
						return x;
					}
					return "";
				}

				else if(s[0].equals("lowestRouter")){
					int a = Integer.parseInt(s[1]);
					int b = Integer.parseInt(s[2]);
					Exchange e = lowestRouter(a, b);
					if(e==null) return "";
					String x = t+": "+e.id;
					return x;
				}

				else if(s[0].equals("findCallPath")){
					int a = Integer.parseInt(s[1]);
					int b = Integer.parseInt(s[2]);
					ExchangeList temp = routeCall(a, b);
					if(temp==null) return "";
					String x = t+": ";
					Node1 n1 = temp.list.head;
					while(n1!=null){
						x+=n1.data.id+" ";
					}
					x = x.substring(0, x.length()-1);
					return x;
				}

				else if(s[0].equals("movePhone")){
					int a = Integer.parseInt(s[1]);
					int b = Integer.parseInt(s[2]);
					movePhone(a, b);
				}

			} catch (Exception e) {
				System.out.println("Query");
			} return "";
		}
}