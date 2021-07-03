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
                Toast.makeText(getApplicationContext(), "연락처를 주소록에 저장합니다.", Toast.LENGTH_LONG).show();

                String ID = idEdit.getText().toString();
                String depart = departEdit.getText().toString();
                String phonenumber = numberEdit.getText().toString();
                String email = emailEdit.getText().toString();

                editor.putString("ID", ID);
                editor.putString("depart", depart);
                editor.putString("phonenumber", phonenumber);
                editor.putString("email", email);
                editor.apply();
                /*
                if( ID.length() == 0 ) showDialog("Input Name !");
                else if( depart.length() == 0 ) showDialog("Input Age !");
                else if( phonenumber.length() == 0 ) showDialog("Input Phone Number!");
                else if( email.length() == 0 ) showDialog("Input Job !");
                else{
                    //모두 입력 했을 경우

                    AddressInfo addressInfo = new AddressInfo();
                    addressInfo.setName(name);
                    addressInfo.setAge(Integer.parseInt(age));
                    addressInfo.setPhone(phone);
                    addressInfo.setJob(job);

                    databaseHelper.insertAddressData(addressInfo);
                **/
                // Creates a new Intent to insert a contact
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                // Sets the MIME type to match the Contacts Provider

                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                //호출 후, 연락처앱에서 전달되는 결과물을 받기 위해 startActivityForResult로 실행한다.
                // Inserts an email address
                intent.putExtra(ContactsContract.Intents.Insert.NAME, ID)
                        .putExtra(ContactsContract.Intents.Insert.COMPANY, depart)
                        .putExtra(ContactsContract.Intents.Insert.EMAIL, email)
                        .putExtra(ContactsContract.Intents.Insert.PHONE, phonenumber);


                startActivityForResult(intent, 10);

                setResult(RESULT_OK);
                finish();
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

        /*
        void showDialog() {

            DialogFragment newFragment = DialogF.newInstance(

                    R.string.alert_dialog_two_buttons_title);

            newFragment.show(getFragmentManager(), "dialog");

        }
        public void doPositiveClick() {
            // Do stuff here.
            Log.i("FragmentAlertDialog", "Positive click!");
        }
        public void doNegativeClick() {
            // Do stuff here.
            Log.i("FragmentAlertDialog", "Negative click!");
        }**/
}