package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("design.fxml"));
			Scene scene = new Scene(root);
			scene.setFill(Color.TRANSPARENT);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
//public void Simulation(ActionEvent event) {
//	
//	refDemandProb[0] *=100;
//	for(int i=1; i<refDemandProb.length; i++) {
//		refDemandProb[i] = refDemandProb[i] * 100 + refDemandProb[i-1];
//	}
//	leadTimeProb[0] *=10;
//	for(int i=1; i<leadTimeProb.length; i++) {
//		leadTimeProb[i] = leadTimeProb[i] * 10 + leadTimeProb[i-1];
//	}
//	display(refDemandProb,"Refrigrators",0);
//	System.out.println();
//	display(leadTimeProb,"Lead time",1);
//	System.out.print("How Many Days for Simulation Process : ");
//	int days = Integer.parseInt(DaysText.getText());
//	System.out.println("Days\tBegin Inventory\t\tRandomRDA\tRef Demand\tEnd Inv\t\tShortage\tOrder\t\tRandomRDA\tLead Time\n");
//	Refrigrator.beginingInv = 3;
//	Refrigrator refrig = new Refrigrator();
//	for (int i = 1; i <= days; i++) {
//		
//		int dem = (int) (Math.random() * 100)+1;
//		int pbegin = Refrigrator.beginingInv;
//		refrig.RefDemand = Generator(dem,refDemandProb,0);
//		int prefdem = refrig.RefDemand;
//		
//		End_With_Shortage(refrig);
//		int pend = Refrigrator.endingInv;
//		int pshor = Refrigrator.shortageQuan;
//		Refrigrator.beginingInv = Refrigrator.endingInv;
//		int pleadT = 0;
//		if(i == 1) {
//			refrig.orderQuan = (Refrigrator.ORDER_UP_TO_LEVEL - Refrigrator.endingInv) + Refrigrator.shortageQuan;
//			refrig.leadTime = 1;
//			pleadT = refrig.leadTime;
//		}
//		
//		if(refrig.leadTime == 0) {
//			if(Refrigrator.shortageQuan != 0) {
//				refrig.orderQuan -= Refrigrator.shortageQuan;
//				Refrigrator.shortageQuan = 0;
//			}
//			Refrigrator.beginingInv = Refrigrator.endingInv + refrig.orderQuan;
//			refrig.leadTime+=5;
//		}else refrig.leadTime --;
//		
//		int lead=0;
//		if(i % Refrigrator.REVIEW_DAYS == 0) {
//			refrig.orderQuan = (Refrigrator.ORDER_UP_TO_LEVEL - Refrigrator.endingInv) + Refrigrator.shortageQuan;
//			lead =  (int) (Math.random() * 10)+1;
//			refrig.leadTime = Generator(lead,leadTimeProb,1);
//			pleadT = refrig.leadTime;
//		}
//		list.addAll(new Refrigrator(i,pbegin,dem,prefdem,pend,pshor,refrig.orderQuan,lead,pleadT));
//		simulTable.setItems(list);
//	}
//}
//private static void End_With_Shortage(Refrigrator ref) {
//	if(Refrigrator.beginingInv < ref.RefDemand) {
//		Refrigrator.shortageQuan +=  (ref.RefDemand - Refrigrator.beginingInv);
//		Refrigrator.endingInv = 0;
//	}
//	else
//		Refrigrator.endingInv =  Refrigrator.beginingInv - ref.RefDemand;
//}
//private static int Generator(int rand,double[] RDA,int limit) {
//	
//	for (int i = 0; i < RDA.length; i++) {
//		if(rand>0 && rand<=RDA[i]) {
//			return i+limit;
//		}
//	}
//	return -1;
//}
//public static void display(double[] RDA,String randVar,int limit){
//	System.out.println("Demand\tRDA Of "+randVar);
//	System.out.println(limit+"\t"+1+" --> "+RDA[0]);
//	for(int i=1; i<RDA.length; i++){
//		System.out.println(i+limit+"\t"+(RDA[i-1]+1)+" --> "+RDA[i]);
//	}
//	System.out.println();
//}