package com.tikal.toledo.util;

import java.util.ArrayList;
import java.util.List;

import com.tikal.toledo.model.Producto;
import com.tikal.toledo.model.Tornillo;

public class Parseador {
	
	public static List<Producto> procesaHerramientas(String cadena){
		List<Producto> lista = new ArrayList<Producto>();
		String[] renglones= cadena.split("\n");
		for(int i=1 ;i< renglones.length;i++){
			String reng= renglones[i];
			reng= reng.replaceAll("[$]", "");
			reng= reng.replaceAll("\r", "");
			Producto t= new Producto();
			String[] values = reng.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			if(values.length>4){
				String nombre= values[0];
				if( nombre.startsWith("\"") && nombre.endsWith("\"")){
					nombre= nombre.substring(1, nombre.length()-2);
				}
			t.setNombre(nombre);
			if(values[1].length()>0){
				t.setClave(values[1]);
			}
			if(values[3].length()>0)
			t.setPrecioMayoreo(Float.parseFloat(values[3].replaceAll(",", "").replace("\"", "").replace("-", "0")));
			if(values[2].length()>0)
			t.setPrecioMostrador(Float.parseFloat(values[2].replaceAll(",", "").replace("\"", "").replace("-", "0")));
			if(values[4].length()>0)
			t.setPrecioCredito(Float.parseFloat(values[4].replaceAll(",", "").replace("\"", "").replace("-", "0")));
			if(values[5].length()>0)
			t.setMarca(values[5]);
			if(values[6].length()>0)
			t.setExistencia(Integer.parseInt(values[6]));
			
			
			//llenar datos tornillos
			
			lista .add(t);
			}
		}
		return lista;
	}
	
	public static List<Tornillo> procesaTornillos(String cadena){
		List<Tornillo> lista = new ArrayList<Tornillo>();
		cadena= cadena.replaceAll("$", "");
		String[] renglones= cadena.split("\n");
		for(int i=1 ;i< renglones.length;i++){
			String reng= renglones[i];
			reng= reng.replaceAll("[$]", "");
			reng= reng.replaceAll("\r", "");
			reng= reng.replaceAll("#", "");
			Tornillo t= new Tornillo();
			String[] values = reng.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			if(values.length>5){
			
				
				String nombre= values[1];
				if( nombre.startsWith("\"") && nombre.endsWith("\"")){
					nombre= nombre.substring(1, nombre.length()-2);
				}
			t.setNombre(nombre);
			
			t.setMedidas(values[2]);
			if(values[3].length()>0)
			t.setPrecioReferencia(Float.parseFloat(values[3].replaceAll(",", "").replace("\"", "").replace("-", "0")));
			if(values[4].length()>0)
			t.setPrecioMostrador(Float.parseFloat(values[4].replaceAll(",", "").replace("\"", "").replace("-", "0")));
			if(values[5].length()>0)
			t.setPrecioMayoreo(Float.parseFloat(values[5].replaceAll(",", "").replace("\"", "").replace("-", "0")));
			if(values[6].length()>0)
			t.setPrecioCredito(Float.parseFloat(values[6].replaceAll(",", "").replace("\"", "").replace("-", "0")));
			
			if(values.length==8){
				if(values[7].trim().length()>0){
					t.setExistencia(Integer.parseInt(values[7].replaceAll(",", "").replace("\"", "")));
				}
			}
			if(values[0].length()>0){
				t.setClave(values[0]+getClave2(t.getMedidas()));
			}else{
				t.setClave(getClave2(t.getMedidas()));
			}
			
//			if(values[0].length()>0){
//				t.setClave(values[0]);
//			}else{
//				t.setClave(getClave(t.getNombre(),t.getMedidas()));
//			}
			
			lista.add(t);
			}
			
		}
		return lista;
	}
	
	public static List<Tornillo> parsear(String cadena){
		List<Tornillo> lista= new ArrayList<Tornillo>();
		cadena=cadena.replace("<P>TOLEDO \n</P>", "");
		cadena=cadena.replace("&quot;", "\"");
		cadena=cadena.replace("<P>TOLEDO \nTOLEDO \n</P>", "");
		cadena=cadena.replace("<TH>CLAVE", "<TD>CLAVE");
		String[] primeros=cadena.split("<TD>CLAVE");
		
		for(int i=1;i< primeros.length; i++){
			String s= primeros[i];
			lista.addAll(parseaProducto(s));
		}
		
		return lista;
	}
	
	private static List<Tornillo> parseaProducto(String s){
		List<Tornillo> lista= new ArrayList<Tornillo>();
		s = s.substring(s.indexOf("<TR>"));
		s = s.substring(s.indexOf("<TD>")+4);
		String nombre = s.substring(0, s.indexOf("</TD>"));
		s=s.substring(s.indexOf("<TR>"));
		String[] elementos= s.split("<TR>");
		for(String el:elementos){
			System.out.println(el);
			if(el.contains("<TH/>")||(el.split("<TD/>").length>2)){
				if(el.split("<TD/>").length<4){
					String aux= el.replace("<TD", "<TH");
					aux=aux.replace("</TD", "</TH");
				nombre = aux.substring(aux.indexOf("<TH>")+4,aux.indexOf("</TH>"));}
				continue;
			}
			
			if(el.contains("<TH>CLAVE")){
				continue;
			}
			
			if(el.length()>0){
			Tornillo t= new Tornillo();
			el=el.replace("TH", "TD");
			el=el.replace("</TR>", "");
			el=el.replace("</TD>", "");
			el=el.replace("<TD/>", "");
			el=el.replace("\r", "");
			el=el.replace("\n", "");
			el=el.replace("<P>", "");
			el=el.replace("</P>", "");
			el=el.replace("TOLEDO", "");
			String[] atts= el.split("<TD>");
			if(atts.length>1){
			t.setNombre(nombre.trim());
			
			t.setMedidas(atts[2].trim());
			t.setClave(getClave(nombre,t.getMedidas()));
			t.setPrecioMostrador(Float.parseFloat(atts[3].replace("$", "").replace(",", "").replace("</Table>","").replace("</Sect>", "").replace("<Sect>", "").replace("<Table>", "")));
			lista.add(t);
			}
			}
			
		}
		
		
		return lista;
	}
	
	public static String getClave(String nombre,String medidas){
		String clave="";
		nombre = nombre.replaceAll("\\."," ");
		nombre = nombre.trim();
		String[] palabras= nombre.split(" ");
		for(String palabra:palabras){
			if(palabra.length()>0){
			if(palabra.matches ("^.*\\d.*$")){
//				clave+=palabra;
				continue;
			}else{
				clave+= palabra.substring(0, 1);
			}
			}
		}
		if(medidas!=null){
		for(int i=0; i<medidas.length();i++){
			if(Character.isDigit(medidas.charAt(i))){
				clave+=medidas.charAt(i);
			}
			
		}
		}
		return clave.replace("/", "");
	}

	public static String getClave2(String medidas){
		String clave="";
		
		if(medidas!=null){
			medidas = medidas.toLowerCase();
			if(medidas.contains("x")){
				String[] pals= medidas.split("x"); 
				if(pals[0].contains("-")){
					pals[0]= pals[0].substring(0,pals[0].indexOf("-"));
					pals[0]= pals[0].replace("-","");
				}
				medidas= pals[0]+pals[1];
			}
			for(int i=0; i<medidas.length();i++){
				if(Character.isDigit(medidas.charAt(i))){
					clave+=medidas.charAt(i);
				}
			
			}
		}
		return clave.replace("/", "");
	}
}
