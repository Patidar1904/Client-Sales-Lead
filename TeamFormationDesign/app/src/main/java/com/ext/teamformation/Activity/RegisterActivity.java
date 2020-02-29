package com.ext.teamformation.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.ext.teamformation.R;
import com.ext.teamformation.Utils.FileManagerUtill;
import com.kbeanie.imagechooser.api.ChosenFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RegisterActivity extends FileManagerUtill {

    @BindView(R.id.edtBOD)
    EditText edtBOD;
    @BindView(R.id.edtGender)
    EditText edtGender;
    @BindView(R.id.edtResume)
    EditText edtResume;
    @BindView(R.id.edtCertificate)
    EditText edtCertificate;
    @BindView(R.id.lnSignUp)
    LinearLayout lnSignUp;
    @BindView(R.id.lnLogin)
    LinearLayout lnLogin;
    @BindView(R.id.spnGender)
    Spinner spnGender;
    String pickFrom = "";

    @Override
    public Void OnImageChosanResponce(String FileExt, String Path, String Base64) {
        return null;
    }

    @Override
    public Void OnFileChosan(ChosenFile file, String Base64) {
        Log.e("file__", file.getFileName() + "__" + file.getFilePath() + "_");

        if (pickFrom.equalsIgnoreCase("Resume")) {
            edtResume.setText(file.getFileName());
        } else {
            edtCertificate.setText(file.getFileName());
        }
        return null;
    }

    @Override
    public Void OnImageFromCrop(String filePath, String Base64) {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        this.setMemoryAllocation();
        this.setListeners();
    }

    private void setListeners() {
        lnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        lnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, DashboardActivity.class);
                startActivity(i);
                finish();
            }
        });

        edtBOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mYear, mMonth, mDay;

                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                edtBOD.setVisibility(View.VISIBLE);
                                edtBOD.setText((monthOfYear + 1) + "-" + dayOfMonth + "-" + year);

                                String strDate1 = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                SimpleDateFormat input = new SimpleDateFormat("dd-MM-yyyy");
                                SimpleDateFormat output = new SimpleDateFormat("MMM dd, yyyy");
                                try {
                                    Date oneWayTripDate = input.parse(strDate1);

                                    edtBOD.setText(output.format(oneWayTripDate));


                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        edtCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFrom = "Certificate";
                chooseFile();
            }
        });

        edtGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spnGender.performClick();
            }
        });

        edtResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFrom = "Resume";
                chooseFile();
            }
        });
    }

    private void setMemoryAllocation() {

        ArrayList<String> gender = new ArrayList<>();
        gender.add("Male");
        gender.add("Female");
        gender.add("Other");
        gender.add("Choose Gender");

        ArrayAdapter adtGender = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, gender) {
            @Override
            public int getCount() {
                return gender.size() - 1;
            }
        };

        spnGender.setAdapter(adtGender);
        spnGender.setSelection(gender.size() - 1);

        spnGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edtGender.setText(gender.get(position) + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
