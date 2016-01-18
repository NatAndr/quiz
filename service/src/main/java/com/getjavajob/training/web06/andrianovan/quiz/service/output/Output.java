package com.getjavajob.training.web06.andrianovan.quiz.service.output;

import java.io.*;

/**
 * Created by Nat on 13.11.2015.
 */
public class Output {

    public void writeToOutputStream(OutputStream os, String str) {

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(os));
            for (String s : str.split("\r\n")) {
                bw.write(s);
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stringToFile(File resultFile, String str) {
        try (PrintStream out = new PrintStream(new FileOutputStream(resultFile))) {
            out.print(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
