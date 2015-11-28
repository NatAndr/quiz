package com.getjavajob.training.web06.andrianovan.quiz.service.output;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

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
}
