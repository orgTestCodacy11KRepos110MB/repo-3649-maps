package com.mapbox.rctmgl.events;

import android.graphics.PointF;
import androidx.annotation.NonNull;
import android.view.View;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.mapbox.maps.ScreenCoordinate;
import com.mapbox.rctmgl.components.annotation.RCTMGLPointAnnotation;
import com.mapbox.rctmgl.events.constants.EventKeys;
import com.mapbox.rctmgl.events.constants.EventTypes;
import com.mapbox.rctmgl.utils.ConvertUtils;
import com.mapbox.rctmgl.utils.GeoJSONUtils;
import com.mapbox.rctmgl.utils.LatLng;

public class PointAnnotationClickEvent extends MapClickEvent {
    private RCTMGLPointAnnotation mView;
    private LatLng mTouchedLatLng;
    private ScreenCoordinate mScreenPoint;

    public PointAnnotationClickEvent(RCTMGLPointAnnotation view, @NonNull LatLng latLng, @NonNull ScreenCoordinate screenPoint, String eventType) {
        super(view, latLng, screenPoint, eventType);
        mView = view;
        mTouchedLatLng = latLng;
        mScreenPoint = screenPoint;
    }

    @Override
    public String getKey() {
        return getType().equals(EventTypes.ANNOTATION_SELECTED) ? EventKeys.POINT_ANNOTATION_SELECTED : EventKeys.POINT_ANNOTATION_DESELECTED;
    }

    @Override
    public WritableMap getPayload() {
        WritableMap properties = new WritableNativeMap();
        properties.putString("id", mView.getID());
        properties.putDouble("screenPointX", mScreenPoint.getX());
        properties.putDouble("screenPointY", mScreenPoint.getY());
        return GeoJSONUtils.toPointFeature(mTouchedLatLng, properties);
    }
}
