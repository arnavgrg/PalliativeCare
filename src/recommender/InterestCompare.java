package Recommender;

import java.util.ArrayList;
import java.util.Iterator;

public class InterestCompare {
	
	private int matches;
	
	public int compare(ArrayList<String> pInterests, ArrayList<String> vInterests) {
		Iterator<String> itr = pInterests.iterator();
		Iterator<String> itr2 = vInterests.iterator();
		if(pInterests.size() > vInterests.size()) {
			itr = pInterests.iterator();
			itr2 = vInterests.iterator();
		}
		if(pInterests.size() < vInterests.size()) {
			itr = vInterests.iterator();
			itr2 = pInterests.iterator();
		}
		while(itr.hasNext()) {
			while(itr2.hasNext()) {
				if(itr.next() == itr2.next())
					matches++;
			}
		}
		return matches;
	}
}
