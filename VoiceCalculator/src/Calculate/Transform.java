package Calculate;

public class Transform {
	public static String transform(double number, int binary) {
		int integerPart=(int)number;
        String integerBase=convertIntegerToBase(integerPart, binary);
        double fractionalPart=number-integerPart;
        StringBuilder fractionalBase=new StringBuilder();
        while(fractionalPart>0) {
            if(fractionalBase.length()>32) {
                break;
            }
            fractionalPart*=binary;
            int digit=(int)fractionalPart;
            fractionalBase.append(convertDigitToChar(digit));
            fractionalPart-=digit;
        }
        return fractionalPart==0?integerBase:integerBase+"."+fractionalBase.toString();
    }
	
	private static String convertIntegerToBase(int integerPart, int binary) {
        if(integerPart==0) {
            return "0";
        }
        StringBuilder baseRepresentation=new StringBuilder();
        while(integerPart>0) {
            int digit=integerPart % binary;
            baseRepresentation.append(convertDigitToChar(digit));
            integerPart/=binary;
        }
        return baseRepresentation.reverse().toString();
    }
	
	private static char convertDigitToChar(int digit) { 
    	if(digit<10) {
            return (char)('0'+digit);
        }
    	else {
            return (char)('A'+(digit-10));
        }
    }
}
