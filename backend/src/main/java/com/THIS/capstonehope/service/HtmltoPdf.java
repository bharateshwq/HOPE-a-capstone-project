package com.THIS.capstonehope.service;

import java.io.FileOutputStream;

import org.springframework.stereotype.Service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;

// public class HtmltoPdf {

// 	public String htmlToPdf(String processedHtml) {
		
// 		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
// 		try {
			
// 			PdfWriter pdfwriter = new PdfWriter(byteArrayOutputStream);
			
// 			DefaultFontProvider defaultFont = new DefaultFontProvider(false, true, false);
			
// 			ConverterProperties converterProperties = new ConverterProperties();
			
// 			converterProperties.setFontProvider(defaultFont);
			
// 			HtmlConverter.convertToPdf(processedHtml, pdfwriter, converterProperties);
			
// 			FileOutputStream fout = new FileOutputStream("C:\\Users\\kushala_gopal\\Downloads\\");
			
// 			byteArrayOutputStream.writeTo(fout);
// 			byteArrayOutputStream.close();
			
// 			byteArrayOutputStream.flush();
// 			fout.close();
			
// 			return null;
			
// 		} catch(Exception ex) {
			
// 			//exception occured
// 		}
		
// 		return null;
// 	}
// }
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
public class HtmltoPdf {

    public InputStream htmlToPdf(String processedHtml) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {

            PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);

            DefaultFontProvider defaultFont = new DefaultFontProvider(false, true, false);

            ConverterProperties converterProperties = new ConverterProperties();

            converterProperties.setFontProvider(defaultFont);

            HtmlConverter.convertToPdf(processedHtml, pdfWriter, converterProperties);

            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        } catch (Exception ex) {
            // Handle exception
            ex.printStackTrace();
        }

        return null;
    }
}
