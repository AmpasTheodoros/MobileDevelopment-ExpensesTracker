package com.example.asgraph;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GraphActivity extends AppCompatActivity {

    Button insertButton;
    EditText inputTextY;
    GraphView graphView;

    DatabaseHandler dbh;
    SQLiteDatabase sqLiteDatabase;

    LineGraphSeries<DataPoint> dataseries = new LineGraphSeries<>(new DataPoint[0]);

    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    insertButton = (Button) findViewById(R.id.insertButton);
    inputTextY = (EditText) findViewById(R.id.inputTextY);
    graphView = (GraphView) findViewById(R.id.graph);

    dbh = new DatabaseHandler(this);
          sqLiteDatabase = dbh.getWritableDatabase();

          graphView.addSeries(dataseries);
          graphView.getGridLabelRenderer().setNumHorizontalLabels(3);
          insertData();
    }

    public void insertData() {
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Dexomaste Dedomena kai ta bazi sto  db
                //to x einai imerominia
                long xValue = new Date().getTime();
                // y = Text
                int yValue = Integer.parseInt(String.valueOf(inputTextY.getText()));

                dbh.insertToData(xValue, yValue);

                //Perni ta dedomena kai ta bazi sto grafima

                dataseries.resetData(getDataPoint());

                graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                    @Override
                    public String formatLabel(double value, boolean isValueX) {
                        if (isValueX) {
                            return sdf.format(new Date((long) value));
                        } else {
                            return super.formatLabel(value, isValueX);
                        }
                    }
                });
            }
        });
    }

            private DataPoint[] getDataPoint() {
                String[] column = {"xValue", "yValue"};
                @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.query("Table1", column, null, null, null, null, null);

                DataPoint[] dataPoints = new DataPoint[cursor.getCount()];

                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToNext();
                    dataPoints[i] = new DataPoint(cursor.getLong(0), cursor.getInt(1));
                }
                return dataPoints;
            }
        }