package com.example.hello_world;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.provider.ContactsContract;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.Toast;

import com.example.hello_world.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class fragment1 extends Fragment {

    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList mSearchData;
    private int count = 0;
    CoordinatorLayout coordinatorLayout;

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                final int position = viewHolder.getAdapterPosition();
                final CustomData item = mAdapter.getData().get(position);

                mAdapter.removeItem(position);

                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mAdapter.restoreItem(item, position);
                        mRecyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(mRecyclerView);
    }




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

        coordinatorLayout = view.findViewById(R.id.coordinatorLayout);

        //populateRecyclerView();
        enableSwipeToDeleteAndUndo();

        SwipeRefreshLayout swipeRefreshLayout= view.findViewById(R.id.swipe_layout);

        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );


        mAdapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
            @Override
            public void onClick(String name,String phone) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                Dialog alert = alertDialog.create();
                alertDialog.setTitle(name);
                alertDialog.setMessage(phone);
                alertDialog.setIcon(R.drawable.ic_launcher_background);
                String tel = ("tel:"+phone).replace("-","");

                alertDialog.setPositiveButton("문자", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setType("vnd.android-dir/mms-sms");
                        intent.putExtra("sms_body", "");
                        intent.setData(Uri.parse("sms:"+phone));
                        startActivity(intent);

                    }
                });

                alertDialog.setNegativeButton("전화", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                    }
                });

                alertDialog.show();
            }
        });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //여기서 새로고침을!!!
                mAdapter.notifyDataSetChanged();

                swipeRefreshLayout.setRefreshing(false);
            }
        });


        Button buttonInsert = (Button)view.findViewById(R.id.button_insert);
        Button buttonlink = (Button)view.findViewById(R.id.button_link);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        if(requestCode == 0){
            name = data.getExtras().getString("ID");
            MobileNumber = data.getExtras().getString("phonenumber");
            WorkName = data.getExtras().getString("depart");
            emailID = data.getExtras().getString("email");

            Toast myToast = Toast.makeText(getActivity().getApplicationContext(),
                    "추가된 연락처 이름 : " + name + "\n연락처 전화번호 : " + MobileNumber
                            + "\n이메일: " + emailID, Toast.LENGTH_SHORT);
            myToast.show();
            mSearchData.add(new CustomData(name,WorkName,MobileNumber,emailID));
            mAdapter.notifyDataSetChanged();
        }
        else if (requestCode == 1) {
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
            mAdapter.notifyDataSetChanged();
        }
    }




    private void initDataset() {
        //for Test
        mSearchData = new ArrayList<>();
        mSearchData.add(new CustomData("Kimjanghyun","Computer of Science", "010-3646-1933","big01ad@kaist.ac.kr"));
        mSearchData.add(new CustomData("Lee HoJun","Biological Science", "010-1234-5678","dontknow@unist.ac.kr"));
        mSearchData.add(new CustomData("tester","Electrical Engineering", "010-8765-4321","abcd@kaist.ac.kr"));
    }
}