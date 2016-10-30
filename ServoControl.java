import com.pi4j.wiringpi.SoftPwm;
import java.io.*;
public class ServoControl {

    public static void main(String[] args) throws IOException{

        // initialize wiringPi library
        com.pi4j.wiringpi.Gpio.wiringPiSetup();
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int pinNum=0;
		int value=0;
		ServoControl control = new ServoControl();
		String input ="";
		
		while(true){
			
			System.out.print("pinNum :");
			input = br.readLine();
			pinNum = Integer.parseInt(input);
			
			System.out.print("value :");
			input = br.readLine();
			value = Integer.parseInt(input);
			
			if(pinNum < 0 || value < 0)
				break;
			
			try{
				control.controlServo(pinNum, value);
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		// System.out.println(pinNum+"|"+value);
    }
	
	void controlServo(int pin, int value) throws InterruptedException{
		
		System.out.println("pin :"+pin+" value :"+value);
		
		// set pin pulse 20ms
		SoftPwm.softPwmCreate(pin, 0, 200);
		Thread.sleep(100);
        
		//if value is smaller than 0 make it neutral(17)
		if(value <0)
			value=14;
		
		SoftPwm.softPwmWrite(pin, value);
		Thread.sleep(1000);
			
	}
}
