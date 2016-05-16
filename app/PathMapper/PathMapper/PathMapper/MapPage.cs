using System;
using Xamarin.Forms;
using Xamarin.Forms.Maps;
using System.Diagnostics;

namespace PathMapper
{
    public class MapPage : ContentPage
    {
        Map map;

        public MapPage()
        {
            map = new Map { HeightRequest = 100, WidthRequest = 960, VerticalOptions = LayoutOptions.FillAndExpand };

            map.MoveToRegion(new MapSpan(new Position(0, 0), 360, 360));

            var stack = new StackLayout { Spacing = 0 };
            stack.Children.Add(map);
            Content = stack;
        }

        static void CalculateBoundingCoordinates(MapSpan region)
        {
            var center = region.Center;
            var halfheightDegrees = region.LatitudeDegrees / 2;
            var halfwidthDegrees = region.LongitudeDegrees / 2;

            var left = center.Longitude - halfwidthDegrees;
            var right = center.Longitude + halfwidthDegrees;
            var top = center.Latitude + halfheightDegrees;
            var bottom = center.Latitude - halfheightDegrees;

            if (left < -180) left = 180 + (180 + left);
            if (right > 180) right = (right - 180) - 180;           
        }
    }
}
