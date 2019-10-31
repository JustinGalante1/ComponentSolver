class Solver{
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
	
	
	public static void main(String[] args) {
		double R1;
		double R2;
		double C1;
		double ratio;
		double tolerance = 0.30;
		// hi there
		for(int x=0;x<resistors.length;x++) {
			R1 = resistors[x];
			for(int y=0;y<resistors.length;y++) {
				R2 = resistors[y];
				ratio = R1/R2;
				//System.out.println(R1/R2);
				if(ratio >= 20 && ratio <=22) {
					//System.out.println("----------BRUH-------------");
					
					for(int z=0;z<capacitors.length;z++) {
						C1 = capacitors[z];
						if(R2*C1 <= .001545 + .001545*tolerance && R2*C1 >= .001545 - .001545*tolerance) {
							System.out.println("Exact Ratio = " + R1/R2);
							System.out.println("R1 = " + R1);
							System.out.println("R2 = " + R2);
							System.out.println("Exact number = " + R2*C1);
							System.out.println("R2 = " + R2);
							System.out.println("C1 = " + C1);
							System.out.println("------------------------------------------------");
						}
					}
					
				}
				
			}
		}
	}
}


