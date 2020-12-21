package lab2;

public class lab2 {
	public static void main(String[] args) {
		Point3d p1 = new Point3d(0, 0, 1),
				p2 = new Point3d(0, -1, 0),
				p3 = new Point3d();
		
		System.out.print(computeArea(p1,p2,p3));
	}
	
	public static double computeArea(Point3d p1, Point3d p2, Point3d p3) {
		double a = p1.distanceTo(p2);
		double b = p1.distanceTo(p3);
		double c = p2.distanceTo(p3);
		
		double p = (a+b+c)/2;
		return Math.sqrt(p*(p-a)*(p-b)*(p-c));
	}
}
