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
            map = new Map {
                IsShowingUser = true,
                HeightRequest = 100,
                WidthRequest = 960,
                VerticalOptions = LayoutOptions.FillAndExpand };

            //var gardensPosition = new Position(-45.856621, 170.518691); //Botanic Gardens Information Center
            // Position doesn't seem to work in scope with the FromCenterAndRadius Method, it might have to be global
            MapSpan gardensMap = MapSpan.FromCenterAndRadius(new Position(-45.856621, 170.518691), Distance.FromMeters(100));
            map.MoveToRegion(gardensMap);

            var stack = new StackLayout { Spacing = 0 };
            stack.Children.Add(map);
            Content = stack;
        }

        
    }
}
