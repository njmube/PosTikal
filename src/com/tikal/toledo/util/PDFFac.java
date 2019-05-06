package com.tikal.toledo.util;



import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
//import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
//import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tikal.toledo.model.Detalle;
import com.tikal.toledo.model.Venta;
import com.tikal.toledo.sat.cfd.Comprobante.Conceptos.Concepto;

import java.io.*;
import java.net.MalformedURLException;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.servlet.ServletOutputStream;
//import java.util.Date;
//import java.util.List; 

public class PDFFac {


	private BaseColor tikalColor;
	private Font fontHead = new Font(Font.FontFamily.HELVETICA, 7.5F, Font.NORMAL);
	private PdfPCell emptyCell = new PdfPCell();
	public  PDFFac( Venta v, String cliente, OutputStream ops) throws MalformedURLException, IOException  {

    	
    	try {
    		
    		Document document = new Document(PageSize.LETTER,15,15,45,30);   	  
	        PdfWriter.getInstance(document,ops);
	        document.open();
//    		Rectangle envelope = new Rectangle(320, 500);
//    	//	Document pdfDoc = new Document(envelope, 230f, 10f, 100f, 0f);
//    		Document document = new Document(envelope,0,0,0,0);   	  
//	        PdfWriter.getInstance(document,ops);
//	        document.open();
//	        
//    		PrintService service = PrintServiceLookup.lookupDefaultPrintService();
//    		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
//    		DocPrintJob pj = service.createPrintJob(); 
//    		
//    		
//    	    String ss=new String("Aquí lo que vamos a imprimir.");
//    	    byte[] bytes;
//    	    //Transformamos el texto a bytes que es lo que soporta la impresora
//    	    bytes=ss.getBytes();
//    	    //Creamos un documento (Como si fuese una hoja de Word para imprimir)
//    	    Doc doc=new SimpleDoc(bytes,flavor,null);
//    	    //Obligado coger la excepción PrintException
//    	    try {
//	    	    //Mandamos a impremir el documento
//	    	    pj.print(doc, null);
//    	    }
//    	    catch (PrintException x) {
//    	    	System.out.println("Error al imprimir: "+x.getMessage());
//    	    }
    	    
    	    Font f0 = new Font();
      	    f0.setStyle(1);
    	    f0.setSize(6);
      	    f0.setColor(BaseColor.BLACK);
    	    
    	    
    	    Font f1 = new Font();
    	  //  f1.setStyle(1);
    	    f1.setSize(10);
    	    f1.setColor(BaseColor.BLACK);
    	    
    	    Font f2 = new Font();
    	   // f2.setStyle(2);
    	    f2.setSize(6);
    	    f2.setColor(BaseColor.BLACK);
    	    f2.setStyle(1);
    	    
    	    Font f3 = new Font();
    	    f3.setStyle(1);
    	    f3.setSize(10);
    	    f3.setColor(BaseColor.BLACK);
    	   
    	    Font f4 = new Font();
        	  //  f1.setStyle(1);
        	    f4.setSize(8);
        	    f4.setColor(BaseColor.BLACK);
        	    
            PdfPTable table = new PdfPTable(3);   
            
            String g="";
       //     Image imagen = Image.getInstance("images/AutosolverLogo.jpg");
          //  imagen.scaleAbsolute(100, 40);
           
      
         //   PdfPCell c1 = new PdfPCell(imagen);
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            c1.setVerticalAlignment(Element.ALIGN_TOP);
//            c1.setColspan(2);
//            c1.setRowspan(10);
//            c1.setBorder(Rectangle.NO_BORDER);
//            table.addCell(c1);
           
            Paragraph p2 = new Paragraph("No. Folio:"+v.getFolio()+"\n",f3);
            PdfPCell c2 = new PdfPCell(p2);
            c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c2.setVerticalAlignment(Element.ALIGN_BOTTOM);
         //   c2.setHorizontalAlignment(Element.ALIGN_LEFT);
            c2.setColspan(2);c2.setRowspan(10);
            c2.setBorder(Rectangle.NO_BORDER);
            table.addCell(c2);
   //
           
            
          //  PdfPTable table2 = new PdfPTable(2);
            
            Paragraph linea = new Paragraph("\n",f1);
            PdfPCell c9 = new PdfPCell(linea);
            //c9.setBackgroundColor(black);
            c9.setHorizontalAlignment(Element.ALIGN_CENTER);
            c9.setColspan(3);
            c9.setRowspan(5);
            c9.setBorder(Rectangle.NO_BORDER);
           // c9.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(c9);
            document.add(table);
            
            agregarCelda(cliente, f1, table, true);
			agregarCelda(String.valueOf(v.getFecha()), f1, table, true);
            
//            Paragraph p91 = new Paragraph("CLIENTE: "+cliente ,f1);
//            PdfPCell c91 = new PdfPCell(p91);
//            //c9.setBackgroundColor(black);
//            c91.setHorizontalAlignment(Element.ALIGN_CENTER);
//            c91.setColspan(2);
//            c91.setRowspan(1);
//         //   c91.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            table.addCell(c91);
//            
//            Paragraph p92 = new Paragraph("FECHA:"+v.getFecha(),f1);
//            PdfPCell c92 = new PdfPCell(p92);
//         
//            c92.setHorizontalAlignment(Element.ALIGN_CENTER);
//            c92.setColspan(1);
//            c92.setRowspan(1);
//          //  c92.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            table.addCell(c92);
//            
//            table.addCell(c9);
            
       
  
            
//            List<Detalle> lista= v.getDetalles();
//            
//            for (Detalle d:lista){
//            	 Paragraph p14 = new Paragraph(d.getDescripcion(),f1);
//                 PdfPCell c14 = new PdfPCell(p14);
//                 c14.setHorizontalAlignment(Element.ALIGN_LEFT);
//                //.setBackgroundColor(BaseColor.BLACK);
//                 c14.setColspan(1);
//                 table.addCell(c14);
//                 
//                 Paragraph p15 = new Paragraph(String.valueOf(d.getCantidad()),f1);
//                 PdfPCell c15 = new PdfPCell(p15);
//                 c15.setHorizontalAlignment(Element.ALIGN_LEFT);
//                // c15.setBackgroundColor(BaseColor.BLACK);
//                 c15.setColspan(1);
//                 table.addCell(c15);
//                 
//                 Paragraph p1 = new Paragraph(String.valueOf(d.getPrecioUnitario()),f1);
//                 PdfPCell c11 = new PdfPCell(p1);
//                 c11.setHorizontalAlignment(Element.ALIGN_LEFT);
//                // c15.setBackgroundColor(BaseColor.BLACK);
//                 c11.setColspan(1);
//                 table.addCell(c11);
//                 
//            }	
            
            
        	PdfPTable tablaConceptos = new PdfPTable(5);
    		tablaConceptos.setWidthPercentage(100);
    		tablaConceptos.setWidths(new float[] { 12, 12, 40, 13, 13 });

    		agregarCeldaConFondo("Cantidad", fontHead, tablaConceptos, true);

    		agregarCeldaConFondo("Unidad", fontHead, tablaConceptos, true);

    		agregarCeldaConFondo("Descripción", fontHead, tablaConceptos, true);

    		agregarCeldaConFondo("Valor unitario", fontHead, tablaConceptos, true);

    		agregarCeldaConFondo("Importe", fontHead, tablaConceptos, true);
    	//	document.add(tablaConceptos);
    		if (v.getDetalles() != null) {
    		//	List<Concepto> listaConceptos =v.getDetalles().;
    			for (Detalle concepto : v.getDetalles()) {
    				//agregarCelda(concepto.getNoIdentificacion(), font3, tablaConceptos, true);
    				agregarCelda(String.valueOf(concepto.getCantidad()), f1, tablaConceptos, true);
    				agregarCelda(concepto.getUnidad(), f1, tablaConceptos, true);
    				agregarCelda(concepto.getDescripcion(), f1, tablaConceptos, false);
    				agregarCelda(String.valueOf(concepto.getPrecioUnitario()), f1, tablaConceptos, true);
    				agregarCelda(String.valueOf(concepto.getCantidad()*concepto.getPrecioUnitario()), f1, tablaConceptos, true);
    			}
    		}
    		tablaConceptos.setSpacingBefore(4);
    		tablaConceptos.setSpacingAfter(3);
    		
    		PdfPTable tablaPrincipalConceptos = new PdfPTable(3);
    		crearNotaDoble(tablaPrincipalConceptos,false,48,4,48,tablaConceptos);
    		document.add(tablaPrincipalConceptos);
    		
    		
            
            
//                      
           // document.add(table);
            document.close();
    	    System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
    	} catch (DocumentException documentException) {
    	    System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
    	}
    }

      
//      public void GeneraComDisPdf(DetalleDiscrepanciaVo det, Document document) throws DocumentException {
//          // Aquí introduciremos el código para crear el PDF.      	  
//      
//    	  	PdfPTable table2 = new PdfPTable(12);      
//    	    Font fuente = new Font();
//	  	    fuente.setStyle(1);
//	  	    fuente.setSize(10);
//	  	    fuente.setStyle(Font.BOLD);
//	  	    Font f1 = new Font();
//	  	    f1.setStyle(2);
//	  	    f1.setSize(9);
//	  	    Font f2 = new Font();
//	  	    f2.setStyle(3);
//	  	    f2.setSize(8);
//            Paragraph d = new Paragraph("Logo");
//            PdfPCell c0 = new PdfPCell(d);
//            c0.setHorizontalAlignment(Element.ALIGN_LEFT);
//            c0.setColspan(12);
//            table2.addCell(c0);
//            Paragraph a = new Paragraph("CROSS AIR SERVICES, S.A. DE C.V.",f1);
//            PdfPCell celdaFinal = new PdfPCell(a);
//            celdaFinal.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            celdaFinal.setColspan(12);
//            table2.addCell(celdaFinal);
//            Paragraph b =new Paragraph("DISCREPANCY REPORT / REPORTE DE DISCREPANCIAS: \n",fuente);
//            PdfPCell c2 =new PdfPCell(b);
//            c2.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c2.setColspan(12);
//            table2.addCell(c2);
//          
//            Paragraph p2=new Paragraph("CUSTOMER / CLIENTE:"+det.getNombreEmpresa() ,f1);
//            PdfPCell c3 =new PdfPCell(p2);
//            c3.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c3.setColspan(12);
//            table2.addCell(c3);
//            
//            Paragraph p3=new Paragraph("MARK/MODEL - MARCA/MODELO:\n\n"+det.getModelo() ,f1);
//            PdfPCell c4 =new PdfPCell(p3);
//            c4.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c4.setColspan(3);
//            table2.addCell(c4);
//            
//            Paragraph p4=new Paragraph("N/S:\n\n"+det.getNoSerie() ,f1);
//            PdfPCell c5 =new PdfPCell(p4);
//            c5.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c5.setColspan(3);
//            table2.addCell(c5);
//            
//            Paragraph p5=new Paragraph("REG. / MATRICULA:\n\n"+det.getMatricula() ,f1);
//            PdfPCell c6 =new PdfPCell(p5);
//            c6.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c6.setColspan(3);
//            table2.addCell(c6);
//            
//            Paragraph p6=new Paragraph("O.T. #:\n\n"+det.getMatricula() ,f1);
//            PdfPCell c7 =new PdfPCell(p6);
//            c7.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c7.setColspan(3);
//            table2.addCell(c7);
//            
//            Paragraph p7=new Paragraph("DISCREPANCIA No:"+d.lastIndexOf(det) ,f1);
//            PdfPCell c8 =new PdfPCell(p7);
//            c8.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c8.setColspan(6);
//            table2.addCell(c8);
//            
//            Paragraph p8=new Paragraph("DATE / FECHA:"+det.getFechaOrden(),f1);
//            PdfPCell c9 =new PdfPCell(p8);
//            c9.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c9.setColspan(6);
//            table2.addCell(c9);
//            
//            Paragraph p9=new Paragraph("DISCREPANCY / DISCREPANCIA:",f1);
//            PdfPCell c10 =new PdfPCell(p9);
//            c10.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c10.setColspan(6);
//            table2.addCell(c10);
//            
//            Paragraph p10=new Paragraph("CORRECT ACCTION / ACCION CORRECTIVA:",f1);
//            PdfPCell c11 =new PdfPCell(p10);
//            c11.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c11.setColspan(6);
//            table2.addCell(c11);
//            
//            Paragraph p11=new Paragraph("\n"+det.getDescripcion()+"\n",fuente);
//            PdfPCell c12 =new PdfPCell(p11);
//            c12.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c12.setColspan(6);
//            table2.addCell(c12);
//            
//            Paragraph p12=new Paragraph("\n"+det.getAccion()+"\n",fuente);
//            PdfPCell c13 =new PdfPCell(p12);
//            c13.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c13.setColspan(6);
//            table2.addCell(c13);
//            
//            Paragraph p13=new Paragraph("\n",f1); ////celda invisible
//            PdfPCell c14 =new PdfPCell(p13);
//            c14.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c14.setColspan(6);
//            table2.addCell(c14);
//            
//            Paragraph p14=new Paragraph("MAN TIME HOURS / TIEMPO HORAS HOMBRE:",f1); 
//            PdfPCell c15 =new PdfPCell(p14);
//            c15.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c15.setColspan(6);
//            table2.addCell(c15);
//            
//            Paragraph p15=new Paragraph("DESCRIPTION / DESCRIPCION:",f1); 
//            PdfPCell c16 =new PdfPCell(p15);
//            c16.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c16.setColspan(2);
//            table2.addCell(c16);
//            
//            Paragraph p16=new Paragraph("PART NUMBER / NUMERO DE PARTE:",f1); 
//            PdfPCell c17=new PdfPCell(p16);
//            c17.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c17.setColspan(2);
//            table2.addCell(c17);
//            
//            Paragraph p17=new Paragraph("QUANTITY / CANTIDAD:",f1); 
//            PdfPCell c18=new PdfPCell(p17);
//            c18.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c18.setColspan(2);
//            table2.addCell(c18);
//            
//            Paragraph p18=new Paragraph("N/S REMOVABLE / N/S REMOVIDA:",f1); 
//            PdfPCell c19=new PdfPCell(p18);
//            c19.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c19.setColspan(2);
//            table2.addCell(c19);
//            
//            Paragraph p19=new Paragraph("N/S INSTALL / N/S INSTALADA:",f1); 
//            PdfPCell c20=new PdfPCell(p19);
//            c20.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c20.setColspan(2);
//            table2.addCell(c20);
//            
//            Paragraph p20=new Paragraph("OSERVATION/OBSERVATIONS:",f1); 
//            PdfPCell c21=new PdfPCell(p20);
//            c21.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c21.setColspan(2);
//            table2.addCell(c21);
//            
//            List<ComDisVo> cds = det.getComponentes();
//            System.out.println("cds:"+cds);
//            if (cds.isEmpty()){
//            	
//	            Paragraph pp=new Paragraph("\n",f1); 
//	            PdfPCell cp=new PdfPCell(pp);
//	            cp.setHorizontalAlignment(Element.ALIGN_CENTER);             
//	            cp.setColspan(2);
//	            table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);
//	            table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);
//	            table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);
//	            table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);
//            }
//            for (ComDisVo cd : cds){
//            	
//            	System.out.println("cds. nombre:"+cd.getNombre_componente());
//	            Paragraph p21=new Paragraph(cd.getNombre_componente(),f1); 
//	            PdfPCell c22=new PdfPCell(p21);
//	            c22.setHorizontalAlignment(Element.ALIGN_CENTER);             
//	            c22.setColspan(2);
//	            table2.addCell(c22);
//	            
//	            System.out.println("cds. parte:"+cd.getNoParte());
//	            Paragraph p22=new Paragraph(cd.getNoParte(),f1); 
//	            PdfPCell c23=new PdfPCell(p22);
//	            c23.setHorizontalAlignment(Element.ALIGN_CENTER);             
//	            c23.setColspan(2);
//	            table2.addCell(c23);
//	            
//	            Paragraph p23=new Paragraph(cd.getCantidad().toString(),f1); 
//	            PdfPCell c24=new PdfPCell(p23);
//	            c24.setHorizontalAlignment(Element.ALIGN_CENTER);             
//	            c24.setColspan(2);
//	            table2.addCell(c24);
//	            
//	            Paragraph p24=new Paragraph("N/A",f1); 
//	            PdfPCell c25=new PdfPCell(p24);
//	            c25.setHorizontalAlignment(Element.ALIGN_CENTER);             
//	            c25.setColspan(2);
//	            table2.addCell(c25);
//	            table2.addCell(c25);
//	            
//	            Paragraph p25=new Paragraph("las observaciones de horas hombre",f1); 
//	            PdfPCell c26=new PdfPCell(p25);
//	            c26.setHorizontalAlignment(Element.ALIGN_CENTER);             
//	            c26.setColspan(2);
//	            table2.addCell(c26);
//	            
//	            
//            
//            }
//            /////////////////////////validar cuantos comps vienen para poner los demas celdas vacias
//           // for (){
//            	
//          //  }
//            Paragraph p26=new Paragraph("REMOVIBLE BY/ REMOVIDO POR:\n",f1); 
//            PdfPCell c27=new PdfPCell(p26);
//            c27.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c27.setColspan(6);
//            table2.addCell(c27);
//            
//            Paragraph p27=new Paragraph("INSTALL BY/ INSTALADA POR:\n",f1); 
//            PdfPCell c28=new PdfPCell(p27);
//            c28.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c28.setColspan(6);
//            table2.addCell(c28);
//            
//            Paragraph p28=new Paragraph("SIGN / FIRMA:\n\n",f1); 
//            PdfPCell c29=new PdfPCell(p28);
//            c29.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c29.setColspan(6);
//            table2.addCell(c29);
//            table2.addCell(c29);
//            
//            Paragraph p29=new Paragraph("PERMISSION / LICENCIA:\n",f1); 
//            PdfPCell c30=new PdfPCell(p29);
//            c30.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c30.setColspan(6);
//            table2.addCell(c30);
//            table2.addCell(c30);
//            
//            Paragraph p30=new Paragraph("DATE / FECHA:\n",f1); 
//            PdfPCell c31=new PdfPCell(p30);
//            c31.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c31.setColspan(6);
//            table2.addCell(c31);
//            table2.addCell(c31);
//            
//            Paragraph p31=new Paragraph("CUSTOMER AUTORIZATION / AUTORIZADO POR EL CLIENTE:\n\n NAME / NOMBRE:\n\n SIGN / FIRMA:\n\n",f1); 
//            PdfPCell c32=new PdfPCell(p31);
//            c32.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c32.setColspan(12);
//            table2.addCell(c32);
//            
//            Paragraph p32=new Paragraph("                                  FORM AUTORIZATION / MEDIO DE AUTORIZACION:\n PERSONAL:\n\n BY TELEFON / VIA TELEFONICA:\n\n "
//            		+ "E-MAIL / CORREO ELECTRONICO:\n\n  HOUR / DATE / HORA Y FECHA \n\n",f1); 
//            PdfPCell c33=new PdfPCell(p32);
//            c33.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c33.setColspan(12);
//            table2.addCell(c33);
//            
//            Paragraph p33=new Paragraph("\n",f1); 
//            PdfPCell c34=new PdfPCell(p33);
//            c34.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c34.setColspan(6);
//            table2.addCell(c34);
//            
//            Paragraph p34=new Paragraph("Vo Bo INSPECTOR\n\n",f1); 
//            PdfPCell c35=new PdfPCell(p34);
//            c35.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c35.setColspan(6);
//            table2.addCell(c35);
//            
//            
//            document.add(table2);  
//            document.add(new Paragraph("\n\n\n"));
//            
//      }

      
     


