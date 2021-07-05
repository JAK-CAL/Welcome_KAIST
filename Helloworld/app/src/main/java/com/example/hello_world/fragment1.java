package com.example.hello_world;

<<<<<<< HEAD
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment1 newInstance(String param1, String param2) {
        fragment1 fragment = new fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }
=======

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class fragment1 extends Fragment {

    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList mSearchData;
    private int count = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(0);
        mAdapter = new CustomAdapter(mSearchData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        Button buttonInsert = (Button)view.findViewById(R.id.button_insert);
        Button buttonlink = (Button)view.findViewById(R.id.button_link);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                count++;
                CustomData data = new CustomData(count + "", "Apple" + count, "사과" + count);
                // mArrayList.add(0, dict); //RecyclerView의 첫 줄에 삽입
                mSearchData.add(data); // RecyclerView의 마지막 줄에 삽입
                **/
                Toast.makeText(getActivity(), "새 연락처를 추가합니다.", Toast.LENGTH_LONG).show();
                // 액티비티 전환 코드
                Intent intent = new Intent(getActivity(), AddPhoneBook.class);
                startActivityForResult(intent,0);
                mAdapter.notifyDataSetChanged();
            }
        });
        buttonlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "연락처를 연동합니다.", Toast.LENGTH_LONG).show();
                // 액티비티 전환 코드
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
                //호출 후, 연락처앱에서 전달되는 결과물을 받기 위해 startActivityForResult로 실행한다.
                startActivityForResult(intent, 1);
                mAdapter.notifyDataSetChanged();
            }
        });


        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                int pos = CustomAdapter.CustomViewHolder
                // Write your code here
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


                if (pos != RecyclerView.NO_POSITION) {
                    // 데이터 리스트로부터 아이템 데이터 참조.
                    RecyclerItem item = mData.get(pos) ;

                    // TODO : use item.
                }

                builder.setTitle(mSearchData.get(position));

                builder.setItems(R.array.LAN, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int pos)
                    {
                        String[] items = getResources().getStringArray(R.array.LAN);
                        Toast.makeText(getActivity().getApplicationContext(),items[pos],Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String ID,name,data_;
        String MobileNumber = "";
        String HomeNumber = "";
        String WorkNumber = "";
        String WorkFaxNumber = "";
        String emailID = "";
        String WorkemailID = "";
        String WorkName = "";
        String WorkPosition = "";

        int type = -1;
        int dataColumn = -1;
        int typeColumn = -1;

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }
        count++;
        if (requestCode == 1) {
            Uri dataUri = data.getData();
            Cursor cursor =  getActivity().getContentResolver().query(dataUri, null, null, null, null);

            try {
                cursor.moveToFirst();
                ID = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
// 1. 사용자 이름 저장
                name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
            } finally {
                cursor.close();
            }

            Cursor phoneCursor = getActivity().getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                    null,
                    ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?",     // where
                    new String[]{ID, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE},  // where param
                    null);
            try {
                if (phoneCursor.moveToFirst()) {
                    dataColumn = phoneCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DATA);
                    typeColumn = phoneCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.TYPE);

                    do {
                        data_ = phoneCursor.getString(dataColumn);
                        type = phoneCursor.getInt(typeColumn);
                        // ATLog.d(this, "Phone Info : "+ data + " / type : "+ type);
                        switch(type){
                            case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                            case ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE:
                                // 회사 전화 번호
                                if (data_ != null && data_.length() > 0){
                                    WorkNumber = data_;
                                }
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK:
                                // 회사 팩스 번호
                                if (data_ != null && data_.length() > 0){
                                    WorkFaxNumber = data_;
                                }
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_FAX_HOME:
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                // 휴대폰 번호
                                if (data_ != null && data_.length() > 0){
                                    MobileNumber = data_;
                                }
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                                // 집전화 번호
                                if (data_ != null && data_.length() > 0){
                                    HomeNumber = data_;
                                }
                                break;
                        }

                    }while(phoneCursor.moveToNext());

                }
            }finally {
                phoneCursor.close();
            }

            // 3. 이메일 정보 읽기
            Cursor emailCursor = getActivity().getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                    null,
                    ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?",     // where
                    new String[]{ID, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE},  // where param
                    null);

            try {
                if (emailCursor.moveToFirst()) {
                    dataColumn = emailCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.DATA);
                    typeColumn = emailCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.TYPE);

                    do {
                        data_ = emailCursor.getString(dataColumn);
                        type = emailCursor.getInt(typeColumn);

                        //
                        switch(type){
                            case ContactsContract.CommonDataKinds.Email.TYPE_HOME:
                                // 개인 이메일
                                if (data_ != null && data_.length() > 0){
                                    emailID = data_;
                                }
                                break;
                            case ContactsContract.CommonDataKinds.Email.TYPE_WORK:
                                // 회사 이메일
                                if (data_ != null && data_.length() > 0){
                                    WorkemailID = data_;
                                }
                                break;
                        }

                    }while(emailCursor.moveToNext());
                }
            }finally {
                emailCursor.close();
            }

            // 4. 조직 정보 읽기
            Cursor organizationCursor = getActivity().getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                    null,
                    ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?",     // where
                    new String[]{ID, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE},  // where param
                    null);

            try {
                if (organizationCursor.moveToFirst()) {
                    dataColumn = organizationCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Organization.DATA);
                    typeColumn = organizationCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Organization.TYPE);
                    String position; // 직급
                    int positionColumn = organizationCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Organization.TITLE);

                    do {
                        data_ = organizationCursor.getString(dataColumn); // 조직명
                        type = organizationCursor.getInt(typeColumn);
                        position = organizationCursor.getString(positionColumn); // 직급명

                        // 조직은 무조건 회사로 취급한다.
                        switch(type){
                            case ContactsContract.CommonDataKinds.Organization.TYPE_WORK:
                                // 조직 정보
                                if (data_ != null && data_.length() > 0){
                                    WorkName = data_;
                                    WorkPosition = position;
                                }
                                break;
                        }

                    }while(organizationCursor.moveToNext());
                }
            }finally {
                organizationCursor.close();
            }

            // 5. 주소 정보 가져오기
            Cursor addressCursor = getActivity().getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                    null,
                    ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?",     // where
                    new String[]{ID, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE},  // where param
                    null);

            try {
                if (addressCursor.moveToFirst()) {
                    typeColumn = addressCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.StructuredPostal.TYPE);

                    do {
                        type = addressCursor.getInt(typeColumn);
                        String poBox = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX));
                        String street = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
                        String city = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
                        String state = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
                        String postalCode = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
                        String country = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));


                        switch(type){
                            case ContactsContract.CommonDataKinds.StructuredPostal.TYPE_WORK:

                                break;
                        }

                    }while(addressCursor.moveToNext());
                }
            }finally {
                addressCursor.close();
            }

            Toast myToast = Toast.makeText(getActivity().getApplicationContext(),
                    "연락처 이름 : " + name + "\n연락처 전화번호 : " + MobileNumber, Toast.LENGTH_SHORT);
            myToast.show();
            mSearchData.add(new CustomData(name,WorkName,MobileNumber,emailID));
        }
    }

    private void initDataset() {
        //for Test
        mSearchData = new ArrayList<>();
        //mSearchData.add(new CustomData("test","hi", "JOY MINI (48/50)",""));
        //mSearchData.add(new CustomData("anothertest","english", "RALLYIST (5/50)"));
        //mSearchData.add(new CustomData("tester","hello", "TEST (10/30)"));
    }
>>>>>>> 91149bd22395e5e53fc1c180f45bbcb9635b6ea9
}