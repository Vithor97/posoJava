package br.com.bradesco.posoTeatro.posoUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {
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
	
	public Date converteData(Date data, String pattern) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(data);
		try {
			data = simpleDateFormat.parse(date);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return data;
	}
}
