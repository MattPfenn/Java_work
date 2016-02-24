package ch.hearc;

public class Test{

	public static void main(String[] args) {

		Matrix a = new Matrix(new double[][] {
				{4, 0, 3, 2},
				{1,-1, 2, 0},
				{0, 1,-2, 0},
				{1, 0, 3, 1},
			});

		Matrix b = a;
		Matrix i = a.inverse(); 
		Matrix sum = b.add(a);
		Matrix c = a.transpose(); 
		Matrix matProd = a.multiply(b);
		Matrix scalarProd = a.multiply(3);
		Matrix sub = a.subtract(b); 

		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(matProd);
		System.out.println(scalarProd);	//CHECKED 
		System.out.println(sum);		//CHECKED 
		System.out.println(sub);		//CHECKED 
		System.out.println(i);
	}
}