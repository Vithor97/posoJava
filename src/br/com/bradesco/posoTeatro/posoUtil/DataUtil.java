package br.com.bradesco.posoTeatro.posoUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {
	@SuppressWarnings("deprecation")
	public boolean validarIdade(int idade, Date data) {
		Date hoje = new Date();
		if (hoje.getYear() - data.getYear() < idade) {
			return false;
		}else if(hoje.getYear() - data.getYear() == idade) {
			if (hoje.getMonth() < data.getMonth()) {
				return false;
			}else if(hoje.getMonth() == data.getMonth()) {
				if (hoje.getDate() < data.getDate()) {
					return false;
				}
			}
		}
		return true;
	}
	
	//transforma a data em dd/MM/yyyy
	public static String transformaData(String dataEntrada) {
		SimpleDateFormat in= new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return out.format(in.parse(dataEntrada));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
