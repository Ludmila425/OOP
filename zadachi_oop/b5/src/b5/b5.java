package b5;
import java.util.*;
import java.util.regex.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;

public class b5 {
	public static void main(String[] args) {
		
		System.out.println("1) encrypt(\"Hello, world!\"): "+Arrays.toString(encrypt("Hello, world!")));
		System.out.println("   decrypt(new int[] {82,-43,111}): "+decrypt(new int[] {82,-43,111} ));
		System.out.println("   decrypt(encrypt(\"Hello, world!\")): "+decrypt(encrypt("Hello, world!")));
		System.out.println();
		
		System.out.println("2) canMove(\"конь\",\"A1\",\"C2\"): "+canMove("конь","A1","C2"));
		System.out.println("   canMove(\"конь\",\"A2\",\"C2\"): "+canMove("конь","A2","C2"));
		System.out.println("   canMove(\"ладья\",\"A1\",\"E1\")): "+canMove("ладья","A1","E1"));
		System.out.println();
		
		System.out.println("3) canComplete(\"butl\", \"beautiful\"): "+canComplete("butl", "beautiful"));
		System.out.println("   canComplete(\"butlz\", \"beautiful\"): "+canComplete("butlz", "beautiful"));
		System.out.println("   canComplete(\"bbutl\", \"beautiful\")): "+canComplete("bbutl", "beautiful"));
		System.out.println();
		
		System.out.println("4) sumDigProd(0): "+sumDigProd(0));
		System.out.println("   sumDigProd(1,2,3): "+sumDigProd(1,2,3));
		System.out.println("   sumDigProd(4,2,4,5,6,2,44,0)): "+sumDigProd(4,2,4,5,6,2,44,0));
		System.out.println();
		
		System.out.println("5) sameVowelGroup({\"toe\", \"ocelot\", \"maniac\"}): "+Arrays.toString(sameVowelGroup(new String[] {"toe", "ocelot", "maniac"})));
		System.out.println("   sameVowelGroup({\"many\", \"carriage\", \"emit\", \"apricot\", \"animal\"}): "+Arrays.toString(sameVowelGroup(new String[] {"many", "carriage", "emit", "apricot", "animal"})));
		System.out.println("   sameVowelGroup({\"hoops\", \"chuff\", \"bot\", \"bottom\"}): "+Arrays.toString(sameVowelGroup(new String[] {"hoops", "chuff", "bot", "bottom"})));
		System.out.println();
		
		System.out.println("6) validateCard(\"1234567890123456\"): "+validateCard("1234567890123456"));
		System.out.println("   validateCard(\"1234567890123452\"): "+validateCard("1234567890123452"));
		System.out.println();
		
		System.out.println("7) vnumToRus(0)): "+numToRus(0));
		System.out.println("   numToRus(1)): "+numToRus(1));
		System.out.println("   numToRus(11)): "+numToRus(11));
		System.out.println("   numToRus(21)): "+numToRus(21));
		System.out.println("   numToRus(401)): "+numToRus(401));
		System.out.println("   numToRus(2122)): "+numToRus(2122));
		System.out.println();
		
		System.out.println("8) getSha256Hash(\"password123\"): "+getSha256Hash("password123"));
		System.out.println("   getSha256Hash(\"Hey dude!\"): "+getSha256Hash("Hey dude!"));
		System.out.println();
		
		System.out.println("9) hexLattice(1): \n"+hexLattice(1));
		System.out.println("   hexLattice(37): \n"+hexLattice(37));
		System.out.println("   hexLattice(18): \n"+hexLattice(18));
		System.out.println();
		
		System.out.println("10)correctTitle(\"jOn SnoW, kINg IN thE noRth.\"): "+correctTitle("jOn SnoW, kINg IN thE noRth."));
		System.out.println("   correctTitle(\"кто-то Самый неинтересный\"): "+correctTitle("кто-то Самый неинтересный"));
		System.out.println();
	}
	
	static int[] encrypt(String s) {
		int[] a = new int[s.length()];
		char[] c = s.toCharArray();
		a[0] = (int)c[0];
		//считаем разницу между текущим символом и предыдущим по таблице ASCII
		for(int i =1; i < s.length(); i++) {
			a[i] = (int)c[i] - c[i-1];
		}
		return a;
	}
	static String decrypt(int[] a) {
		String res = "";
		res += (char)a[0];
		//восстанавливаем символы по разнисти между ними, начиная с первого
		for(int i = 1; i < a.length; i++) {
			a[i] += a[i-1];
			res += (char)(a[i]);
		}
		return res; 
	}
	
