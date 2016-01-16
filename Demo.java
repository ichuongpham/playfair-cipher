import java.util.ArrayList;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char key[] = {'c','h','u','o','n','g'};
		Playfair a = new Playfair(key);
		a.showMatrix();
		System.out.println("--------------------");
		a.encrypt("phamngocchuong");
		a.getCiphertext();
		
	}
}
