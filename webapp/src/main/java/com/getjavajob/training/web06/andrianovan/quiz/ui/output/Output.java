package com.getjavajob.training.web06.andrianovan.quiz.ui.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by Nat on 13.11.2015.
 */
public class Output {

    public void stringToFile(File resultFile, String str) {
        try (PrintStream out = new PrintStream(new FileOutputStream(resultFile))) {
            out.print(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
