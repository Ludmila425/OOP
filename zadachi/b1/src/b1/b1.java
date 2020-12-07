package b1;

public class b1 {
	public static void main(String[] args) {
		System.out.println("1) reminder(1,3): "+reminder(1,3));
		System.out.println("   reminder(4,3): "+reminder(4,3));
		System.out.println("  reminder(10,6): "+reminder(10,6));
		System.out.println();
		
		System.out.println("2) triArea(1,3): "+triArea(1,3));
		System.out.println("   triArea(4,3): "+triArea(4,3));
		System.out.println("   triArea(10,6): "+triArea(10,6));
		System.out.println();
		
		System.out.println("3) legs(1,3,5): "+legs(1,3,5));
		System.out.println("   legs(4,3,1): "+legs(4,3,1));
		System.out.println("   legs(10,6,2): "+legs(10,6,2));
		System.out.println();
		
		System.out.println("4) profitableGamble(1,3,2): "+profitableGamble(1,3,2));
		System.out.println("   profitableGamble(4,3,3): "+profitableGamble(4,3,3));
		System.out.println("   profitableGamble(10,6,4): "+profitableGamble(10,6,4));
		System.out.println();
		
		System.out.println("5) operation(1,3,4): "+operation(1,3,4));
		System.out.println("   operation(4,3,1): "+operation(4,3,1));
		System.out.println("   operation(10,6,1): "+operation(10,6,1));
		System.out.println();
		
		System.out.println("6) ctoa('a'): "+ctoa('a'));
		System.out.println("   ctoa('b'): "+ctoa('b'));
		System.out.println("   ctoa('c'): "+ctoa('c'));
		System.out.println();
		
		System.out.println("7) addUpTo(1): "+addUpTo(1));
		System.out.println("   addUpTo(4): "+addUpTo(4));
		System.out.println("   addUpTo(10): "+addUpTo(10));
		System.out.println();
		
		System.out.println("8) nextEdge(1,3): "+nextEdge(1,3));
		System.out.println("   nextEdge(4,3): "+nextEdge(4,3));
		System.out.println("   nextEdge(10,6): "+nextEdge(10,6));
		System.out.println();
		
		System.out.println("9) sunOfCubes([1]): "+sunOfCubes(new int[] {1}));
		System.out.println("   sunOfCubes([]): "+sunOfCubes(new int[] {}));
		System.out.println("   sunOfCubes([1,5,6,2]): "+sunOfCubes(new int[] {1,5,6,2}));
		System.out.println();
		
		System.out.println("10)abcmath(1,3,5): "+abcmath(1,3,5));
		System.out.println("   abcmath(4,3,10): "+abcmath(4,3,10));
		System.out.println("   abcmath(10,6,1): "+abcmath(10,6,1));
	}
	
	//остаток от делени€ одного числа на другое, использу€ стандартный оператор %
	static int reminder(int a, int b) {
		return a%b;
	}
	
	//площадь треугольника: 1/2*основание*высота
	static int triArea(int a, int b) {
		return a*b/2;
	}
	
	//подсчЄт количества ног животных
	static int legs(int a, int b, int c) {
		return a*2 + b*4 + b*4;
	}
	
	//возвращение согласно формуле из задани€
	static boolean profitableGamble(int a, int b, int c) {
		return a*b>c;
	}
	
	//проверка нескольких условий, как получить число с
	static String operation(float a, float b, float c) {
		if(a+b == c)
			return "added";
		else if (a-b == c)
			return "substructed";
		else if(a*b == c)
			return "multyplied";
		else if(a/b == c)
			return "divided";
		return "none";
	}
	
	//возвращает номер символа из таблицы ASCII
	static int ctoa(char a) {
		return (int)a;
	}
	
	//сумма числел [1..a], можно оптимизировать арифметической прогрессией
	static int addUpTo(int a) {
		int sum = 0;
		for(int i =1; i <= a; i++) sum+=i;
		return sum;
	}
	
	//наибольшее целоичсленное ззначение длины третьей стороны треугольника
	static int nextEdge(int a, int b) {
		return a+b-1;
	}
	
	//проходим по входному массиву и считаем сумму кубов его элементов
	static int sunOfCubes(int[] a) {
		int sum = 0;
		for(int i = 0; i < a.length; i++) sum+=a[i]*a[i]*a[i];
		return sum;
	}
	
	//согласно заданию
	static boolean abcmath(int a, int b, int c) {
		a*=b;
		return a%c == 0;
	}
	
}
