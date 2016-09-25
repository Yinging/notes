package com.notes;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

public class AddContent extends Activity implements OnClickListener {

	private String val;// ����Intent
	private Button savebtn, deletebtn;
	private EditText ettext;
	private ImageView c_img;
	private VideoView c_video;

	private NotesDB notesDB;// �������ݿ����
	private SQLiteDatabase dbWriter;// ������дȨ��

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addcontent);
		val = getIntent().getStringExtra("flag");
		savebtn = (Button) findViewById(R.id.save);
		deletebtn = (Button) findViewById(R.id.delete);
		ettext = (EditText) findViewById(R.id.ettext);
		c_img = (ImageView) findViewById(R.id.c_img);
		c_video = (VideoView) findViewById(R.id.c_video);
		savebtn.setOnClickListener(this);
		deletebtn.setOnClickListener(this);

		notesDB = new NotesDB(this);// ʵ����
		dbWriter = notesDB.getWritableDatabase();// д�����ݵ�Ȩ��
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.save:
			addDB();
			finish();// �رյ���ǰ��activity
			break;
		case R.id.delete:
			finish();
			break;
		}
	}

	// ���������������
	public void addDB() {
		ContentValues cv = new ContentValues();
		cv.put(NotesDB.CONTENT, ettext.getText().toString());
		cv.put(NotesDB.TIME, getTime());
		dbWriter.insert(NotesDB.TABLE_NAME, null, cv);
	}

	// ��ȡϵͳʱ��
	private String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
		Date date = new Date();
		String str = format.format(date);
		return str;
	}
}
