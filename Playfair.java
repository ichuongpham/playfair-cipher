import java.util.ArrayList;

class Playfair {
	private char alphaArr[][];
	private String ciphertext; 
	
	Playfair() {
		alphaArr = new char[5][5];
		int count = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if((char)(97+count) == 'j') {
					alphaArr[i][j]= (char)(97+count+1);
					count+=2;
				}
				else {
					alphaArr[i][j] = (char)(97+count);
					count++;
				}
			}
		}
	}
	
	Playfair(char key[]) {
		alphaArr = new char[5][5];
		char ch = 'a';
		int n = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(n < key.length)
					alphaArr[i][j] = key[n++];
				else
					//alphaArr[i][j] = 'x';
					break;
			}
		}
		
		char restOfAlpha[] = new char[25-key.length]; 
		for(int i = 0; i < restOfAlpha.length; i++) {
			for(int j = 0; j < key.length; j++) {
				if(ch == key[j] || ch == 'j') {
					ch++;
					j = 0;
				}
			}
			restOfAlpha[i] = ch++;
		}
		int k = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(alphaArr[i][j] == 0) {
					alphaArr[i][j] = restOfAlpha[k];
					k++;
				}
			}
		}
	}
	
	String getCiphertext() {
		System.out.println("Ciphertext:");
		System.out.println(ciphertext);
		return ciphertext;
	}
	
	void encrypt(String msg) {
		String result = "";
		char msgArr[] = msg.toCharArray();
		Object modifiedMsg[] = modifyMsg(msgArr);
		
		System.out.println("Plaintext: ");
		for(int i = 0; i < modifiedMsg.length; i++) {
			System.out.print(modifiedMsg[i] + "");
		}
		System.out.println();
		for(int i = 0; i < modifiedMsg.length; i+=2) {
			result += substitute(((char)modifiedMsg[i]), (char)modifiedMsg[i+1]);
		}	
		ciphertext = result;
	}
	
	private String substitute(char ch1, char ch2 ) {
		
		int ch1_i = -1, ch1_j = -1;
		int ch2_i = -1, ch2_j = -1;
		String chunk = "";
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(ch1_i != -1 && ch1_j != -1 && ch2_i != -1 && ch2_j != -1) {
					break;
				}
				if(alphaArr[i][j] == ch1) {
					ch1_i = i; 
					ch1_j = j;
				}	
				if(alphaArr[i][j] == ch2) {
					ch2_i = i;
					ch2_j = j;
				}
			}
			if(ch1_i != -1 && ch1_j != -1 && ch2_i != -1 && ch2_j != -1) {
				break;
			}			
		}
		
		if(ch1_i == ch2_i) {
			chunk = Character.toString(alphaArr[ch1_i][(ch1_j+1)%5])+Character.toString(alphaArr[ch2_i][(ch2_j+1)%5]);
		}
		else if(ch1_j == ch2_j) {
			chunk = Character.toString(alphaArr[(ch1_i+1)%5][ch1_j])+Character.toString(alphaArr[(ch2_i+1)%5][ch2_j]);
		}
		else {
			chunk = Character.toString(alphaArr[ch1_i][ch2_j])+Character.toString(alphaArr[ch2_i][ch1_j]);
		}
		
		return chunk;
	}
	
	private Object[] modifyMsg(char[] msg) {
		ArrayList al = new ArrayList();
		
		for(int i = 0; i < msg.length; i++) {
			al.add(msg[i]);
		}
		
		/*
		for(int i = 0; i < al.size(); i++) {
			System.out.print(al.get(i) + "-");
		}
		System.out.println(al.size());
		*/
		
		for(int i = 0; i < al.size(); i++) {
			if( i == al.size() - 1 && i % 2 == 0  ) {
				al.add(i+1, 'x');
				break;
			}
			if(i+1 < al.size() && al.get(i) == al.get(i+1) && i % 2 == 0) {
				al.add(i+1, 'x');
				i=0;
			}
		}
		return al.toArray();
	}
	
	void showMatrix() {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				System.out.print(alphaArr[i][j] + "  ");
			}
			System.out.println();
		}
	}
}
