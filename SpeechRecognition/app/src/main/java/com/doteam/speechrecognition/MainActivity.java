package com.doteam.speechrecognition;
import android.app.Activity;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;




public class MainActivity extends Activity {
    public Intent i;
    SpeechRecognizer mRecognizer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Intent
        i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        //음성 인식언어 설정   kr-KR 이니까 한국어
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");

        //Recognizer 사용
        mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mRecognizer.setRecognitionListener(listener);

        Button button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //이코드가 음성 인식을 시작하는 코드
                //지금은 테스트용으로 button에 작동하도록 구현
                mRecognizer.startListening(i);
            }
        });

    }







    private RecognitionListener listener = new RecognitionListener() {

        @Override
        public void onRmsChanged(float rmsdB) {
            // TODO Auto-generated method stub

        }



        //음성인식이 성공했을때 결과를 이용하는 함수
        //지금은 음성인식 결과가 HelloWorld!!부분에 들어가게 코딩됨
        @Override
        public void onResults(Bundle results) {
            String key = "";
            key = SpeechRecognizer.RESULTS_RECOGNITION;
            ArrayList<String> mResult = results.getStringArrayList(key);
            String[] rs = new String[mResult.size()];
            mResult.toArray(rs);
            TextView tv = (TextView) findViewById(R.id.textView);
            tv.setText(""+rs[0]);
        }

        @Override
        public void onReadyForSpeech(Bundle params) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPartialResults(Bundle partialResults) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onEvent(int eventType, Bundle params) {
            // TODO Auto-generated method stub

        }

        //음성인식에 오류가 발생했을 때
        @Override
        public void onError(int error) {
            if(error == mRecognizer.ERROR_NETWORK_TIMEOUT){
                Toast.makeText(getApplicationContext(),"네트워크 타임아웃 에러",Toast.LENGTH_SHORT).show();
            }
            else if(error == mRecognizer.ERROR_NETWORK){
                Toast.makeText(getApplicationContext(),"네트워크 에러",Toast.LENGTH_SHORT).show();
            }

            else if(error == mRecognizer.ERROR_AUDIO){
                Toast.makeText(getApplicationContext(),"녹음 에러",Toast.LENGTH_SHORT).show();
            }

            else if(error == mRecognizer.ERROR_SERVER){
                Toast.makeText(getApplicationContext(),"서버 에러",Toast.LENGTH_SHORT).show();
            }

            else if(error == mRecognizer.ERROR_CLIENT){
                Toast.makeText(getApplicationContext(),"클라이언트 에러",Toast.LENGTH_SHORT).show();
            }
            else if(error == mRecognizer.ERROR_SPEECH_TIMEOUT){
                Toast.makeText(getApplicationContext(),"아무 음성도 듣지 못함",Toast.LENGTH_SHORT).show();
            }
            else if(error == mRecognizer.ERROR_NO_MATCH){
                Toast.makeText(getApplicationContext(),"적당한 결과를 찾지 못함",Toast.LENGTH_SHORT).show();
            }
            else if(error == mRecognizer.ERROR_RECOGNIZER_BUSY){
                Toast.makeText(getApplicationContext(),"인스턴스가 바쁨",Toast.LENGTH_SHORT).show();
            }





            // TODO Auto-generated method stub

        }

        @Override
        public void onEndOfSpeech() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onBufferReceived(byte[] buffer) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onBeginningOfSpeech() {
            // TODO Auto-generated method stub

        }
    };

}