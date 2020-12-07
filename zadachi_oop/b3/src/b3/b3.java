package b3;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
public class b3 {
	public static void main(String[] args) {
		
		System.out.println("1) solutions(1,-4,1): "+solutions(1,-4,1));
		System.out.println("   solutions(1,0,0): "+solutions(1,0,0));
		System.out.println("   solutions(-1,0,5): "+solutions(-1,0,-5));
		System.out.println();
		
		System.out.println("2) findZip('zipzipzip'): "+findZip("zipzipzip"));
		System.out.println("   findZip('putinputinputin'): "+findZip("putinputinputin"));
		System.out.println("   findZip('zip abc zip zip'): "+findZip("zip abc zip zip"));
		System.out.println();
		
		System.out.println("3) flipEndChars('abcdefg'): "+flipEndChars("abcdefg"));
		System.out.println("   flipEndChars('a'): "+flipEndChars("a"));
		System.out.println("   flipEndChars('abcda'): "+flipEndChars("abcda"));
		System.out.println();
		
		System.out.println("4) checkPerfect(6): "+checkPerfect(6));
		System.out.println("   checkPerfect(28): "+checkPerfect(28));
		System.out.println("   checkPerfect(29): "+checkPerfect(29));
		System.out.println();
		
		System.out.println("5) isValidHexCode('#hexcod'): "+isValidHexCode("hexcod"));
		System.out.println("   isValidHexCode('#fffaac'): "+isValidHexCode("#fffaac"));
		System.out.println("   isValidHexCode('#cdefs'): "+isValidHexCode("#cdefs"));
		System.out.println();
		
		System.out.println("6) same({1,2,3,3},{1,1,2,2,3,3}): "+same(new int[] {1,2,3,3}, new int[] {1,1,2,2,3,3}));
		System.out.println("   same({1,2,3,3},{2,2,3,3}): "+same(new int[] {1,2,3,3}, new int[] {2,2,3,3}));
		System.out.println("   same({},{1,1,2,2,3,3}): "+same(new int[] {}, new int[] {1,1,2,2,3,3}));
		System.out.println();
		
		System.out.println("7) isKaprekar(1): "+isKaprekar(1));
		System.out.println("   isKaprekar(2): "+isKaprekar(2));
		System.out.println("   isKaprekar(289): "+isKaprekar(289));
		System.out.println();
		
		System.out.println("8) longestZero(\"111111\"): "+longestZero("111111"));
		System.out.println("   longestZero(\"0010000010\"): "+longestZero("0010000010"));
		System.out.println("   longestZero(\"00000\"): "+longestZero("00000"));
		System.out.println();
		
		System.out.println("9) nextPrime(5): "+nextPrime(5));
		System.out.println("   nextPrime(6): "+nextPrime(6));
		System.out.println("   nextPrime(12344): "+nextPrime(12344));
		System.out.println();
		
		System.out.println("10)rightTriangle(3,4,5): "+rightTriangle(3,4,5));
		System.out.println("   rightTriangle(10,11,12): "+rightTriangle(10,11,12));
		System.out.println("   rightTriangle(1,2,3): "+rightTriangle(1,2,3));
		System.out.println();
		
		
		
	}
	
	//количество корней квадратного уравнения зависит от его дискриминанта
	static int solutions(int a, int b, int c) {
		int d = b*b-4*a*c;
		if (d>0) return 2;
		else if (d==0) return 1;
		else return 0;
	}
	
	//найти второе вхождени zip в строку
	//ищем первое, удаляем подстроку с начала до конца первого zip
	//ищем второе
	static int findZip(String s) {
		if (s.indexOf("zip") == -1) return -1;
		s = s.substring(s.indexOf("zip")+3);
		if (s.indexOf("zip") == -1) return -1;
		return s.indexOf("zip")+3;
	}
	
	//проверяем все возможные делители числа
	//чекаем до n/2, так как 2 - наименьший делитель
	static boolean checkPerfect(int n) {
		int sum = 0;
		for(int i = 2; i <= n/2; i++) {
			if(n%i==0)sum += i;
		}
		return n==sum;
	}
	
	//Всё строго по условию задачи
	static String flipEndChars(String str) {
		char[] s = str.toCharArray();
		if (s.length < 2) return "Incompatible.";
		if (s[0] == s[s.length-1]) return "Two's a pair.";
		char c = s[0];
		s[0] = s[s.length-1];
		s[s.length-1] = c;
		return new String(s);
	}
	
	static boolean isValidHexCode(String str) {
		str = str.toLowerCase();
		char[] s = str.toCharArray();
		if (s[0] != '#') return false;
		//бежим по каждому симвлоу и смотри на его допустимость
		for(int i = 1; i < s.length; i++)
			if (!(Character.isDigit(s[i]) ||
							   s[i] == 'a'||
							   s[i] == 'b'||
							   s[i] == 'c'||
							   s[i] == 'd'||
							   s[i] == 'e'||
							   s[i] == 'f'))
				return false;
		return true;
				
	}
	
	static boolean same(int[] a1, int[] a2) {
		//Используе два множества
		//они могут содержать только уникальные элементы
		Set <Integer> u1 = new HashSet <Integer>();
		Set <Integer> u2 = new HashSet <Integer>();
        for (Integer t:a1){  
            u1.add(t); 
        }   
        for (Integer t:a2){  
            u2.add(t); 
        }  
        //если их длины равны, то исхоные массивы имели одинаковое количество уникальных элементов
        return u1.size() == u2.size();
	}
	
	static boolean isKaprekar(int n) {
		int k = n*n;
		String s = Integer.toString(k);
		//разбиваем квадрат числа на половины
		String l = s.substring(0, s.length()/2);
		String r = s.substring(s.length()/2);
		if (l.isEmpty()) l = "0";
		//проверяем условия
		return n == (Integer.parseInt(l)+Integer.parseInt(r));
	}
	
	static String longestZero(String s) {
		char[] chrs = s.toCharArray();
		String str = "";
		String maxStr = "";
		//бежим по строке и ищем максимальную подстроку нулей
		for(char c : chrs) {
			if (c == '0') str += '0';
			else {
				if (str.length() > maxStr.length())
					maxStr = str;
				str = "";
			}
		}
		if (str.length() > maxStr.length())
			maxStr = str;
		return maxStr;
	}
	
	static int nextPrime(int n) {
		while(true) {
			if (isPrime(n)) return n;
			n++;
		}
			
	}
	//проверяет, является ли число простым
	static boolean isPrime(int n){ 
        if (n <= 1) 
            return false; 
  
        for (int i = 2; i < n/2; i++) 
            if (n % i == 0) 
                return false; 
  
        return true; 
    } 
	
	//вместо сортировки сделала так
	static boolean rightTriangle(int a, int b, int c) {
		if (a>b && a>c) return a*a == b*b + c*c;
		if (b>a && b>c) return b*b == a*a + c*c;
		if (c>a && c>b) return c*c == b*b + a*a;
		return false;
	}
	
}
