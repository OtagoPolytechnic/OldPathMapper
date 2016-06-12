using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Xamarin.Forms;

namespace PathMapper
{
    public class App : Application
    {
        public App()
        {
            var tabs = new TabbedPage();

            // demonstrates the map control with zooming and map-types
            tabs.Children.Add(new MapPage { Title = "Map" });
            tabs.Children.Add(new OverlayPath { Title = "Path" });

            MainPage = tabs;
        }
    }
}