	//считает разницу между х и у координатами
	//для каждой из фигур провярет, может ли она двигаться с такими разностями
	static boolean canMove(String name, String pos1, String pos2) {
		int dx = (int)pos2.charAt(0) - (int)pos1.charAt(0);
		int dy = (int)pos2.charAt(1) - (int)pos1.charAt(1);
		switch(name) {
			//например, пешка идет только вперед.
			case "пешка":
				if(dx == 0 && dy == 1) return true;
				break;
			case "ладья":
				if((dx == 0 || dy == 0) && (dx > 0 || dy > 0)) return true;
				break;
			case "слон":
				if(dx == dy && dx>0) return true;
			case "конь":
				if(Math.abs(dx) == 1 && Math.abs(dy)==2 || Math.abs(dx) == 2 && Math.abs(dy)==1) return true;
				break;
			case "король":
				if(Math.abs(dx)<=1 && Math.abs(dy) <= 1) return true;
				break;
			case "ферзь":
				if((dx == 0 || dy == 0) && (dx > 0 || dy > 0) || 
						Math.abs(dx)<=1 && Math.abs(dy) <= 1) return true;
				break;
					
		}
		return false;
	}
	
	static boolean canComplete(String s1, String s2) {
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		
		Map<Character,Integer> chrs1 = new HashMap<Character,Integer>();
		Map<Character,Integer> chrs2 = new HashMap<Character,Integer>();
		
		//collecting all the chars in both strings into dicts
		//first initialize with 0's, then it's value 
		for(int i = 0; i < str1.length; i++) {
			chrs1.put(str1[i], 0);
		}
		for(int i = 0; i < str1.length; i++) {
			chrs1.put(str1[i], chrs1.get(str1[i])+1);
		}
		for(int i = 0; i < str2.length; i++) {
			chrs2.put(str2[i], 0);
		}
		for(int i = 0; i < str2.length; i++) {
			chrs2.put(str2[i], chrs2.get(str2[i])+1);
		}
		//the same amount of equal symbols
		for(int i = 0; i < str1.length; i++) {
			if(chrs2.containsKey(str1[i]) && chrs2.get(str1[i]) >= chrs1.get(str1[i]))
				;
			else
				return false;
				
		}
		int index = 0;
		for(int i = 0; i < s1.length(); i++) {
			//проверяем неравные символы
			if(str1[i] != str2[i]) {
				boolean flag = false;
				//бежим по второй строке до момента, пока символы не станут равными
				for(int j = index; j < str2.length; j++) {
					if(str1[i] == str2[j]) {
						index = j;
						flag = true;
						break;
					}
				}
				//если цикл закончился самостоятлельно, а не break, то аканчиваем и возвращаем false
				if (flag) continue;
				return false;
			}
		}
		return true;
	}
	
	static int sumDigProd(int... args) {
		int res = 0;
		//складываем все аргументы вместе
		for(int i = 0; i < args.length; i++) res+=args[i];
		//произведение цифра числа, по то больше либо равно 10
		while(res>9) {
			int r = 1;
			while(res>1) {
				r *= res%10;
				res /= 10;
			}
			res = r;
		}
		return res;
	}
	
	static String[] sameVowelGroup(String[] strs) {
		List<String> res = new ArrayList<String>();
		
		//оставляем все гласные в первом
		Pattern p = Pattern.compile("[^eyuioaеыаоэяию]");
		Matcher m1 = p.matcher(strs[0]);
		
		String ss1 = m1.replaceAll("");
		res.add(strs[0]);
		for(int i = 1; i < strs.length; i++) {
			m1 = p.matcher(strs[i]);
			char[] s = m1.replaceAll("").toCharArray();
			boolean flag = false;
			//оставляем все гласные и сравниваем с первым словом.
			for(int j = 0; j < s.length; j++) {
				if(!ss1.contains(String.valueOf(s[j]))) flag = true;
			}
			if(flag) continue;
			res.add(strs[i]);
		}
		String[] r = new String[res.size()];
	    res.toArray(r);
		return r;

	}
	
