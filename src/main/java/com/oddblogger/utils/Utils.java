package com.oddblogger.utils;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.logging.Logger;

public class Utils {

  static final Logger logger = Logger.getLogger(Utils.class.getName());

  public void convertImagesToPdf(Path directory) {
    File[] images = directory.toFile().listFiles();
    if (images != null) {
      for (File image : images) {
        convertImageToPdf(image);
      }
    }
  }

  public void convertImageToPdf(File imageFile) {
    PdfWriter pdfWriter = null;
    String pdfFile = imageFile.getName().split("\\.")[0] + ".pdf";

    try {
      pdfWriter = new PdfWriter(pdfFile);
    } catch (FileNotFoundException e) {
      logger.info("Unable to create pdfWriter. Pdf file not found for file: " + imageFile.toString());
      return;
    }

    PdfDocument pdfDocument = new PdfDocument(pdfWriter);

    PageSize pageSize = new PageSize(936.0F, 684.0F);
    try (Document document = new Document(pdfDocument, pageSize)) {

      document.setLeftMargin(0);
      document.setRightMargin(0);
      document.setBottomMargin(0);
      document.setTopMargin(0);

      try {

        ImageData data = ImageDataFactory.create(imageFile.toString());
        com.itextpdf.layout.element.Image img = new com.itextpdf.layout.element.Image(data);
        document.add(img);
      } catch (MalformedURLException e) {
        logger.info("Unable to add image to the pdf. Error: " + e.toString());
      }
    }
    logger.info(imageFile.getName() + " converted to pdf");
  }

}
