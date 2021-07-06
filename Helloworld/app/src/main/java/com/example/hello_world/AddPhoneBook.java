package com.example.hello_world;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.preference.PreferenceManager;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPhoneBook extends Activity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_phone_book);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        // SharedPreferences 수정을 위한 Editor 객체를 얻어옵니다.
        editor = preferences.edit();

        Button button1 = (Button) findViewById(R.id.add);
        Button button2 = (Button) findViewById(R.id.back);

        EditText idEdit = (EditText)findViewById(R.id.name);
        EditText departEdit = (EditText)findViewById(R.id.department);
        EditText numberEdit = (EditText)findViewById(R.id.phonenumber);
        EditText emailEdit = (EditText)findViewById(R.id.email);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ID = idEdit.getText().toString();
                String depart = departEdit.getText().toString();
                String phonenumber = numberEdit.getText().toString();
                String email = emailEdit.getText().toString();
                String alert;
                editor.putString("ID", ID);
                editor.putString("depart", depart);
                editor.putString("phonenumber", phonenumber);
                editor.putString("email", email);
                editor.apply();

                if( ID.length() == 0 ) {
                    alert = "이름을 넣어주세요!";
                    Toast myToast = Toast.makeText(getApplicationContext(),
                            alert, Toast.LENGTH_SHORT);
                    myToast.show();
                }
                else if( depart.length() == 0 ){
                    alert = "학부를 넣어주세요!";
                    Toast myToast = Toast.makeText(getApplicationContext(),
                            alert, Toast.LENGTH_SHORT);
                    myToast.show();
                }
                else if( phonenumber.length() == 0 ) {
                    alert = "전화번호를 넣어주세요!";
                    Toast myToast = Toast.makeText(getApplicationContext(),
                            alert, Toast.LENGTH_SHORT);
                    myToast.show();
                }
                else if( email.length() == 0 ) {
                    alert = "이메일을 넣어주세요!";
                    Toast myToast = Toast.makeText(getApplicationContext(),
                            alert, Toast.LENGTH_SHORT);
                    myToast.show();
                }
                else {
                    //모두 입력 했을 경우
                    // Creates a new Intent to insert a contact
                    Toast.makeText(getApplicationContext(), "연락처를 주소록에 저장합니다.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                    // Sets the MIME type to match the Contacts Provider
                    Intent Result = new Intent();
                    intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                    //호출 후, 연락처앱에서 전달되는 결과물을 받기 위해 startActivityForResult로 실행한다.
                    // Inserts an email address
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, ID)
                            .putExtra(ContactsContract.Intents.Insert.COMPANY, depart)
                            .putExtra(ContactsContract.Intents.Insert.EMAIL, email)
                            .putExtra(ContactsContract.Intents.Insert.PHONE, phonenumber);

                    startActivityForResult(intent, 10);

                    Result.putExtra("ID", ID)
                            .putExtra("depart", depart)
                            .putExtra("email", email)
                            .putExtra("phonenumber", phonenumber);

                    setResult(RESULT_OK, Result);
                    finish();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "메인으로 돌아갑니다.", Toast.LENGTH_LONG).show();
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

}