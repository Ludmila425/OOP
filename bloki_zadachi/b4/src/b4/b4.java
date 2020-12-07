package b4;
import java.util.*;
import java.util.regex.*;
public class b4 {
	public static void main(String[] args) {
		
		System.out.println("1) bessey(7, \"hello my name is Bessie and this is my essay\"): "+bessey(7, "hello my name is Bessie and this is my essay"));
		System.out.println();
		
		System.out.println("2) clasters(\"()()()\"): "+Arrays.toString(clasters("()()()")));
		System.out.println("   clasters(\"(()())()(())\"): "+Arrays.toString(clasters("(()())()(())")));
		System.out.println("   clasters(\"\"): "+Arrays.toString(clasters("")));
		System.out.println();
		
		System.out.println("3) toCamelCase(\"qwefw_fewq_Fewq\"): "+toCamelCase("qwefw_fewq_Fewq"));
		System.out.println("   toSnakeCase(\"fwfqFEwfFEWfwfEFWfg\"): "+toSnakelCase("fwfqFEwfFEWfwfEFWfg"));
		System.out.println();
		
		System.out.println("4) overTime(13.25, 15, 30, 1.5): "+overTime(13.25f, 15f, 30f, 1.5f));
		System.out.println("   overTime(9, 17, 30, 1.5): "+overTime(9f, 17f, 30f,1.5f));
		System.out.println();
		
		System.out.println("5) BMI(\"205 pounds\", \"73 inches\"): "+BMI("205 pounds", "73 inches"));
		System.out.println("   BMI(\"154 pounds\", \"2 meters\"): "+BMI("154 pounds", "2 meters"));
		System.out.println();
		
		System.out.println("6) bugger(39): "+bugger(39));
		System.out.println("   bugger(999): "+bugger(999));
		System.out.println();
		
		System.out.println("7) toStarShorthand(\"aabbwefmwep\"): "+toStarShorthand("aabbwefmwep"));
		System.out.println("   toStarShorthand(\"wfwfeeef\"): "+toStarShorthand("wfwfeeef"));
		System.out.println();
		
		System.out.println("8) doesRhyme(\"Sam I am!\", \"Green eggs and ham.\"): "+doesRhyme("Sam I am!", "Green eggs and ham."));
		System.out.println("   doesRhyme(\"You are off to the races\", \"a splendid day.\"): "+doesRhyme("You are off to the races", "a splendid day."));
		System.out.println();
		
		System.out.println("9) trouble(451999277, 411172299): "+trouble(451999277, 411772299));
		System.out.println("   trouble(1222345, 1234): "+trouble(1222345, 1234));
		System.out.println();
		
		System.out.println("10)countUniqueBooks(\"AZYWABBCATTTA\", 'A'): "+countUniqueBooks("AZYWABBCATTTA", 'A'));
		System.out.println("   countUniqueBooks(\"ZZABCDEF\", 'Z'): "+countUniqueBooks("ZZABCDEF", 'Z'));
		System.out.println();
	}
	
	static String bessey(int k, String s) {
		//чтобы перенести на новую строку
		String res = "\n";
		//делим исходную строку по пробелам
		String[] words = s.split(" ");
		
		//переменная для текущей строки
		String currentString = "";
		for(int i = 0; i < words.length; i++) {
			String word = words[i];
			//выполняется ли условие по длине строки
;			if (currentString.length() + word.length()+1 < k)
				currentString += " " + word;
			else {
				res += currentString + '\n';
				currentString = word;
			}
		}
		//добавление последнего слова
		String word = words[words.length -1];
		if (currentString.length() + word.length()+1 < k)
			currentString += " " + word;
		else {
			res += word ;
		}
		return res;
	}
	
	//use ArrayList, not arrays
	static String[] clasters(String s) {
		ArrayList<String> cls = new ArrayList<String>();
		int brackets = 0;
		String currentString = "";
		int size = 0;
		String[] arr = {};
		//считаем количество открытых скобок
		//считаем количество закрытых скобок
		//как только они станут равны - выделяем кластер.
		for(char c : s.toCharArray()) {
			if(c=='(')brackets++;
			else if (c==')')brackets++;
			
			currentString+=c;
			if (brackets == 0) {
				size+=1;
				String[] a = arr;
				arr = new String[size];
				for(int i = 1; i <= a.length; i++) {
					arr[i] = a[i-1];
				}
				arr[0] = currentString;
				currentString = "";
			}
		}
		return arr;
	}
	
