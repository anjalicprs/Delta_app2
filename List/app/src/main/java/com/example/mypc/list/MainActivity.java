package com.example.mypc.list;

        import android.app.TabActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.LayoutInflater;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.RadioGroup;
        import android.widget.TabHost;
        import android.widget.TextView;
        import java.util.ArrayList;
        import java.util.List;
public class MainActivity extends TabActivity {
    List<Contact> model=new ArrayList<Contact>();
    ContactAdapter adapter=null;
    EditText name=null;
    EditText phone=null;
    RadioGroup types=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        types=(RadioGroup)findViewById(R.id.types);
        Button save=(Button)findViewById(R.id.save);
        save.setOnClickListener(onSave);
        ListView list=(ListView)findViewById(R.id.restaurants);
        adapter=new ContactAdapter();
        list.setAdapter(adapter);
        TabHost.TabSpec spec=getTabHost().newTabSpec("tag1");
        spec.setContent(R.id.restaurants);
        spec.setIndicator("List", getResources()
                .getDrawable(R.drawable.list));
        getTabHost().addTab(spec);
        spec=getTabHost().newTabSpec("tag2");
        spec.setContent(R.id.details);
        spec.setIndicator("Details", getResources()
                .getDrawable(R.drawable.detail));
        getTabHost().addTab(spec);
        getTabHost().setCurrentTab(0);
        list.setOnItemClickListener(onListClick);
    }
    private View.OnClickListener onSave=new View.OnClickListener() {
        public void onClick(View v) {
            Contact r=new Contact();
            r.setName(name.getText().toString());
            r.setPhone(phone.getText().toString());
            switch (types.getCheckedRadioButtonId()) {
                case R.id.male:
                    r.setType("Male");
                    break;
                case R.id.female:
                    r.setType("Female");
                    break;
            }
            adapter.add(r);
        }
    };
    private AdapterView.OnItemClickListener onListClick=new
            AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent,
                                        View view, int position,
                                        long id) {
                    Contact r=model.get(position);
                    name.setText(r.getName());
                    phone.setText(r.getPhone());
                    if (r.getType().equals("Male")) {
                        types.check(R.id.male);
                    }
                    else  {
                        types.check(R.id.female);
                    }
                    getTabHost().setCurrentTab(1);
                }
            };
    class ContactAdapter extends ArrayAdapter<Contact> {
        ContactAdapter() {
            super(MainActivity.this, R.layout.row, model);
        }
        public View getView(int position, View convertView,
                            ViewGroup parent) {
            View row=convertView;
            ContactHolder holder=null;
            if (row==null) {
                LayoutInflater inflater=getLayoutInflater();
                row=inflater.inflate(R.layout.row, parent, false);
                holder=new ContactHolder(row);
                row.setTag(holder);
            }
            else {
                holder=(ContactHolder)row.getTag();
            }
            holder.populateFrom(model.get(position));
            return(row);
        }
    }
    static class ContactHolder {
        private TextView name=null;
        private TextView phone=null;
        private ImageView icon=null;
        private View row=null;
        ContactHolder(View row) {
            this.row=row;
            name=(TextView)row.findViewById(R.id.title);
            phone=(TextView)row.findViewById(R.id.add);
            icon=(ImageView)row.findViewById(R.id.icon);
        }
        void populateFrom(Contact r) {
            name.setText(r.getName());
            phone.setText(r.getPhone());
            if (r.getType().equals("Male")) {
                icon.setImageResource(R.drawable.boy);
            }
            else if (r.getType().equals("Female")) {
                icon.setImageResource(R.drawable.girl);
            }
            else {
                icon.setImageResource(R.drawable.boy);
            }
        }
    }
}