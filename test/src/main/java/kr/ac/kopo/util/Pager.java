package kr.ac.kopo.util;

import java.util.ArrayList;
import java.util.List;

public class Pager {
      private int page = 1;
      private int perPage = 10;
      private int perGroup = 5;
      private double total;  
      private int search;
      private String keyword;
      
   public double getTotal() {
      return total;
   }
   public void setTotal(double total) {
      this.total = total;
   }
   public int getPage() {
      return page;
   }
   public void setPage(int page) {
      this.page = page;
   }
   public int getPerPage() {
      return perPage;
   }
   public void setPerPage(int perPage) {
      this.perPage = perPage;
   }
   public int getPerGroup() {
      return perGroup;
   }
   public void setPerGroup(int perGroup) {
      this.perGroup = perGroup;
   }
   
   public int getPrev() {       
      return page <= perGroup ? 1 : (((page-1) / perGroup) - 1) * perGroup + 1;
      
   }   
   
   public int getNext() {
      int next = (((page-1) / perGroup) + 1) * perGroup + 1;
      int last = getLast();
      return next < last ? next : last;
   } 
   
   public int getLast() {
      return (int) Math.ceil(total / perPage);   
   }         

   public List<Integer> getList() {
      ArrayList<Integer> list = new ArrayList<Integer>();
      
      int startPage = (((page-1) / perGroup)) * perGroup + 1;
      
      for(int index = startPage; index < (startPage + perGroup) && 
            index <= getLast(); index++)
         list.add(index);   
      
      if(list.isEmpty())   
         list.add(1);
      
      return list;
   }
public int getSearch() {
	return search;
}
public void setSearch(int search) {
	this.search = search;
}
public String getKeyword() {
	return keyword;
}
public void setKeyword(String keyword) {
	this.keyword = keyword;
}
   
      
}
