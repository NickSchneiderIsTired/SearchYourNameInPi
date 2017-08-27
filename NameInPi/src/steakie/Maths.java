package steakie;

public class Maths {

    public Maths() {

    }

    public int getNumber(char letter) {

        switch (letter) {
            case 'a' : return 0;
            case 'ä' : return 0;
            case 'b' : return 1;
            case 'c' : return 2;
            case 'd' : return 3;
            case 'e' : return 4;
            case 'f' : return 5;
            case 'g' : return 6;
            case 'h' : return 7;
            case 'i' : return 8;
            case 'j' : return 9;
            case 'k' : return 10;
            case 'l' : return 11;
            case 'm' : return 12;
            case 'n' : return 13;
            case 'o' : return 14;
            case 'ö' : return 14;
            case 'p' : return 15;
            case 'q' : return 16;
            case 'r' : return 17;
            case 's' : return 18;
            case 't' : return 19;
            case 'u' : return 20;
            case 'ü' : return 20;
            case 'v' : return 21;
            case 'w' : return 22;
            case 'x' : return 23;
            case 'y' : return 24;
            case 'z' : return 25;
            case 'A' : return 0;
            case 'Ä' : return 0;
            case 'B' : return 1;
            case 'C' : return 2;
            case 'D' : return 3;
            case 'E' : return 4;
            case 'F' : return 5;
            case 'G' : return 6;
            case 'H' : return 7;
            case 'I' : return 8;
            case 'J' : return 9;
            case 'K' : return 10;
            case 'L' : return 11;
            case 'M' : return 12;
            case 'N' : return 13;
            case 'O' : return 14;
            case 'Ö' : return 14;
            case 'P' : return 15;
            case 'Q' : return 16;
            case 'R' : return 17;
            case 'S' : return 18;
            case 'T' : return 19;
            case 'U' : return 20;
            case 'Ü' : return 20;
            case 'V' : return 21;
            case 'W' : return 22;
            case 'X' : return 23;
            case 'Y' : return 24;
            case 'Z' : return 25;
            default : return 0;
        }

    }

    public long nameToNumber(int[] faktoren) {
    	long x = 0;
    	
    	for(int i = 0; i < faktoren.length; i++) {
    		x += faktoren[i] * Math.pow(26, faktoren.length - 1 - i);
    	}
    	
    	return x;
    }
    
  
    
}

