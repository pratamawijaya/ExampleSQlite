package id.pratamawijaya.examplesqlite;

import java.util.List;

import id.pratamawijaya.examplesqlite.entity.E_Buku;
import id.pratamawijaya.examplesqlite.util.DBAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity implements OnItemClickListener, TextWatcher
{
	private ListView				lv;
	private EditText				search;
	private DBAdapter				db;
	private ArrayAdapter<E_Buku>	adapter;
	private List<E_Buku>			listBuku;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.lv_buku);
		lv.setEmptyView(findViewById(R.id.empty));

		search = (EditText) findViewById(R.id.search);
		search.addTextChangedListener(this);

		db = DBAdapter.getInstance(this);

		listBuku = db.getAllBuku();

		if (listBuku != null)
		{
			adapter = new ArrayAdapter<E_Buku>(this, android.R.layout.simple_list_item_1, listBuku);
			lv.setAdapter(adapter);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.add_data:
				startActivity(new Intent(this, AddDataActivity.class));
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
	{

	}

	@Override
	public void afterTextChanged(Editable s)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * untuk filter search
	 */
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count)
	{
		// TODO Auto-generated method stub
		adapter.getFilter().filter(s.toString());

	}

}
