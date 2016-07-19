using CoreLocation; 
using MapKit;
using PathMapper;
using PathMapper.iOS;
using UIKit;
using Xamarin.Forms;
using Xamarin.Forms.Maps.iOS;
using Xamarin.Forms.Platform.iOS;

[assembly:ExportRenderer(typeof(CustomMap), typeof(CustomMapRenderer))]
namespace PathMapper.iOS
{
    public class CustomMapRenderer : MapRenderer
    {
        MKPolylineRenderer polylineRenderer;

        protected override void OnElementChanged (ElementChangedEventArgs<View> e)
        {
            base.OnElementChanged(e);

            if (e.OldElement != null) {
                var nativeMap = Control as MKMapView;
                nativeMap.OverlayRenderer = null;
            }

            if (e.NewElement != null) {
                var formsMap = (CustomMap)e.NewElement;
                var nativeMap = Control as MKMapView;

                nativeMap.OverlayRenderer = GetOverlayRenderer;

                CLLocationCoordinate2D[] coordinates = new CLLocationCoordinate2D[formsMap.PathCoordinates.Count];

                int i = 0;
                foreach (var position in formsMap.PathCoordinates)
                {
                    coordinates[i] = new CLLocationCoordinate2D(position.Latitude, position.Longitude);
                    i++;
                }

                var routeOverlay = MKPolyline.FromCoordinates(coordinates);
                nativeMap.AddOverlay(routeOverlay);
            }   
        }

        MKOverlayRenderer GetOverlayRenderer(MKMapView mapView, IMKOverlay overlay)
        {
            if (polylineRenderer == null)
            {
                polylineRenderer = new MKPolylineRenderer(overlay as MKPolyline);
                polylineRenderer.FillColor = UIColor.Blue;
                polylineRenderer.StrokeColor = UIColor.Red;
                polylineRenderer.LineWidth = 3;
                polylineRenderer.Alpha = 0.4f;
            }
            return polylineRenderer;
        }

    }
}
                                                               