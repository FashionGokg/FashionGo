package com.maifeng.fashiongo.fragment;

import java.util.ArrayList;
import java.util.List;

import com.maifeng.fashiongo.R;
import com.maifeng.fashiongo.adapter.ShoppingcarAdapter;
import com.maifeng.fashiongo.base.ShoppingcarType;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ShoppingcarFragment extends Fragment {
	private View topbar;
	private LinearLayout ll_returnbtn;
	private LinearLayout ll_functionbtn;
	private TextView tv_title,tv_name_function;
	private ListView listView;
	private List<ShoppingcarType> listdate;
	private ShoppingcarType sType;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.shoppingcar_fragment, container,false);
		
		//����������
        topbar = view.findViewById(R.id.topbar);
		topbar.findViewById(R.id.ll_returnbtn).setVisibility(View.INVISIBLE);
        ll_functionbtn= (LinearLayout) topbar.findViewById(R.id.ll_functionbtn);
        tv_title = (TextView) topbar.findViewById(R.id.tv_title);
        tv_name_function = (TextView) topbar.findViewById(R.id.tv_name_function);
        tv_title.setText("���ﳵ");
        tv_name_function.setText("�༭");
        ll_functionbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getActivity(), "����˱༭��ť", Toast.LENGTH_SHORT).show();
			}
		});
        
        listView = (ListView) view.findViewById(R.id.list_shoppingcar);
        
        //����Դ
        listdate = new ArrayList<ShoppingcarType>();
        for (int i = 0; i < 10; i++) {
        	 int id =i;
        	 String	goodsName ="������Ʒ"+i;
        	 int	goodsImage=R.drawable.img_png3;
        	 Double	price=200.00;
        	 int	number=2;
        	 String	model="��ɫ";
        	 String	size="M��";
        	 sType=new ShoppingcarType(id, goodsName, goodsImage, price, number, model, size);
        	 listdate.add(sType);
        }
        
        ShoppingcarAdapter myAdapter =new ShoppingcarAdapter(getActivity(),listdate);
        listView.setAdapter(myAdapter);
		
		
		return view;
	}
}
