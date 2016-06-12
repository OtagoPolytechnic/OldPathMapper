from django.shortcuts import render

from django.core import serializers
from django.http import HttpResponse
from pathmapperapp.models import Plant
from pathmapperapp.models import Collection
from pathmapperapp.models import TrackPoint
from pathmapperapp.models import Track
from pathmapperapp.models import PlantCollection
from pathmapperapp.models import UpdateManager

def plant_json(request):
    item = Plant.objects.all()
    data = serializers.serialize("json", item)
    return HttpResponse(data, content_type='application/json')

def collection_json(request):
    item = Collection.objects.all()
    data = serializers.serialize("json", item)
    return HttpResponse(data, content_type='application/json')

def track_json(request):
    item = Track.objects.all()
    data = serializers.serialize("json", item)
    return HttpResponse(data, content_type='application/json')

def trackPoint_json(request):
    item = TrackPoint.objects.all()
    data = serializers.serialize("json", item)
    return HttpResponse(data, content_type='application/json')

def updateManager_json(request):
    item = UpdateManager.objects.all()
    data = serializers.serialize("json", item)
    return HttpResponse(data, content_type='application/json')

def plantCollection_json(request):
    item = PlantCollection.objects.all()
    data = serializers.serialize("json", item)
    return HttpResponse(data, content_type='application/json')