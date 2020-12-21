package lab1;

public class primes {
	public static void main(String[] args) {
		for(int i = 1; i <= 100; i++) {
			if(isPrime(i)) {
				System.out.print(i+" ");
			}
		}
		System.out.println();
		System.out.println("\"shrek\" is palindome? "+isPalindrome("shrek"));
		System.out.print("\"kekkek\" is palindome? "+isPalindrome("kekkek"));
	}
	
	public static boolean isPrime(int n) {
		for(int i = 2; i < n/2+1; i++) {
			if(n%i==0)return false;
		}
		return true;
	}
	public static boolean isPalindrome(String s) {
		for(int i = 0, j = s.length()-1; i<j; i++,j--) {
			if(s.charAt(i)!=s.charAt(j))return false;
		}
		return true;
	}
}
