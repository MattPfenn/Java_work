package ch.hearc;

public class Matrix {

	private final int rows;
	private final int columns;
	private final double[][] value;

	public Matrix(int i, int j) {		//Constructor 1
		this.rows=i; 
		this.columns=j;
		this.value= new double[i][j]; 
	}

	public Matrix(double[][] data) {	//Constructor 2
		this.value=data.clone(); 		//Copy of "data" content in "value" array
		this.rows=data.length;
		this.columns=data.length;
	}

	public int getRows() {
		return this.rows;
	}

	public int getColumns() {
		return this.columns;
	}

	public Matrix multiply(Matrix b) {
		if(this.columns==b.rows){ //Can be multiplied ? 
			Matrix prod = new Matrix(this.rows, b.columns); 
			for(int i=0; i<rows; i++){
				for(int j=0; j<columns; j++){
					prod.value[i][j]=this.value[i][j]*b.value[j][i];
				}
			}
			return prod;
		}
		else{					 //If the Matrix can't be multiplied, return null
			return null; 
		}
	}

	public Matrix multiply(double d) {
		Matrix res = new Matrix(this.rows, this.columns);
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				res.value[i][j] = this.value[i][j]*d;
			}
		}
		return res;
	}
	
	//TODO : add try and throw error if condition are not filled	----> IllegalArgumentException
	public Matrix add(Matrix c) {
		Matrix sum = new Matrix(this.rows, this.columns);
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				sum.value[i][j] = this.value[i][j] + c.value[i][j];
			}
		}
		return sum;
	}
	
	//TODO : add try and throw error if condition are not filled 
//	try{
//		
//	}
//	catch(IllegalArgumentException err)
//	{
//		
//	}
	public Matrix subtract(Matrix d) {
		Matrix res = new Matrix(this.rows, this.columns);
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				res.value[i][j] = this.value[i][j] - d.value[i][j];
			}
		}
		return res;
	}

	public Matrix transpose() {
		Matrix transpose = new Matrix(value);
	    for (int i = 0 ; i < rows ; i++ ){
	         for (int j = 0 ; j < columns ; j++ ){ 
	        	transpose.value[i][j]=transpose.value[j][i];
	         }
	    }
		return transpose;
	}

	public double determinant() {
		if (this.rows != this.columns)
			return 0;
		if (this.rows == 1)
			return this.value[0][0];
		if (this.rows == 2)
			return this.value[0][0] * this.value[1][1] - this.value[0][1]
					* this.value[1][0];

		double sum = 0.0;
		for (int i = 0; i < this.rows; i++) {
			sum += signal(i) * this.value[0][i] * subMatrix(0, i).determinant();
		}
		return sum;
	}

	public Matrix subMatrix(int excludeRow, int excludeColumn) {
		Matrix mat = new Matrix(this.rows - 1, this.columns - 1);
		int r = -1;
		for (int i = 0; i < this.rows; i++) {
			if (i != excludeRow) {
				r++;
				int c = -1;
				for (int j = 0; j < this.columns; j++) {
					if (j != excludeColumn) {
						c++;
						mat.value[r][c] = this.value[i][j];
					}
				}
			}
		}
		return mat;
	}

	private double signal(int i) {
		return ((i&1)==0) ? 1. : -1.;
	}

	public Matrix cofactor() {
		if (this.rows != this.columns)
			return null;

		Matrix mat = new Matrix(this.rows, this.columns);
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				Matrix m1 = subMatrix(i, j);
				double det = m1.determinant();
				mat.value[i][j] = signal(i) * signal(j) * det;
			}
		}
		return mat;
	}

	public Matrix inverse() {
		return cofactor().transpose().multiply(1. / determinant());
	}

	public String toString() {
		
		return "Rows : " + this.rows +
			   " ,Columns : " + this.columns + 
			   " ,Value : " + this.value[0][0]+ " "
					   		+ this.value[0][1]+ " "
					   		+ this.value[0][2]+ " "
					   		+ this.value[0][3]+ " "
					   		+ this.value[1][0]+ " "
					   		+ this.value[1][1]+ " "
					   		+ this.value[1][2]+ " "
					   		+ this.value[1][3]+ " "
					   		+ this.value[2][0]+ " "
					   		+ this.value[2][1]+ " "
					   		+ this.value[2][2]+ " "
					   		+ this.value[2][3]+ " "
					   		+ this.value[3][0]+ " "
					   		+ this.value[3][1]+ " "
					   		+ this.value[3][2]+ " "
					   		+ this.value[3][3]+ " "
			   ;
	}
}

