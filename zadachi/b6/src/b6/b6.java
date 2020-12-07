package b6;
import java.util.*;
import java.util.regex.*;
import java.util.Map;
import java.util.HashMap;

public class b6 {
	public static void main(String[] args) {
		
		System.out.println("1) bell(1): "+bell(1));
		System.out.println("   bell(5): "+bell(5));
		System.out.println("   bell(20): "+bell(10));		
		System.out.println();
		
		System.out.println("2) translateSentance(\"kek\"): "+translateSentance("kek"));
		System.out.println("   translateSentance(\"I am the king\"): "+translateSentance("I am the king"));
		System.out.println("   translateSentance(\"Putctuation, lets test it\"): "+translateSentance("Putctuation, lets test it"));		
		System.out.println();
		
		System.out.println("3) validColor(\"rgb(1,2,3)\"): "+validColor("rgb(1,2,3)"));
		System.out.println("   validColor(\"rgba(1,2,3,0.4)\"): "+validColor("rgba(1,2,3,0.4)"));
		System.out.println("   validColor(\"rgb(,,6)\"): "+validColor("rgb(,,6)"));		
		System.out.println();
		
		System.out.println("4) stripUrlParams(\"https://edabit.com?a=1&b=2&a=2\"): "+stripUrlParams("https://edabit.com?a=1&b=2&a=2"));
		System.out.println("   stripUrlParams(\"https://edabit.com?a=1&b=2&a=2\", [\"b\"]): "+stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[] {"b"} ));
		System.out.println("   stripUrlParams(\"https://edabit.com\", [\"b\"])): "+stripUrlParams("https://edabit.com", new String[] {"b"}));		
		System.out.println();
		