	public String letras(Double number){
    	  Integer entero = number.intValue();//.longValue();
    	  double decimales = number - entero;
    	  
    	  
    	  StringBuffer resultado = new StringBuffer(); 
    	  String strEntero = letras(entero.doubleValue()); 
    	
    	  String strDecimales = letras(decimales); 
    	  resultado.append(strEntero); 
    	  resultado.append(" con "); 
    	  resultado.append(strDecimales); 
    	  return resultado.toString(); 
    	  
    	  //return "si";
      }
	private void agregarCeldaConFondo(String contenidoCelda, Font fuente, PdfPTable tabla, boolean centrado) {
		PdfPCell celda = new PdfPCell(new Paragraph(contenidoCelda, fuente));
		celda.setBorderWidth(1);
		celda.setBorderColor(new BaseColor(209,227,233));
		celda.setPadding(5);
		celda.setBackgroundColor(new BaseColor(209,227,233));

		if (centrado)
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);

		tabla.addCell(celda);
	}	
	
	private void agregarCelda(String contenidoCelda, Font fuente, PdfPTable tabla, boolean centrado) {
		PdfPCell celda = new PdfPCell(new Paragraph(contenidoCelda, fuente));
		celda.setBorderWidth(1);
		celda.setBorderColor(BaseColor.GRAY);
		celda.setPadding(5);

		if (centrado)
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);

		tabla.addCell(celda);
	}
	public void crearNotaDoble(PdfPTable tablaPrincipal, boolean borde,float col1, float col2, float col3, PdfPTable tablaColumna) throws DocumentException{
		tablaPrincipal.setWidthPercentage(100);
		tablaPrincipal.setWidths(new float[]{col1,col2,col3});
		PdfPCell celdaPrincipal = new PdfPCell();
		if (borde == true){
			celdaPrincipal.setBorderColor(BaseColor.GRAY);
			celdaPrincipal.setBorderWidth(1);
		}else{
			celdaPrincipal.setBorder(PdfPCell.NO_BORDER);
		}
		
		celdaPrincipal.addElement(tablaColumna);
		tablaPrincipal.addCell(celdaPrincipal);
		emptyCell.setBackgroundColor(BaseColor.WHITE);
		tablaPrincipal.addCell(emptyCell);
		tablaPrincipal.addCell(celdaPrincipal);
		
	}
	
}

