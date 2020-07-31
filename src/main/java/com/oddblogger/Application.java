package com.oddblogger;

import com.oddblogger.utils.Utils;
import java.io.File;

public class Application {

  public static void main(String[] args) {

    Utils utils = new Utils();

    ClassLoader classLoader = Application.class.getClassLoader();

    File file = new File(classLoader.getResource("ob-logo.png").getFile());

    utils.convertImageToPdf(file);

  }

}