		System.out.println("5) getHashTags(\"How the Avocado Became the Fruit of the Global Trade\"): "+Arrays.toString(getHashTags("How the Avocado Became the Fruit of the Global Trade")));
		System.out.println("   getHashTags(\"Why You Will Probably Pay More for Your Christmas Tree This Year\"): "+Arrays.toString(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year")));
		System.out.println("   getHashTags(\"Visualizing Science\"): "+Arrays.toString(getHashTags("Visualizing Science")));		
		System.out.println();
		
		System.out.println("6) longestNonrepeatingSubstring(\"aaaa\")): "+longestNonrepeatingSubstring("aaaa"));
		System.out.println("   longestNonrepeatingSubstring(\"abcabcacc\"): "+longestNonrepeatingSubstring("abcabcacc"));
		System.out.println("   longestNonrepeatingSubstring(\"abcdef\"): "+longestNonrepeatingSubstring("abcdef"));		
		System.out.println();
		
		System.out.println("7) ulam(3): "+ulam(3));
		System.out.println("   ulam(9): "+ulam(9));
		System.out.println("   ulam(100): "+ulam(206));		
		System.out.println();
		
		System.out.println("8) convertToRoman(499): "+convertToRoman(499));
		System.out.println("   convertToRoman(11): "+convertToRoman(11));
		System.out.println("   convertToRoman(3456): "+convertToRoman(3456));		
		System.out.println();
		
		System.out.println("9) formula(\"1 + 3 =4\"): "+formula("1 + 3 =4"));
		System.out.println("   formula(\"3 / 3 = 4-2-1 = 1\"): "+formula("3 / 3 = 4-2-1 = 1"));
		System.out.println("   formula(\"3 == 3++31\"): "+formula("3 == 3++31"));		
		System.out.println();
		
		System.out.println("10)palindromedescendant(\"11211230\"): "+palindromedescendant("11211230"));
		System.out.println("   palindromedescendant(\"13001120\"): "+palindromedescendant("13001120"));
		System.out.println("   palindromedescendant(\"23336014\"): "+palindromedescendant("23336014"));		
		System.out.println();
		
	}
	
	static int bell(int n) {
		int sum = 0;
		for(int i = 0; i <= n; i++) {
			sum += Stirling(n,i);
		}
		return sum;
	}
	static int Stirling (int n, int k){
	    if (n==k) return 1;
	    else
	    if (k==0) return 0;
	    else
	        return Stirling(n-1,k-1) + k * Stirling(n-1,k);

	}
	
	static String translateWord(String s) {
		s = s.toLowerCase();
		
		if(isVowel(s.charAt(0))) {
			s+="yay";
		}else {
			String temp = "";
			for(int i = 0; i < s.length(); i++) {
				if(!isVowel(s.charAt(i))) {
					temp+=s.charAt(i);
				}else {
					s = s.substring(i);
					s += temp + "ay";
					break;
				}
			}
		}
		
		return s;
	}
	static String translateSentance(String s) {
		String[] strs = s.split(" ");
		String[] words = strs.clone();
		String res = "";
		for(int i = 0; i < words.length; i++) {
			String w = words[i];
			w = w.trim();
			if(w.endsWith(",") || w.endsWith(".")) 
				w = w.substring(0, w.length()-1);
			w = translateWord(w);
			
			if(Character.isUpperCase(strs[i].toCharArray()[0])) {
				char[] cs = w.toCharArray();
				cs[0] = Character.toUpperCase(cs[0]);
				w = String.valueOf(cs);
			}
			
			
			if(strs[i].endsWith(".")) {res += w + "."; continue;}
			if(strs[i].endsWith(",")) {res += w + ", "; continue;}
			res+=w + " ";
		}
		return res;
	}
	static boolean isVowel(char c) {
		return String.valueOf(c).matches("[eyuioaеыаоэяию]");
	}
	
	
	static boolean validColor(String s) {
		s = s.replaceAll(" ", "").trim();		
		
		if(s.indexOf("rgba") != -1) {
			s = s.substring(5);
			s = s.substring(0,s.length()-1);
			String[] strs = s.split(",");
			if(strs.length != 4) return false;
			
			for(int i = 0; i < strs.length-1; i++) {
				if(strs[i].length() > 0) {
					int val;
					try {
						val = Integer.parseInt(strs[i]);
						if(val < 0 || val > 255) return false;
					} catch(Exception e) {
						return false;
					}
				}else {
					return false;
				}
			}
			if(strs[3].length() > 0) {
				float val;
				try {
					val = Float.parseFloat(strs[3]);
					if(val < 0 || val > 1) return false;
				} catch(Exception e) {
					return false;
				}
			}else {
				return false;
			}
			
		}else if(s.indexOf("rgb") != -1) {
			s = s.substring(4);
			s = s.substring(0,s.length()-1);
			String[] strs = s.split(",");
			if(strs.length != 3) return false;
			for(int i = 0; i < strs.length-1; i++) {
				if(strs[i].length() > 0) {
					int val;
					try {
						val = Integer.parseInt(strs[i]);
					} catch(Exception e) {
						return false;
					}
				}else {
					return false;
				}
			}
		} 
		return true;
	}
	
	static String stripUrlParams(String s, String[] p) {
		String res = "";
		Map<String,String> params = new HashMap<String,String>();

		if(s.indexOf("?") == -1) return s;
		
		String str = s.substring(s.indexOf("?")+1);
		String[] vars = str.split("&");
		for(String v : vars) {
			String[] t = v.split("=");
			params.put(t[0], t[1]);
		}
		res = s.substring(0,s.indexOf("?")+1);
		
		String[] keySet = new String[params.keySet().toArray().length];
		for(int j = 0; j < keySet.length; j++) keySet[j] = (String)params.keySet().toArray()[j];
		Arrays.sort(keySet);

		for(int i = 0; i < keySet.length; i++) {
			if(in(p,keySet[i])) continue;
			res+= keySet[i]+"="+params.get(keySet[i])+"&";
		}
		
		return res.endsWith("&") ? res.substring(0,res.length()-1):res;
	}
	static String stripUrlParams(String s) {
		return stripUrlParams(s, new String[] {});
	}
	
	static String[] getHashTags(String str) {
		String[] strs = str.split(" ");
		//sort by length
		Arrays.sort(strs, (a, b)->Integer.compare(b.length(), a.length()));

		int l = Math.min(strs.length, 3);
		String[] res = new String[l];
		for(int i = 0; i < l; i++) {
			if(strs[i].endsWith(","))
				res[i] = "#"+strs[i].toLowerCase().substring(0, strs[i].length()-1).trim();
			else
				res[i] = "#"+strs[i].toLowerCase().trim();
		}
		return res;
	}
	
	static int ulam(int n) {
		int[] nums = new int[n];
		
		if(n<2)return n;
		
		nums[0] = 1;
		nums[1] = 2;
		int complete = 2;
		
		while(complete<n) {
			Map<Integer,Integer> sums = new HashMap<Integer,Integer>();
			for(int i = 0; i < complete-1; i++) {
				for(int j = i+1; j < complete; j++) {
					if(i==j)continue;
					int sum = nums[i] + nums[j];
					try {
						sums.put(sum, sums.get(sum)+1);
					} catch (Exception e){
						sums.put(sum, 1);
					}
					
				}
			}
			int[] keySet = new int[sums.keySet().toArray().length];
			for(int j = 0; j < keySet.length; j++) keySet[j] = (int)sums.keySet().toArray()[j];
			Arrays.sort(keySet);
			
			for(int i = 0; i < keySet.length; i++) {
				//key = candidate for Ulam nuber
				if(sums.get(keySet[i]) == 1 && !in(nums, keySet[i])) {
					nums[complete] = keySet[i];
					break;
				}
			}

			complete++;
		}

		Arrays.sort(nums);
		return nums[n-1];
		
	}
	static boolean in(int[] a, int n) {
		for(int i = 0; i < a.length; i++) {
			if(a[i] == n) return true;
		}
		return false;
	}
	static boolean in(String[] a, String n) {
		for(int i = 0; i < a.length; i++) {
			if(a[i].equals(n)) return true;
		}
		return false;
	}
	
	static String longestNonrepeatingSubstring(String s) {
		char[] chrs = s.toCharArray();
		String res = "";
		String temp = "";
		for(int i = 0; i < chrs.length; i++) {
			if(temp.indexOf(chrs[i]) == -1) {
				temp += chrs[i];
			}else {
				if(temp.length() > res.length()) {
					res = temp;
					temp = "";
				}
			}
		}
		if(temp.length() > res.length()) {
			res = temp;
			temp = "";
		}
		return res;
	}
	
	static String convertToRoman(int n) {
		String res = "";
		//use additional symbols in alphabet
		String[] chrs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		if(n < 0 || n > 3999) return "out of range";
		
		int i = 0;
		while(n>0 && i < chrs.length) {
			if(values[i] <= n) {
				res+=chrs[i];
				n-= values[i];
			} else {
				i++;
			}
		}
		
		return res;
	}
	
	static boolean formula(String s) {
		String[] parts = s.split("=");
		int res = 0;
		try {
			res = calculate(parts[0]);
		}catch(Exception e){
			return false;
		}
		for(int i = 1; i < parts.length; i++) {
			try {
				if(calculate(parts[i]) != res) return false;
			}catch(Exception e){
				return false;
			}
		}
		return true;
	}
	static int calculate(String s) throws Exception{
		s = s.replaceAll(" ", "");
		
		boolean flag = true;
		while(flag) {
			flag = false;
			int i = 0;
			
			if(s.indexOf("/")!=-1 || s.indexOf("+")!=-1 || s.indexOf("-")!=-1 || s.indexOf("*")!=-1) {
				flag = true;
				
				//определяем действие и естb ли они еще в стоке
				char c = ' ';
				if(s.indexOf("/") != -1) {i = s.indexOf("/"); c = '/';}
				else if(s.indexOf("*") != -1) {i = s.indexOf("*"); c = '*';}
				else if(s.indexOf("+") != -1) {i = s.indexOf("+"); c = '+';}
				else if(s.indexOf("-") != -1) {i = s.indexOf("-"); c = '-';}
				
				int a = i-1;
				int b = i+1;
				
				String an = "";
				String bn = "";
				while(a>=0 && Character.isDigit(s.charAt(a))){
					an+=s.charAt(a);
					a--;
					
				}
				an =  new StringBuilder(an).reverse().toString();
				while(b<s.length() && Character.isDigit(s.charAt(b))) {
					bn += s.charAt(b);
					b++;
				}				
				
				int value = 0;
				switch(c) {
					case '+':
						value = Integer.parseInt(an) + Integer.parseInt(bn);
						break;
					case '-':
						value = Integer.parseInt(an) - Integer.parseInt(bn);
						break;
					case '*':
						value = Integer.parseInt(an) * Integer.parseInt(bn);
						break;
					case '/':
						value = Integer.parseInt(an) / Integer.parseInt(bn);
						break;
				}
				s = s.replace(an+c+bn, String.valueOf(value));
			}
			
		}
		return Integer.parseInt(s);
	}
	
	static boolean palindromedescendant(String s) {
		while(s.length() >= 2){
			if(isPalindrome(s)) return true;
			else s = createChildPalindrome(s);
		}
		return false;
	}
	static boolean isPalindrome(String s) {
		for(int i = 0, j = s.length()-1; i<j; i++,j--) {
			if(s.charAt(i) != s.charAt(j)) return false;
		}
		return true;
	}
	static String createChildPalindrome(String s) {
		String res = "";
		for(int i = 0; i < s.length(); i+=2) {
			res += String.valueOf(Integer.parseInt(String.valueOf(s.charAt(i))) + Integer.parseInt(String.valueOf(s.charAt(i+1))));
		}
		return res;
	}
	
}