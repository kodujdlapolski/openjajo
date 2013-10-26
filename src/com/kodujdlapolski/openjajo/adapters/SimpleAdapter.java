package com.kodujdlapolski.openjajo.adapters;

import com.kodujdlapolski.openjajo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SimpleAdapter extends ArrayAdapter<AdapterItem> {

	private AdapterItem[] objects;

	public SimpleAdapter(Context context, int resource, AdapterItem[] objects) {
		super(context, resource, objects);
		this.objects = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.data_row, null);
		}

		((TextView) convertView.findViewById(R.id.item1))
				.setText(objects[position].getName());
		((TextView) convertView.findViewById(R.id.item2))
				.setText(objects[position].getValue());

		return convertView;

	}
}
