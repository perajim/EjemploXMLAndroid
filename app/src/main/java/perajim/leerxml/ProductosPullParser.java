package perajim.leerxml;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by peraj on 14/06/2017.
 */

public class ProductosPullParser {

    private static final String PRODUCTO_NOMBRE = "nombre";
    private static final String PRODUCTO_CANTIDAD = "cantidad";

    private Producto currentProducto = null;
    private String currentTag = null;
    List <Producto> productos = new ArrayList<>();

    public List<Producto> parseXML(Context context){
        try{
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser  xpp = factory.newPullParser();
            try {
                InputStream stream = context.getAssets().open("miarchivo.xml");
                xpp.setInput(stream, null);
                int eventType = xpp.getEventType();
                while(eventType != XmlPullParser.END_DOCUMENT){
                    if(eventType == XmlPullParser.START_TAG){
                        handleStartTag(xpp.getName());
                    }else if(eventType == XmlPullParser.END_TAG){
                        currentTag = null;
                    }
                    else if(eventType == XmlPullParser.TEXT){
                        handleText(xpp.getText());
                    }
                    eventType = xpp.next();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (XmlPullParserException e){
            e.printStackTrace();
        }
        return  productos;
    }

    private void handleText(String text) {
        String xmltext= text;
        if (currentProducto != null  && currentTag != null){
            if(currentTag.equals(PRODUCTO_NOMBRE)){
                currentProducto.setNombre(xmltext);
            }else if(currentTag.equals(PRODUCTO_CANTIDAD)){
                Integer cantidad = Integer.parseInt(xmltext);
                currentProducto.setCantidad(cantidad);
            }
        }
    }

    private void handleStartTag(String name) {
        if(name.equals("producto")){
            currentProducto = new Producto();
            productos.add((currentProducto));
        }else{
            currentTag = name;
        }
    }

}


