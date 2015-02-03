package app.cq.test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Test {

	public static void main(String[] args) throws IOException {
		 /* Set<Integer> set = new TreeSet<Integer>();
		  for(int i=0;i<5;i++){
			  set.add(3*(i+1));
			  for(int j=0;j<4;j++){
				  set.add(5*(j+1));
				  set.add(3*(i+1)+5*(j+1));
			  }
		  }
		  System.out.println("邮资情况为："+set.size()+"种");
		  for (int i : set) {
			System.out.println(i);
		  }*/
		
		   /* BufferedReader b = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\English.txt"));
			BufferedWriter w = new BufferedWriter(new FileWriter("C:\\Users\\Administrator\\Desktop\\EnglishCount.txt"));
			String s = "";
			Pattern p = Pattern.compile("\\s*\\b(\\w+)\\b\\s*");
			int i = 0;
			do{
				s = b.readLine();
				Matcher m = p.matcher(s);  
				while(m.find()){
			                i++;
				}
				w.write(s);
				w.write(i+"个\r\n");
				i=0;
			  }while(b.read()!=-1);
			b.close();
			w.close();*/
		
/*		String regEx = "(\\d{1,3})\\.";
		String s1 = "192.168.1.1.";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(s1);
		while(mat.find()){
			System.out.println(mat.group(1));
		 }
		
*/		
		
		  Pattern p = Pattern.compile("(\\d{3,5})([a-z]{2})");
          String s = "123aa-34345bb-234cc-00";
          Matcher m = p.matcher(s);
          while(m.find()) 
          {
               System.out.println("m.group():"+m.group()); //打印所有
                
               System.out.println("m.group(1):"+m.group(1)); //打印数字的
                
               System.out.println("m.group(2):"+m.group(2)); //打印字母的
               System.out.println();
               
        }    
        System.out.println("捕获个数:groupCount()="+m.groupCount());
		
	}
	

}
