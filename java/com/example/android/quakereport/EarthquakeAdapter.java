package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.android.quakereport.R.id.magnitude_text_view;

/**
 * Created by danielbeleza on 16/02/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{


    public final static String LOCATION_SEPARATOR = " of ";


    public EarthquakeAdapter(Activity context, List<Earthquake> earthquakes) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for three TextViews, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_listview, parent, false);

            final Earthquake currentEarthquake = getItem(position);

            // Cria um novo objeto Date do tempo em mili-segundos do earthquake
            Date dateObject = new Date(currentEarthquake.getTimeInMiliseconds());

            // Formata a data string (i.e. "Mar 3, 1984")
            // Ver metodo formatDate
            String formattedDate = formatDate(dateObject);


            /**DATA**/
            TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_text_view);
            // Mostra a DATA do terremoto atual na TextView
            dateTextView.setText(formattedDate);

            /**HORA**/
            // Formata a HORA em string (i.e. "4:30PM")
            // Ver metodo formatTime
            String formattedTime = formatTime(dateObject);

            TextView timeTextView = (TextView) listItemView.findViewById(R.id.time_text_view);
            // Mostra a HORA do terremoto atual na TextView
            timeTextView.setText(formattedTime);

            String primaryLocation;
            String locationOffset;

            // Divide a String location em 2 partes - location offset/location.
            String currentLocation = currentEarthquake.getLocation();

            if(currentLocation.contains(LOCATION_SEPARATOR)){
                String[] parts = currentLocation.split(LOCATION_SEPARATOR);
                primaryLocation = parts[0] + LOCATION_SEPARATOR;
                locationOffset = parts [1];
            } else {
                locationOffset = getContext().getString(R.string.near_the);
                primaryLocation = currentLocation;
            }
            // As Strings são separadas pela palavra "of", mantendo a mesma


            /**MAGNITUDE**/
            TextView magnitudeTextView = (TextView) listItemView.findViewById(magnitude_text_view);

            // Sete a cor de fundo apropriada no círculo de magnitude.
            // Busque o fundo do TextView, que é um GradientDrawable.
            GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

            // Obtenha a cor de fundo apropriada baseada na magnitude do terremoto atual
            int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

            // Sete a cor no círculo de magnitude
            magnitudeCircle.setColor(magnitudeColor);
            // Formata a magnitude para mostrar 1 casa decimal (atraves do metodo criado "formatMagnitude")
            String formattedMagnitude =  formatMagnitude(currentEarthquake.getMagnitude());
            // Mostra a magnitude do terramoto atual
            magnitudeTextView.setText(formattedMagnitude);



            /**PRIMARY LOCATION**/
            TextView primaryLocationTextView = (TextView) listItemView.findViewById(R.id.primary_location_text_view);
            // Mostra a DISTANCIA da cidade mais proxima que o terramoto foi sentido
            primaryLocationTextView.setText(primaryLocation);

            TextView locationOffsetTextView = (TextView) listItemView.findViewById(R.id.location_offset_text_view);
            // Mostra a LOCALIZAÇÃO mais proxima em que se sentiu o terramoto
            locationOffsetTextView.setText(locationOffset);



        }
        return listItemView;

    }

    /**
     * Return a formated data string (i.e. "Mar 3, 1984") of an Date object.
     */
    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL DD, yyyy"); //Disposição da data é definida pelo parametro inserido no novo objeto criado "SimpleDateFormat"
        return dateFormat.format(dateObject);
    }

    /**
     * Return a formated data string (i.e. "4:30 PM") of a Date object.
     */
    private String formatTime(Date dateObject){
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Retorna a string magnitude formatada mostrando 1 casa decimal (i.e. "3.2")
     * de um valor de magnitude decimal.
     */
    private String formatMagnitude(double magnitude){
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch(magnitudeFloor){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }
}
