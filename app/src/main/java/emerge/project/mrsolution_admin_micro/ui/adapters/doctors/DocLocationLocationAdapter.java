package emerge.project.mrsolution_admin_micro.ui.adapters.doctors;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import emerge.project.mrsolution_admin_micro.R;
import emerge.project.mrsolution_admin_micro.ui.activity.doctors.DoctorsPresenter;
import emerge.project.mrsolution_admin_micro.ui.activity.doctors.DoctorsPresenterImpli;
import emerge.project.mrsolution_admin_micro.ui.activity.doctors.DoctorsView;
import emerge.project.mrsolution_admin_micro.utils.entittes.LocationEntitie;


/**
 * Created by Himanshu on 4/10/2015.
 */
public class DocLocationLocationAdapter extends RecyclerView.Adapter<DocLocationLocationAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<LocationEntitie> locItems;

    DoctorsPresenter doctorsPresenter;

    public DocLocationLocationAdapter(Context mContext, ArrayList<LocationEntitie> item, DoctorsView doctorsView) {
        this.mContext = mContext;
        this.locItems = item;

        doctorsPresenter= new DoctorsPresenterImpli(doctorsView);

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_location, parent, false);
        return new MyViewHolder(itemView);


    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final LocationEntitie loc =locItems.get(position);

      holder.textviewAddress.setText(loc.getAddress());
      holder.textviewLocation.setText(loc.getName());



        if (loc.isSelect()) {
            holder.cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.colorgold));
        } else {
            holder.cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));
        }


        holder.relativeLayoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loc.isSelect()){
                    locItems.get(position).setSelect(false);
                    doctorsPresenter.assignLocationToDocStart(loc,false);
                }else {
                    locItems.get(position).setSelect(true);
                    doctorsPresenter.assignLocationToDocStart(loc,true);
                }
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return locItems.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder  {


        @BindView(R.id.card_view)
        CardView cardView;

        @BindView(R.id.relativeLayout_main)
        RelativeLayout relativeLayoutMain;

        @BindView(R.id.textview_location_address)
        TextView textviewAddress;

        @BindView(R.id.textview_location)
        TextView textviewLocation;




        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }


}
