package atd.spring.server.unused;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import atd.spring.server.bills.Bill;
import atd.spring.server.bills.BillParser;

public class BillTextFileLoader_u {

	public Bill loadFromFile(BillParser billTextFileLoader, String filename) throws IOException {
	    Bill ret = new Bill();
	    BufferedReader br = new BufferedReader(new FileReader(filename));
	    try {
	      String line;
	      while ((line = br.readLine()) !=null) {
	        billTextFileLoader.parseLineItem(line,ret);
	      } 
	    }finally {
	      br.close();
	    }  
	    return ret;
	  }

}