	static String toCamelCase(String s) {
		String res = "";
		char[] a = s.toCharArray();
		
		//все по условию
		for(int i = 0; i < s.length(); i++) {
			if(i>0 && a[i-1] == '_') {
				res+=Character.toUpperCase(a[i]);
			}else if(a[i] != '_') {
				res += a[i];
			}
		}
		
		return res;
	}
	static String toSnakelCase(String s) {
		String res = "";
		char[] a = s.toCharArray();
		
		//все по условию
		for(int i = 0; i < s.length(); i++) {
			if (Character.isUpperCase(a[i])) {
				res+="_"+Character.toLowerCase(a[i]);
			}else
				res+=a[i];
		}
		
		return res;
	}
	
	//все по условию
	static String overTime(float a, float b, float c, float d) {
		float normal = Math.min(8, b - a)*c;
		float over = Math.max(0, b-17) * d;
		return "$" + String.valueOf(normal+over);
	}
	
	static String BMI(String s1, String s2) {
		String[] w1 = s1.split(" ");
		String[] w2 = s2.split(" ");
		
		//конвертируем в метрическую систему
		float w = new Float(w1[0]);
		if(w1[1].contains("pounds")) w *= 0.453;
		float h = new Float(w2[0]);
		if(w2[1].contains("inches") ) h *= 0.0254;
		
		//вычесляем ИМТ
		float value = w/(h*h);
		//Выводим результат
		if (value < 18.5)
			return String.valueOf(value)+" Underwight.";
		else if(value > 25)
			return String.valueOf(value)+" Overwight.";
		else 
			return String.valueOf(value)+" Normal weigth.";
	}
	
	static int bugger(int n) {
		int res = n;
		int count= 0 ;
		//пока res больше одной цифры
		while(res > 9) {
			int p = 1;
			
			//произведение цифр чиcla res
			while(res > 1) {
				p*=res%10;
				res /= 10;
			}
			res = p;
			count++;
		}
		return count;
	}
	
	static String toStarShorthand(String s) {
		char[] a = s.toCharArray();
		int count = 0;
		String res = "";
		for(int i = 0; i < s.length(); i++) {
			if(i>0 && a[i]!=a[i-1]) {
				//тернарный оператор для более короткой записи условя
				res+= count == 1 ? a[i-1] : a[i-1]+"*"+String.valueOf(count);
				count = 1;
			} else {
				count++;
			}
		}
		//handling last symbol
		res += count==1 ? a[a.length-1] : a[a.length-1]+"*"+String.valueOf(count);
		return res;
	}
	
	static boolean doesRhyme(String s1, String s2) {
		//выделали последние слова и перевела их в нижний регситр
		String w1 = s1.split(" ")[s1.split(" ").length-1].toLowerCase();
		String w2 = s2.split(" ")[s2.split(" ").length-1].toLowerCase();
		
		//use pattern
		Pattern p = Pattern.compile("[^eyuioaеыаоэяию]");
		Matcher m1 = p.matcher(w1);
		Matcher m2 = p.matcher(w2);
		
		//меняем согласные на пустую строку - остаются гласные.
		char[] ss1 = m1.replaceAll("").toCharArray();
		char[] ss2 = m2.replaceAll("").toCharArray();
		
		int m = Math.min(ss1.length, ss2.length);
		
		if(m == 0) return false;
		
		//возвращаем результат
		for(int i = 1; i <= m; i++)
			if(ss1[ss1.length - i] != ss2[ss2.length - i]) return false;
		return true;
	}
	
	
	static boolean trouble(int a, int b) {
		String s1 = String.valueOf(a);
		String s2 = String.valueOf(b);
		for(int i = 0; i < 10; i++) {
			//i меняется от 0 до 9
			String c1 = String.valueOf(i).repeat(3);// iii
			String c2 = String.valueOf(i).repeat(2);// ii
			//ищем iii и ii в строках
			if(s1.indexOf(c1)!=-1 && s2.indexOf(c2)!=-1)
				return true;
		}
		
		//если условие ни разу не выполнилось, то таких цифр нет
		return false;
	}
	
	static int countUniqueBooks(String str, char chr) {
		int res = 0;
		ArrayList<Character> chrs = new ArrayList<Character>();
		char[] s = str.toCharArray();
		boolean opened = false;
		
		for(int i = 0; i < s.length; i++) {
			//if closed, open
			if(!opened && s[i] == chr) {
				opened = true;
				continue;
			}
			//if opend and not end of the book
			if(opened && s[i] != chr) {
				if(!chrs.contains(s[i])) {
					chrs.add(s[i]);
				}
			}
			//if open and end of the book
			if(opened && s[i] == chr) {
				opened = false;
				res += chrs.size();
				chrs.clear();
			}
		}
		
		return res;
	}
}
