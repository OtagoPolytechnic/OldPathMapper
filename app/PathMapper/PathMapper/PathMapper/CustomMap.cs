using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms.Maps;

namespace PathMapper
{
    public class CustomMap : Map
    {

        public List<Position> PathCoordinates { get; set; }

        public CustomMap()
        {
            PathCoordinates = new List<Position>();
        }

    }
}
