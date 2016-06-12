using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using Xamarin.Forms.Maps;

namespace PathMapper
{
    public class OverlayPath : ContentPage
    {

       

        public OverlayPath()
        {
            var customMap = new CustomMap
            {
                IsShowingUser = true,
                MapType = MapType.Street,
                HeightRequest = 100,
                WidthRequest = 960,
                VerticalOptions = LayoutOptions.FillAndExpand
            };

            customMap.PathCoordinates.Add(new Position(-45.856542, 170.518708));
            customMap.PathCoordinates.Add(new Position(-45.856640, 170.518893));
            customMap.PathCoordinates.Add(new Position(-45.856692, 170.518966));
            customMap.PathCoordinates.Add(new Position(-45.856784, 170.519006));
            customMap.PathCoordinates.Add(new Position(-45.856954, 170.519076));
            customMap.PathCoordinates.Add(new Position(-45.857084, 170.519228));
            customMap.PathCoordinates.Add(new Position(-45.857224, 170.519456));
            customMap.PathCoordinates.Add(new Position(-45.857086, 170.519698));
            customMap.PathCoordinates.Add(new Position(-45.856932, 170.520177));
            customMap.PathCoordinates.Add(new Position(-45.856828, 170.520344));
            customMap.PathCoordinates.Add(new Position(-45.856652, 170.520521));
            customMap.PathCoordinates.Add(new Position(-45.856586, 170.520728));
            customMap.PathCoordinates.Add(new Position(-45.856438, 170.521035));
            customMap.PathCoordinates.Add(new Position(-45.856405, 170.521318));
            customMap.PathCoordinates.Add(new Position(-45.856636, 170.521193));
            customMap.PathCoordinates.Add(new Position(-45.856691, 170.521513));

            customMap.MoveToRegion(MapSpan.FromCenterAndRadius(new Position(-45.856542, 170.518708), Distance.FromMiles(1.0)));
            Content = customMap;
        }

    }
}
