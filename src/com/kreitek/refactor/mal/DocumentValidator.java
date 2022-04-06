package com.kreitek.refactor.mal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class DocumentValidator 
{
    public enum TipoUltCaracter {
        LETRA, NUMERO, AMBOS
    }

    private static DocumentValidator doc;

    private DocumentValidator() {
        if (doc != null) {
            throw new RuntimeException("Usage getInstance() method to create");
        }
    }

    public static DocumentValidator getInstance() {
        if (doc == null) {
            synchronized(DocumentValidator.class) {
                if (doc == null) {
                    doc = new DocumentValidator();
                }
            }
        }
        return doc;
    }

    public String DNIE(String codigo) 
    {
        if (codigo.length() != 9)
        return ( "DNI " + codigo + " es: " + false);
	
		String primeraLetra =  String.valueOf(codigo.charAt(0));
		int parteNumerica = Integer.parseInt(codigo.substring (1, 8));
		
		if (primeraLetra.equalsIgnoreCase("X"))
			primeraLetra = "0";
		else if (primeraLetra.equalsIgnoreCase("Y"))
			primeraLetra = "1";
		else if (primeraLetra.equalsIgnoreCase("Z"))
			primeraLetra = "2";
		
		if(Character.isDigit(codigo.charAt(0)))
			{parteNumerica = Integer.parseInt(codigo.substring (0, 8));}
		
		String ultimaLetra = codigo.substring (8); 
		String palabrasGancho="TRWAGMYFPDXBNJZSQVHLCKE"; 
	    int letraDni=  parteNumerica % 23; 
	    char letra = palabrasGancho.charAt(letraDni); 
	    String letraGancho = Character.toString(letra);
	    boolean validaLetra = letraGancho.equalsIgnoreCase(ultimaLetra);
        if (validaLetra)
            return ( "DNI " + codigo + " es: " + true); 
        else 
            return ( "DNI " + codigo + " es: " + false); 
    }

    public String CIF(String codigo) {
        codigo = codigo.toUpperCase();

        if ("ABCDEFGHJKLMNPQRSUVW".indexOf(codigo.charAt(0)) == -1) 
            return ( "DNI " + codigo + " es: " + false);

        final Pattern mask = Pattern
                .compile("[ABCDEFGHJKLMNPQRSUVW][0-9]{7}[A-Z[0-9]]{1}");
        final Matcher matcher = mask.matcher(codigo);
        if (!matcher.matches()) 
            return ( "DNI " + codigo + " es: " + false);

        final char primerCar = codigo.charAt(0);
        final char ultimoCar = codigo.charAt(codigo.length() - 1);

        TipoUltCaracter tipUltCar;

        // si empiezo por P,Q, S, K o W la última letra tiene que ser una LETRA
        if (primerCar == 'P' || primerCar == 'Q' || primerCar == 'S' || primerCar == 'K' || primerCar == 'W') {
            tipUltCar = TipoUltCaracter.LETRA;
            if (!(ultimoCar >= 'A' && ultimoCar <= 'Z')) {
                return ( "DNI " + codigo + " es: " + false);
            }
        // si empiezo por A, B, E o H la última letra tiene que ser un número
        } else if (primerCar == 'A' || primerCar == 'B' || primerCar == 'E'
                || primerCar == 'H') {
            tipUltCar = TipoUltCaracter.NUMERO;
            if (!(ultimoCar >= '0' && ultimoCar <= '9')) {
                return ( "DNI " + codigo + " es: " + false);
            }
        } else {
            tipUltCar = TipoUltCaracter.AMBOS;
        }

        final String digitos = codigo.substring(1, codigo.length() - 1);
        
        Integer sumaPares = 0;
        for (int i = 1; i <= digitos.length() - 1; i = i + 2) {
            sumaPares += Integer.parseInt(digitos.substring(i, i + 1));
        }

        Integer sumaImpares = 0;
        for (int i = 0; i <= digitos.length() - 1; i = i + 2) {
            Integer cal = Integer.parseInt(digitos.substring(i, i + 1)) * 2;
            if (cal.toString().length() > 1) {
                cal = Integer.parseInt(cal.toString().substring(0, 1))
                        + Integer.parseInt(cal.toString().substring(1, 2));
            }
            sumaImpares += cal;
        }

        final Integer total = sumaPares + sumaImpares;

        Integer numControl = 10 - (total % 10);

        int pos = numControl == 10? 0:numControl;
        final char carControl = "JABCDEFGHI".charAt(pos);

        if (tipUltCar == TipoUltCaracter.NUMERO) {
            final Integer ultCar = Integer.parseInt(Character
                .toString(ultimoCar));
            if (pos != ultCar.intValue()) {
                return ( "DNI " + codigo + " es: " + false);
            }

        } else if (tipUltCar == TipoUltCaracter.LETRA) {
            if (carControl != ultimoCar) {
                return ( "DNI " + codigo + " es: " + false);
            } 
        } else {
            // find all occurrences forward
            Integer ultCar = -1;

            ultCar = "JABCDEFGHI".indexOf(ultimoCar);

            if (ultCar < 0){
                ultCar = Integer.parseInt(Character.toString(ultimoCar));
                if (pos != ultCar.intValue()) {
                    return ( "DNI " + codigo + " es: " + false);
                }
            }
            if ((pos != ultCar.intValue()) && (carControl != ultimoCar)) {
                return ( "DNI " + codigo + " es: " + false);
            }
        }
        return ( "DNI " + codigo + " es: " + true);
    }
}