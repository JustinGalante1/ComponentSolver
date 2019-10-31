import java.awt.GridBagConstraints;

public class NonInvertingOpAmp {
	static double[] resistors = {1.0, 1.2, 1.5, 1.8, 2.2, 2.7, 3.3, 3.9, 4.7, 5.6, 6.8, 8.2,
			  10, 12, 15, 18, 22, 27, 33, 39, 47, 56, 68, 82, 100, 120, 150,
			  180, 220, 270, 330, 390, 470, 560, 680, 820, 1000, 1200, 1500,
			  1800, 2200, 2700, 3300, 3900, 4700, 5600, 6800, 8200, 10000, 
			  12000, 15000, 18000, 22000, 27000, 33000, 39000, 47000, 56000,
			  68000, 82000, 100000, 120000, 150000, 180000, 220000, 270000,
			  330000, 390000, 470000, 560000, 680000, 820000, 1000000};
	static double[] capacitors = {0.000000001, 0.0000000047, 0.00000001, 0.000000047, 0.0000001,
			   0.00000047, 0.000000002, 0.00000002, 0.0000047, 0.00001, 0.000022,
			   0.0001, 0.00022, 0.001, 0.00000022, 0.00000033};
	
	double gain;
	double R1;
	double R2;
	
	public NonInvertingOpAmp() {
		this.R1 = 0;
		this.R2 = 0;
		this.gain = 0;
	}
	
	public double getGain() {
		return this.gain;
	}
	
	public void setGain(double gain) {
		this.gain = gain;
	}
	
	public double getR1() {
		return R1;
	}
	
	public double getR2() {
		return R2;
	}
	
	public void gainSolver(double gain, double tolerance) {
		double R1temp = 0;
		double R2temp = 0;
		double error = Double.MAX_VALUE;
		double temp;
		//double returnThis[] = new double[2];
		for(int x=resistors.length-1;x>=0;x--) {
			R1temp = resistors[x];
			for(int y=resistors.length-1;y>=0;y--) {
				R2temp = resistors[y];
				if(Math.abs(gain - (1+(R1temp/R2temp))) < gain*tolerance){
					temp = Math.abs(gain - (1+(R1temp/R2temp)));
					if(temp < error){
						error = temp;
						this.R1 = R1temp;
						this.R2 = R2temp;
					}
				}
			}
		}
	}
}
