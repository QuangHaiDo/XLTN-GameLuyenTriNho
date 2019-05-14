package sample;

import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;
import javafx.scene.control.TextField;
import net.sourceforge.javaflacencoder.FLACFileWriter;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

public class SpeechToText  {

    private static Microphone mic = new Microphone(FLACFileWriter.FLAC);
    private static GSpeechDuplex duplex = new GSpeechDuplex("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
    private static String output = "";

    public SpeechToText(){

    }

    public static void startSpeechRecognition(TextField search, String LanguageCode) {
        duplex.setLanguage(LanguageCode);
        duplex.addResponseListener(new GSpeechResponseListener() {
            public void onResponse(GoogleResponse googleResponse) {
                output = googleResponse.getResponse();
                System.out.println("trong startSpeech...:"+ output);
                search.setText(output);
            }
        });
        //Start a new Thread so our application don't lags
        new Thread(()  -> {
            try {
                duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
            } catch (LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    public static void stopSpeechRecognition() {
        duplex.stopSpeechRecognition();
        mic.close();
        System.out.println("Stopping Speech Recognition...." + " , Microphone State is:" + mic.getState());
    }

}