	//просто согласно алгоритму, ничего особенного
	static boolean validateCard(String a) {
		int[] ar = new int[a.length()];
		if (ar.length < 14 || ar.length > 19) return false;
		
		//fill the array
		for(int i = 0; i < ar.length; i++) {
			ar[i] = Integer.parseInt(String.valueOf(a.toCharArray()[i]));
		}
		
		int digit = ar[ar.length-1];
		//reverse the array
		for(int i = 0, j = ar.length-2; i < j; i++, j--) {
			int d = ar[i];
			ar[i] = ar[j];
			ar[j] = d;
		}
		
		int sum = 0;
		for(int i = 0; i < ar.length-1; i++) {
			if(i%2 == 0) {
				
				ar[i] = ar[i]*2;
				if(ar[i] >= 10) ar[i] = ar[i]%10 + ar[i]/10;
			}
			sum+=ar[i];			
		}
		
		return digit == 10-(sum%10);
	}
	
	//собираем нужную строку из заданных, в зависимости от числа
	//строка собирается, доставая из массива элемента по индексам, бес циклов
	static String numToRus(int num) {
		String[][] words = {{"","сто ","двести ","триста ","четыреста ","пятьсот ","шестьсот ","семьсот ","восемьсот ","девятьсот ","девятьсот"},
							{""," ","двадцать ","тридцать ","сорок ","пятьдесят ","шестьдесят ","семьдесят ","восемьдесят ","девяносто"},
							{"","один ","два ","три ","четыре ","пять ","шесть ","семь ","восемь ","девять"},
							{"десять ","одиннадцать ","двенадцать ","тринадцать ","четырнадцать ","пятнадцать ","шестнадцать ","семьнадцать ","восемьнадцать ","девятнадцать"}};
		if(num < 0 || num > 999)
			return "out of range";
		else if(num == 0)
			return "ноль";
		else if(num < 10) {
			return words[2][num];
		}else if(num < 20) {
			return words[3][num-10];
		}else if(num < 100) {
			return words[1][num/10] + words[2][num%10];
		} else if(num < 1000) {
			return words[0][num/100] + words[1][num/10%10] + words[2][num%10];
		}
		return "";
	}
	
	//взятая из дефолтной библиотеки SHA256 
	static String getSha256Hash(String base){
		try {
			MessageDigest md = MessageDigest.getInstance( "SHA-256" );
			
			// Change this to UTF-8 if needed
			md.update( base.getBytes( StandardCharsets.UTF_8 ) );
			byte[] digest = md.digest();

			String hex = String.format( "%064x", new BigInteger( 1, digest ) );
			return hex;
		} catch(NoSuchAlgorithmException e) {
			return "error occured";
		}
	}
	
	//всю строку в нижний регистр
	//разделяем на слова и у первого слова первую букву в верхний регистр
	static String correctTitle(String title) {
		title = title.toLowerCase();
		String[] words = title.split(" ");
		String res = "";
		for(String word : words) {
			word = word.substring(0, 1).toUpperCase() + word.substring(1);
			res += word + " ";
		}
		return res;
				
	}
	
	//сначала проверить число на его гексогональность
	//потом отрисовать строку, используя вспомогательную функцию
	static String hexLattice(int n) {
		if((n-1)%6 == 0) {
			int k = (n-1)/6;
			int r = 1;
			while(k > r) {
				k -= r;
				r += 1;
			}
			if(k != r && k!=0) return "Invalid";
		}else {
			return "Invalid";
		}
		
		int x = (3 + (int)Math.sqrt(12*n-3))/6;
		int size = (x*2-1)*2-1;
		String res = "";
		for(int i = x; i <= size/2+1; i++) {
			res += genLine(size, i) + '\n';
		}
		for(int i = size/2; i >= x; i--) {
			res += genLine(size, i) + '\n';
		}
		return res;
	}
	//вспомогатеьная функция рисует а символов посердине строки длинной n
	static String genLine(int a, int n) {
		int spaceing = (a - n*2-1)/2;
		String res = "";
		for(int i = 0; i < a; i++) {
			if(i > spaceing && i < a - spaceing) {
				if(n % 2 == 1) {
					if(i%2 == 0)res+='o';
					else res += ' ';
				}else {
					if(i%2 == 1)res+='o';
					else res += ' ';
				}
			}else {
				res+=' ';
			}
		}
		return res;
	}
	
}
