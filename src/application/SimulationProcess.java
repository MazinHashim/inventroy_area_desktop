package application;
import java.util.Scanner;

public class SimulationProcess {

	static Scanner scanner = new Scanner(System.in);
	public static double[] refDemandProb = {0.10,0.25,0.35,0.21,0.09};
	public static double[] leadTimeProb  = {0.6,0.3,0.1};
	
	private static void End_With_Shortage(Refrigrator ref) {
		if(Refrigrator.beginingInv < ref.RefDemand) {
			Refrigrator.shortageQuan +=  (ref.RefDemand - Refrigrator.beginingInv);
			Refrigrator.endingInv = 0;
		}
		else
			Refrigrator.endingInv =  Refrigrator.beginingInv - ref.RefDemand;
	}
	private static int Generator(int rand,double[] RDA,int limit) {
		
		for (int i = 0; i < RDA.length; i++) {
			if(rand>0 && rand<=RDA[i]) {
				return i+limit;
			}
		}
		return -1;
	}

	public static String display(double[] RDA,String randVar,int limit){
		StringBuilder printer = new StringBuilder();
		printer.append("Demand\t\tRDA Of "+randVar+"\n");
		printer.append(limit+"\t\t\t"+1+" --> "+RDA[0]+"\n");
		for(int i=1; i<RDA.length; i++){
			printer.append(i+limit+"\t\t\t"+(RDA[i-1]+1)+" --> "+RDA[i]+"\n");
		}
		printer.append("\n");
		return printer.toString();
	}

	public static void SimAlgorithm(int days) {
		
		designController.getInstance().setSimProp(("Days\t\tBegin Inventory\t\tRandomRDA\t\tRef Demand\t\t"
				+ "End Inventory\t\tShortage\t\tOrder Quantity\t\tRandomRDA\t\tLead Time\n"));
		Refrigrator.beginingInv = 3;
		Refrigrator refrig = new Refrigrator();
		for (int i = 1; i <= days; i++) {
			
			int dem = (int) (Math.random() * 100)+1;
			int pbegin = Refrigrator.beginingInv;
			refrig.RefDemand = Generator(dem,refDemandProb,0);
			int prefdem = refrig.RefDemand;
			
			End_With_Shortage(refrig);
			int pend = Refrigrator.endingInv;
			int pshor = Refrigrator.shortageQuan;
			Refrigrator.beginingInv = Refrigrator.endingInv;
			int pleadT = 0;
			if(i == 1) {
				refrig.orderQuan = (Refrigrator.ORDER_UP_TO_LEVEL - Refrigrator.endingInv) + Refrigrator.shortageQuan;
				refrig.leadTime = 1;
				pleadT = refrig.leadTime;
			}
			
			if(refrig.leadTime == 0) {
				if(Refrigrator.shortageQuan != 0) {
					refrig.orderQuan -= Refrigrator.shortageQuan;
					Refrigrator.shortageQuan = 0;
				}
				Refrigrator.beginingInv = Refrigrator.endingInv + refrig.orderQuan;
				refrig.leadTime+=5;
			}else refrig.leadTime --;
			
			int lead=0;
			if(i % Refrigrator.REVIEW_DAYS == 0) {
				refrig.orderQuan = (Refrigrator.ORDER_UP_TO_LEVEL - Refrigrator.endingInv) + Refrigrator.shortageQuan;
				lead =  (int) (Math.random() * 10)+1;
				refrig.leadTime = Generator(lead,leadTimeProb,1);
				pleadT = refrig.leadTime;
			}
			designController.getInstance().setSimProp(i+"\t\t\t"+pbegin+"\t\t\t\t"+dem+"\t\t\t\t"+prefdem+"\t\t\t\t"+pend+"\t\t\t\t"+
					pshor+"\t\t\t"+refrig.orderQuan+"\t\t\t\t\t"+lead+"\t\t\t\t"+pleadT+"\n");
			designController.getInstance().setSimProp("----------------------------------------"
					+ "------------------------------------------------------------------------"
					+ "-----------------------------------------------------------------------\n");
		}
	}
}
